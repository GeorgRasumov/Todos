package com.georg.todos;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.georg.todos.databinding.SingleTodoBinding;

public class Single_Todo extends FrameLayout {
    private SingleTodoBinding binding;

    private boolean done = false;
    private CrossOut crossout;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
        crossout.setDrawcrossout(!done);
    }

    public void toggleDone() {
        if (done){
            setDone(false);
        }else{
            setDone(true);
        }
    }

    static private FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
    );

    public Single_Todo(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        // Inflate the layout with View Binding
        binding = SingleTodoBinding.inflate(LayoutInflater.from(context), this, true);

        int list_margin = (int) getResources().getDimension(R.dimen.todo_list_margin);
        layoutParams.setMargins(list_margin,list_margin/2,list_margin,list_margin/2);
        setLayoutParams(layoutParams);

        int background_margin = (int) getResources().getDimension(R.dimen.todo_background_margin);
        int text_heigth = (int) getResources().getDimension(R.dimen.todo_text_size);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, text_heigth+2*background_margin);
        binding.getRoot().setLayoutParams(layoutParams);

        //FrameLayout.LayoutParams layoutParams_edit = new FrameLayout.LayoutParams(text_heigth, text_heigth);
        //binding.edit.setLayoutParams(layoutParams_edit);

        EditText text = binding.todoText;
        binding.edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                text.bringToFront();
                text.setFocusable(true);
                text.setFocusableInTouchMode(true);
                text.requestFocus();
                text.setSelection(text.getText().length());
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(text, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        text.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    text.setFocusable(false);
                    binding.mainButton.bringToFront();
                }
            }
        });

        binding.mainButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        crossout = new CrossOut(context, text);
        binding.getRoot().addView(crossout);

        binding.mainButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDone();
                Log.d("myTest", "test");
            }
        });

    }
}