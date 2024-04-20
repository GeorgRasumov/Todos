package com.georg.todos;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TodoItemSaver {

    public TodoItemSaver(){

    }


    public static void safeTodos(Context context, List<SingleTodo> todos) {
        StringBuilder sb = new StringBuilder();
        for (SingleTodo todo : todos){
            sb.append(todo.getText());
            sb.append("\n");
        }
        //Log.d("MyTest", "input: "+sb.toString());
        safeString(context, sb.toString(), "Test");
    }

    public static List<SingleTodo> getTodos(Context context, SelectionStateProvider selectionStateProvider){
        String totalString = getString(context, "Test");
        List<SingleTodo> todos = new ArrayList<>();
        StringBuilder todoText = new StringBuilder();
        for(char ch: totalString.toCharArray()){
            if (ch=='\n'){
                SingleTodo newTodo = new SingleTodo(context, selectionStateProvider);
                newTodo.setText(todoText.toString());
                todos.add(newTodo);
                todoText = new StringBuilder();
            }
            else{
                todoText.append(ch);
            }
        }
        return todos;
    }

    public static void safeString(Context context, String safeString, String filename){
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(safeString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getString(Context context, String filename){
        String contents;
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(filename);
        } catch (FileNotFoundException e) {
            return "";
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            contents = stringBuilder.toString();
        }
        Log.d("MyTest", contents);
        return contents;
    }


}
