package com.georg.todos.models;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.georg.todos.BR;
import com.georg.todos.types.TodoTypes;

public class MainModel extends BaseObservable {

    private TodoItemsHandler todoItemsHandler;
    @Bindable TodoTypes currentTodoType;
    @Bindable boolean closeApp = false;
    public void onCreate(Context context){
        ModelProvider.getDataHandler().setContext(context);
        todoItemsHandler = ModelProvider.getToDoItemsHandler();
        setCurrentTodoType(TodoTypes.TODAY);
    }

    public void onDestroy(){
        ModelProvider.getToDoItemsHandler().safeTodos();
    }

    public boolean getCloseApp(){
        return closeApp;
    }

    private void setCloseApp(boolean closeApp){
        this.closeApp = closeApp;
        notifyPropertyChanged(BR.closeApp);
    }

    public void goBack(){
        if(todoItemsHandler.getCurrentTodoType() != TodoTypes.TODAY){
            setCurrentTodoType(TodoTypes.TODAY);
        }
        else{
            setCloseApp(true);
        }
    }

    public TodoTypes getCurrentTodoType() {
        return currentTodoType;
    }

    public void setCurrentTodoType(TodoTypes todoType) {
        this.currentTodoType = todoType;
        notifyPropertyChanged(BR.currentTodoType);
    }


}
