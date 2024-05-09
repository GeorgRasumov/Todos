package com.georg.todos.models;

public class ModelProvider {
    private static TodoItemsHandler todoItemsHandler;
    private static DataHandler dataHandler;
    private static MainModel mainModel;

    public static TodoItemsHandler getToDoItemsHandler() {
        if (todoItemsHandler == null) {
            todoItemsHandler = new TodoItemsHandler();
        }
        return todoItemsHandler;
    }

    public static DataHandler getDataHandler() {
        if (dataHandler == null) {
            dataHandler = new DataHandler();
        }
        return dataHandler;
    }

    public static MainModel getMainModel() {
        if (mainModel == null) {
            mainModel = new MainModel();
        }
        return mainModel;
    }
}