package com.georg.todos;

import java.util.ArrayList;
import java.util.List;

public class TodoItemManager {


    List<SingleTodoView> allTodos = new ArrayList<>();

    public TodoItemManager(){

    }

    public void addTodo(SingleTodoView todo){
        allTodos.add(todo);
    }

    public List<SingleTodoView> getAllTodos(){
        return allTodos;
    }


}
