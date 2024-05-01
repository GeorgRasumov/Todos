package com.georg.todos.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.georg.todos.viewModels.TodoListViewModel;

public class TodoViewModel extends ViewModel {

    protected TodoListViewModel todoListViewModel;
    protected MutableLiveData<String> text = new MutableLiveData<String>();
    protected MutableLiveData<Integer> position = new MutableLiveData<Integer>();
    protected MutableLiveData<Boolean> done = new MutableLiveData<Boolean>(false);
    protected MutableLiveData<Boolean> selected = new MutableLiveData<Boolean>();

    protected MutableLiveData<Integer> id = new MutableLiveData<Integer>();


    public TodoViewModel(int id, TodoListViewModel todoListViewModel) {
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

    public boolean onTextClicked() {
        done.setValue(!done.getValue());
        return true;
    }

    public boolean onTextLongClicked(){
        todoListViewModel.onTodoTextLongClicked(this);
        return true;
    }

    public boolean onEditClicked() {
        todoListViewModel.onTodoEditClicked(this);
        return true;
    }

}
