package com.georg.todos.viewModels;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.georg.todos.R;
import com.georg.todos.models.Todo;
import com.georg.todos.types.SingleLiveEvent;
import com.georg.todos.views.ToDoView;
import com.georg.todos.types.BottomMenus;

import java.util.ArrayList;
import java.util.List;

public class TodoListViewModel extends ViewModel {

    private List<Todo> todos = new ArrayList<Todo>();
    private SingleLiveEvent<Void> toDoListChanged = new SingleLiveEvent<>();
    private MutableLiveData<BottomMenus> currentMenu = new MutableLiveData<>(BottomMenus.ADD);

    //private DataHandler dataHandler;

    public SingleLiveEvent<Void> getToDoListChangedLiveData() {
        return toDoListChanged;
    }

    public List<ITodoViewModel> getTodoViewModels() {
        List<ITodoViewModel> todoViewModels = new ArrayList<>(todos);
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

    public void onTodoEditClicked(Todo todo){

    }

    public void onTodoTextLongClicked(Todo todo){

    }

    int id = 0;
    private void addTodo(){
        Todo todo = new Todo(id, this);
        id++;
        todos.add(todo);
        toDoListChanged.call();
    }

}
