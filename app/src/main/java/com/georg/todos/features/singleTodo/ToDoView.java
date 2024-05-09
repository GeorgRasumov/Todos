package com.georg.todos.features.singleTodo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.lifecycle.LifecycleOwner;

import com.georg.todos.R;
import com.georg.todos.databinding.SingleTodoBinding;
import com.georg.todos.features.todoList.views.CrossOutView;

public class ToDoView extends FrameLayout{
    private SingleTodoBinding binding;
    private TodoViewModel viewModel;
    private CrossOutView crossout;
    private EditText editText;
    private int id;

    public ToDoView(Context context, TodoViewModel viewModel, LifecycleOwner lifecycleOwner) {
        super(context);
        this.viewModel = viewModel;
        init(context, lifecycleOwner);
    }

    public TodoViewModel getViewModel() {
        return viewModel;
    }

    public void setDone(boolean done) {
        crossout.setDrawCrossout(done);
    }

    @Override
    public void setSelected(boolean selected) {
        if(selected){
            binding.getRoot().setBackgroundResource(R.drawable.single_todo_background_selected);
        }
        else{
            binding.getRoot().setBackgroundResource(R.drawable.single_todo_background);
        }
    }

    private void init(Context context, LifecycleOwner lifecycleOwner) {
        // Inflate the layout with View Binding
        binding = SingleTodoBinding.inflate(LayoutInflater.from(context), this, true);
        binding.setViewModel(viewModel);

        editText = binding.todoText;
        editText.setFocusableInTouchMode(false);

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

        //Initialize the Crossout
        crossout = new CrossOutView(context, editText);
        binding.singleTodo.addView(crossout);

        viewModel.getDoneLiveData().observe(lifecycleOwner, this::setDone);
        viewModel.getEditTextEvent().observe(lifecycleOwner, aVoid -> onEditTextEvent());
        viewModel.getSelectedLiveData().observe(lifecycleOwner, this::setSelected);
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                onEditTextFocusChange(v, hasFocus);
            }
        });
    }

    private void onEditTextFocusChange(View v, boolean hasFocus) {
        if (!hasFocus){
            if (!hasFocus){
                editText.setFocusableInTouchMode(false);
                binding.mainButton.bringToFront();
            }
        }
    }

    private void onEditTextEvent(){
        editText.bringToFront();
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().length());
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }


}