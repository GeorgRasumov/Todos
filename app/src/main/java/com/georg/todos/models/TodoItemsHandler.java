package com.georg.todos.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;

import com.georg.todos.BR;
import com.georg.todos.types.TodoTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TodoItemsHandler extends BaseObservable {

    @Bindable
    private TodoTypes currentTodoType; //No setter because it should only be set in the MainModel
    @Bindable // Only bindable to register changes. There is no get, because the View should only get the current todoItems
    private ArrayList<TodoItem>todoItems;
    private ToDoItemIOHandler toDoItemIOHandler;
    public TodoItemsHandler(){
        toDoItemIOHandler = new ToDoItemIOHandler();
        ModelProvider.getMainModel().addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                onMainModelPropertyChanged(propertyId);
            }
        });
        LoadTodoItems();
    }

    public void createNewTodoItem(TodoTypes type){
        TodoItem newTodo = new TodoItem(type);
        todoItems.add(newTodo);
        notifyPropertyChanged(BR.todoItems);
    }

    public void removeTodoItem(int id){
        for (TodoItem todoItem : todoItems){
            if (todoItem.getId() == id){
                todoItems.remove(todoItem);
                notifyPropertyChanged(BR.todoItems);
                return;
            }
        }
    }

    // returns a list of all the todoItems of the current type
    public ArrayList<TodoItem> getCurrentTodoItems() {
        ArrayList<TodoItem> currentTodoItems = new ArrayList<>(todoItems.stream().filter(todoItem -> todoItem.getType() == currentTodoType).collect(Collectors.toList()));
        return currentTodoItems;
    }

    public void safeTodos(){
        toDoItemIOHandler.safeTodos(todoItems);
    }

    public TodoTypes getCurrentTodoType(){
        return currentTodoType;
    }

    public void onMainModelPropertyChanged(int propertyId){
        if(propertyId == BR.currentTodoType){
            currentTodoType = ModelProvider.getMainModel().getCurrentTodoType();
            notifyPropertyChanged(BR.todoItems);
        }
    }

    private void LoadTodoItems(){
        todoItems = toDoItemIOHandler.getTodos();
        notifyPropertyChanged(BR.todoItems);
    }
}
