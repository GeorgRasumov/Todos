package com.georg.todos.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.georg.todos.SingleTodo;
import com.georg.todos.types.BottomMenus;
import com.georg.todos.types.SelectionStateProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListViewModel extends ViewModel {

    private MutableLiveData<List<SingleTodo>> todos = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<BottomMenus> currentMenu = new MutableLiveData<>(BottomMenus.ADD);

    public LiveData<List<SingleTodo>> getTodos() {
        return todos;
    }

    public LiveData<BottomMenus> getCurrentMenu() {
        return currentMenu;
    }

    public void addTodo(Context context, SelectionStateProvider provider) {
        List<SingleTodo> currentTodos = todos.getValue();
        SingleTodo newTodo = new SingleTodo(context, provider);
        currentTodos.add(newTodo);
        todos.postValue(currentTodos);
    }

    public void removeSelectedTodos() {
        List<SingleTodo> currentTodos = todos.getValue();
        List<SingleTodo> toRemove = currentTodos.stream().filter(SingleTodo::isSelected).collect(Collectors.toList());
        currentTodos.removeAll(toRemove);
        todos.postValue(currentTodos);
        currentMenu.postValue(BottomMenus.ADD);
    }

    public void updateMenu() {
        boolean selectionMode = todos.getValue().stream().anyMatch(SingleTodo::isSelected);
        currentMenu.postValue(selectionMode ? BottomMenus.OPTIONS : BottomMenus.ADD);
    }

}
