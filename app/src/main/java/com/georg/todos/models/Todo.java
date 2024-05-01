package com.georg.todos.models;

import androidx.lifecycle.MutableLiveData;

import com.georg.todos.viewModels.ITodoViewModel;
import com.georg.todos.viewModels.TodoListViewModel;

public class Todo implements ITodoViewModel {

    private TodoListViewModel todoListViewModel;
    private MutableLiveData<String> text = new MutableLiveData<String>();
    private MutableLiveData<Integer> position = new MutableLiveData<Integer>();
    private MutableLiveData<Boolean> done = new MutableLiveData<Boolean>(false);
    private MutableLiveData<Boolean> selected = new MutableLiveData<Boolean>();

    private MutableLiveData<Integer> id = new MutableLiveData<Integer>();


    public Todo(int id, TodoListViewModel todoListViewModel) {
        this.id.setValue(id);
        this.todoListViewModel = todoListViewModel;
    }

    public MutableLiveData<String> getTextLiveData() {
        return text;
    }

    public MutableLiveData<Integer> getPositionLiveData() {
        return position;
    }

    public MutableLiveData<Boolean> getDoneLiveData() {
        return done;
    }

    public MutableLiveData<Boolean> getSelectedLiveData() {
        return selected;
    }
    public MutableLiveData<Integer> getIdLiveData() {
        return id;
    }


    @Override
    public boolean onTextClicked() {
        done.setValue(!done.getValue());
        return true;
    }

    @Override
    public boolean onTextLongClicked(){
        todoListViewModel.onTodoTextLongClicked(this);
        return true;
    }

    @Override
    public boolean onEditClicked() {
        todoListViewModel.onTodoEditClicked(this);
        return true;
    }

}
