package com.georg.todos.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.georg.todos.types.TodoTypes;

public class TodoItem extends BaseObservable {

    private static int nextId = 0;
    @Bindable
    private int id = 0;
    @Bindable
    private String text = "";
    @Bindable
    private boolean done = false;
    @Bindable
    private TodoTypes type = TodoTypes.TODAY;

    public TodoItem(TodoTypes type) {
        this.setId(nextId++);
        setType(type);
        setText("new ToDo");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
        notifyPropertyChanged(BR.done);
    }

    public TodoTypes getType() {
        return type;
    }

    public void setType(TodoTypes type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }


}
