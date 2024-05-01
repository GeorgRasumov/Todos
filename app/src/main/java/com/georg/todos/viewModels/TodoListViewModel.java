package com.georg.todos.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.georg.todos.types.SingleLiveEvent;
import com.georg.todos.types.BottomMenus;

import java.util.ArrayList;
import java.util.List;

public class TodoListViewModel extends ViewModel {

    private List<TodoViewModelModifier> todos = new ArrayList<>();
    private SingleLiveEvent<Void> toDoListChanged = new SingleLiveEvent<>();
    private MutableLiveData<BottomMenus> currentMenu = new MutableLiveData<>(BottomMenus.ADD);

    //private DataHandler dataHandler;

    public SingleLiveEvent<Void> getToDoListChangedLiveData() {
        return toDoListChanged;
    }

    public List<TodoViewModel> getTodoViewModels() {
        List<TodoViewModel> todoViewModels = new ArrayList<>(todos);
        return todoViewModels;
    }

    public LiveData<BottomMenus> getCurrentMenu() {
        return currentMenu;
    }

    public void onStop() {
    }

    public void onAddClicked(){
        addTodo();
    }

    public void onRDeleteClicked(){
        //externalTodos.
    }


    public void onReminderClicked(){

    }

    public void onMoveClicked(){

    }

    public void onTodoEditClicked(TodoViewModel todo){

    }

    public void onTodoTextLongClicked(TodoViewModel todo){

    }

    int id = 0;
    private void addTodo(){
        TodoViewModelModifier todo = new TodoViewModelModifier(id, this);
        id++;
        todos.add(todo);
        toDoListChanged.call();
    }

}
