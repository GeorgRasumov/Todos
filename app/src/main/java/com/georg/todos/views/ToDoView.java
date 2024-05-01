package com.georg.todos.views;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.lifecycle.LifecycleOwner;

import com.georg.todos.R;
import com.georg.todos.databinding.SingleTodoBinding;
import com.georg.todos.viewModels.TodoViewModel;

public class ToDoView extends FrameLayout{
    private SingleTodoBinding binding;
    private TodoViewModel viewModel;
    private CrossOut crossout;
    private EditText editText;
    private int id;

    public ToDoView(Context context, TodoViewModel viewModel, LifecycleOwner lifecycleOwner) {
        super(context);
        this.viewModel = viewModel;
        init(context);
        viewModel.getDoneLiveData().observe(lifecycleOwner, this::setDone);
    }

    public TodoViewModel getViewModel() {
        return viewModel;
    }

    public void setDone(boolean done) {
        crossout.setDrawCrossout(done);
    }

    public void setText(String text){
        editText.setText(text);
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

    private void init(Context context) {
        // Inflate the layout with View Binding
        binding = SingleTodoBinding.inflate(LayoutInflater.from(context), this, true);
        binding.setViewModel(viewModel);

        editText = binding.todoText;

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
        crossout = new CrossOut(context, editText);
        binding.singleTodo.addView(crossout);

    }


}