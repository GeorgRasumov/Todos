package com.georg.todos.models;

import com.georg.todos.types.TodoTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToDoItemIOHandler {

    private String fileName = "TodosFile2";
    private DataHandler dataHandler;

    public ToDoItemIOHandler(){
        dataHandler = ModelProvider.getDataHandler();
    }


    public void safeTodos(List<TodoItem> todos) {
        StringBuilder sb = new StringBuilder();
        for (TodoItem todo : todos){
            sb.append(todo.getId());
            sb.append(";");
            sb.append(todo.getText());
            sb.append(";");
            sb.append(todo.isDone());
            sb.append(";");
            sb.append(todo.getType());
            sb.append("\n");
        }
        dataHandler.safeString(sb.toString(), fileName);
    }

    public ArrayList<TodoItem> getTodos(){

        ArrayList<TodoItem> todoItems = new ArrayList<TodoItem>();

        // If the file does not exist, return an empty list
        if (!dataHandler.fileExists(fileName)){
            return todoItems;
        }

        String totalString = dataHandler.getString(fileName);
        ArrayList<TodoItem> todos = new ArrayList<>();
        String[] lines = totalString.split("\n");
        for(String line : lines){
            String[] parts = line.split(";");
            TodoTypes type = TodoTypes.valueOf(parts[3]);
            TodoItem newTodo = new TodoItem(type);
            newTodo.setId(Integer.parseInt(parts[0]));
            newTodo.setText(parts[1]);
            newTodo.setDone(Boolean.parseBoolean(parts[2]));
            todoItems.add(newTodo);
        }
        return todoItems;
    }

}
