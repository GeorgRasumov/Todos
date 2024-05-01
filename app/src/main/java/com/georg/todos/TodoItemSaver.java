package com.georg.todos;

public class TodoItemSaver {

//    public TodoItemSaver(){
//
//    }
//
//
//    public static void safeTodos(DataHandler dataHandler, List<ToDoView> todos) {
//        StringBuilder sb = new StringBuilder();
//        for (ToDoView todo : todos){
//            sb.append(todo.getText());
//            sb.append("\n");
//        }
//        //Log.d("MyTest", "input: "+sb.toString());
//        dataHandler.safeString(sb.toString(), "Test");
//    }
//
//    public static List<ToDoView> getTodos(DataHandler dataHandler, SelectionStateProvider selectionStateProvider){
//        String totalString = dataHandler.getString("Test");
//        List<ToDoView> todos = new ArrayList<>();
//        StringBuilder todoText = new StringBuilder();
//        for(char ch: totalString.toCharArray()){
//            if (ch=='\n'){
//                ToDoView newTodo = new ToDoView(context, selectionStateProvider);
//                newTodo.setText(todoText.toString());
//                todos.add(newTodo);
//                todoText = new StringBuilder();
//            }
//            else{
//                todoText.append(ch);
//            }
//        }
//        return todos;
//    }

}
