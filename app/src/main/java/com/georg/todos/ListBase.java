package com.georg.todos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.georg.todos.databinding.AddBarBinding;
import com.georg.todos.databinding.FragmentListBinding;
import com.georg.todos.databinding.OptionsBarBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListBase extends Fragment implements View.OnClickListener, SingleTodo.OnSelectedChangeListener, SelectionStateProvider {

    private static enum BottomMenus {
        ADD,
        OPTIONS;
    }

    private FragmentListBinding binding;
    List<SingleTodo> todos = new ArrayList<>();

    private void changeBottomMenu(BottomMenus option){
        switch (option){
            case ADD:
                binding.menuPlaceholder.removeAllViews();
                AddBarBinding addBarBinding = AddBarBinding.inflate(getLayoutInflater(), binding.menuPlaceholder, true);
                addBarBinding.addButton.setOnClickListener(this);
                break;
            case OPTIONS:
                binding.menuPlaceholder.removeAllViews();
                OptionsBarBinding optionsBarBinding = OptionsBarBinding.inflate(getLayoutInflater(), binding.menuPlaceholder, true);
                optionsBarBinding.moveButton.setOnClickListener(this);
                optionsBarBinding.reminderButton.setOnClickListener(this);
                optionsBarBinding.deleteButton.setOnClickListener(this);
                break;
        }
    }
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        changeBottomMenu(BottomMenus.ADD);

        todos = TodoItemSaver.getTodos(getContext(), this);
        for(SingleTodo todo: todos){
            todo.addSelectedChangeListener(this);
            binding.list.addView(todo);
        }

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onStop() {
        TodoItemSaver.safeTodos(getContext(), todos);
        super.onStop();
        binding = null;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add_button) {
            SingleTodo todo = new SingleTodo(getContext(), this);
            todo.addSelectedChangeListener(this);
            todos.add(todo);
            binding.list.addView(todo);
        }
        else if(v.getId()==R.id.delete_button){
            List<SingleTodo> toRemove = new ArrayList<>();
            for(SingleTodo todo: todos){
                if (todo.isSelected()){
                    binding.list.removeView(todo);
                    toRemove.add(todo);
                    changeBottomMenu(BottomMenus.ADD);
                }
            }
            todos.removeAll(toRemove);
        }
    }

    @Override
    public void onSelectedStatusChanged(boolean isSelected) {
        for(SingleTodo todo: todos){
            if(getIsInSelectionMode()){
                changeBottomMenu(BottomMenus.OPTIONS);
            }
            else{
                changeBottomMenu(BottomMenus.ADD);
            }
        }
    }

    @Override
    public boolean getIsInSelectionMode() {
        boolean todoSelectet = false;
        for(SingleTodo todo: todos){
            if (todo.isSelected()){
                todoSelectet = true;
            }
        }
        return todoSelectet;
    }
}