package com.georg.todos.features.todoList.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.georg.todos.features.singleTodo.ToDoView;
import com.georg.todos.features.todoList.TodoListViewModel;
import com.georg.todos.databinding.AddBarBinding;
import com.georg.todos.databinding.FragmentListBinding;
import com.georg.todos.databinding.OptionsBarBinding;
import com.georg.todos.types.BottomMenus;
import com.georg.todos.features.singleTodo.TodoViewModel;

import java.util.ArrayList;
import java.util.List;

public class ToDoListView extends Fragment{

    private FragmentListBinding binding;
    private TodoListViewModel viewModel;
    private List<ToDoView> toDoViews = new ArrayList<ToDoView>();

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        initalizeViewModel();
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
        viewModel.onStop();
        super.onStop();
        binding = null;
    }

    private void initalizeViewModel(){
        viewModel = new ViewModelProvider(getActivity()).get(TodoListViewModel.class);
        viewModel.getCurrentMenu().observe(getViewLifecycleOwner(), this::changeBottomMenu);
        viewModel.getToDoListChangedLiveData().observe(getViewLifecycleOwner(), this::onTodosChanged);
    }

    private void onTodosChanged(Void aVoid) {
        List<TodoViewModel>  viewModels = viewModel.getTodoViewModels();
        List<TodoViewModel>  newViewModels = new ArrayList<>(viewModels);
        List<ToDoView>  oldViews = new ArrayList<>();
        for (ToDoView toDoView : toDoViews){
            TodoViewModel viewModel = toDoView.getViewModel();
            if (viewModels.contains(viewModel)){
                newViewModels.remove(viewModel);
            }
            else{
                oldViews.add(toDoView);
            }
        }
        for (TodoViewModel viewModel : newViewModels){
            addTodo(viewModel);
        }
        for (ToDoView toDoView : oldViews){
            toDoViews.remove(toDoView);
            removeTodo(toDoView);
        }
    }

    private void addTodo(TodoViewModel toDoViewModel){
        ToDoView toDoView = new ToDoView(getContext(), toDoViewModel, getViewLifecycleOwner());
        toDoViews.add(toDoView);
        binding.list.addView(toDoView);
    }

    private void removeTodo(ToDoView toDoView){
        toDoViews.remove(toDoView);
        binding.list.removeView(toDoView);
    }

    private void changeBottomMenu(BottomMenus option){
        switch (option){
            case ADD:
                binding.menuPlaceholder.removeAllViews();
                AddBarBinding addBarBinding = AddBarBinding.inflate(getLayoutInflater(), binding.menuPlaceholder, true);
                addBarBinding.setViewModel(viewModel);
                addBarBinding.setLifecycleOwner(this);
                break;
            case OPTIONS:
                binding.menuPlaceholder.removeAllViews();
                OptionsBarBinding optionsBarBinding = OptionsBarBinding.inflate(getLayoutInflater(), binding.menuPlaceholder, true);
                optionsBarBinding.setViewModel(viewModel);
                optionsBarBinding.setLifecycleOwner(this);
                break;
        }
    }

}