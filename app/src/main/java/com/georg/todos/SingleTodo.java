package com.georg.todos;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.georg.todos.databinding.SingleTodoBinding;

import java.util.ArrayList;
import java.util.List;

public class SingleTodo extends FrameLayout implements View.OnClickListener {
    private SingleTodoBinding binding;
    private boolean done = false;
    private CrossOut crossout;
    private EditText editText;
    private ImageButton editButton;
    private Button crossoutButton;

    private List<ToDoDoneChangeListener> todoStatusChangeListeners = new ArrayList<ToDoDoneChangeListener>();

    public interface ToDoDoneChangeListener {
        public void onToDoStatusChanged(boolean done);
    }

    public void addTodoStatusChangeListener(ToDoDoneChangeListener listener) {
        todoStatusChangeListeners.add(listener);
    }

    public SingleTodo(Context context) {
        super(context);
        init(context);
    }


    //Setter and Getter Functions
    public void setDone(boolean done) {
        this.done = done;
        for (ToDoDoneChangeListener listener : todoStatusChangeListeners) {
            if (listener != null) {
                listener.onToDoStatusChanged(this.done);
            }
        }
    }
    public void toggleDone() {
        setDone(!done);
    }

    private void init(Context context) {
        // Inflate the layout with View Binding
        binding = SingleTodoBinding.inflate(LayoutInflater.from(context), this, true);

        //Set the layout params for the parent frame layout
        int list_margin = (int) getResources().getDimension(R.dimen.todo_list_margin);
        FrameLayout.LayoutParams layoutParams_self = new FrameLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams_self.setMargins(list_margin,list_margin/2,list_margin,list_margin/2);
        setLayoutParams(layoutParams_self);

        //Initialize the Background
        int background_margin = (int) getResources().getDimension(R.dimen.todo_background_margin);
        int textHeight = (int) getResources().getDimension(R.dimen.todo_text_size);
        FrameLayout.LayoutParams layoutParams_background = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, textHeight+2*background_margin);
        binding.getRoot().setLayoutParams(layoutParams_background);

        //Initialize the Button used to Cross out a Todo
        crossoutButton = binding.mainButton;
        crossoutButton.setOnClickListener(this);

        //Initialize the editable Text
        editText = binding.todoText;
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    editText.setFocusable(false);
                    crossoutButton.bringToFront();
                }
            }
        });

        //Initialize the Crossout
        crossout = new CrossOut(context, editText);
        addTodoStatusChangeListener(crossout);
        binding.getRoot().addView(crossout);

        //Initialize the Button used to edit the text
        editButton = binding.edit;
        editButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==editButton) {
            editText.bringToFront();
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            editText.setSelection(editText.getText().length());
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
        else if(v==crossoutButton){
            toggleDone();
        }
    }
}