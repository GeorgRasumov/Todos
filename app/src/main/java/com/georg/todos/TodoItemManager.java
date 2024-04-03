package com.georg.todos;

import java.util.ArrayList;
import java.util.List;

public class TodoItemManager {


    List<ToDo> allTodos = new ArrayList<ToDo>();

    public TodoItemManager(){

    }

    public void addTodo(ToDo todo){
        allTodos.add(todo);
    }

    public List<ToDo> getAllTodos(){
        return allTodos;
    }


}
