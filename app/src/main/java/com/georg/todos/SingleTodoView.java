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

public class SingleTodoView extends FrameLayout implements View.OnClickListener, View.OnLongClickListener {
    private SingleTodoBinding binding;
    private boolean done = false;
    private boolean isSelected = false;
    private CrossOut crossout;
    private EditText editText;
    private ImageButton editButton;
    private Button mainButton;
    SelectionStateProvider selectionStateProvider;

    public SingleTodoView(Context context, SelectionStateProvider selectionStateProvider) {
        super(context);
        this.selectionStateProvider = selectionStateProvider;
        init(context);
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

        //Initialize the Button used to Crossout or select
        mainButton = binding.mainButton;
        mainButton.setOnClickListener(this);
        mainButton.setOnLongClickListener(this);

        //Initialize the editable Text
        editText = binding.todoText;
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    editText.setFocusable(false);
                    mainButton.bringToFront();
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

    private List<ToDoDoneChangeListener> todoStatusChangeListeners = new ArrayList<ToDoDoneChangeListener>();
    public interface ToDoDoneChangeListener {
        public void onToDoStatusChanged(boolean done);
    }
    public void addTodoStatusChangeListener(ToDoDoneChangeListener listener) {
        todoStatusChangeListeners.add(listener);
    }
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

    private List<OnSelectedChangeListener> selectedChangeListeners = new ArrayList<OnSelectedChangeListener>();

    public interface OnSelectedChangeListener {
        public void onSelectedStatusChanged(boolean done);
    }

    public void addSelectedChangeListener(OnSelectedChangeListener listener) {
        selectedChangeListeners.add(listener);
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.isSelected = selected;
        if(selected){
            binding.getRoot().setBackgroundResource(R.drawable.single_todo_background_selected);
        }
        else{
            binding.getRoot().setBackgroundResource(R.drawable.single_todo_background);
        }
        for (OnSelectedChangeListener listener : selectedChangeListeners) {
            if (listener != null) {
                listener.onSelectedStatusChanged(this.isSelected);
            }
        }
    }


    private void handleEditClick(){
        editText.bringToFront();
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().length());
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    private void handleSelectionClick(){
        setSelected(!isSelected());
    }

    private void handleCrossoutClick(){
        toggleDone();
    }
    @Override
    public void onClick(View v) {
        if(selectionStateProvider.getIsInSelectionMode()){
            if (v == mainButton) {
                handleSelectionClick();
            }
        }
        else {
            if (v == editButton) {
                handleEditClick();
            } else if (v == mainButton) {
                handleCrossoutClick();
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if(v==mainButton){
            handleSelectionClick();
            return true;
        }
        return false;
    }
}