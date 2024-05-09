package com.georg.todos.features.todoList;

import androidx.databinding.Observable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.georg.todos.BR;
import com.georg.todos.features.singleTodo.TodoViewModel;
import com.georg.todos.misc.SelectionStateProvider;
import com.georg.todos.models.ModelProvider;
import com.georg.todos.models.TodoItem;
import com.georg.todos.models.TodoItemsHandler;
import com.georg.todos.misc.SingleLiveEvent;
import com.georg.todos.types.BottomMenus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TodoListViewModel extends ViewModel implements SelectionStateProvider {

    private boolean isInSelectionMode = false;
    private List<TodoViewModel> todos = new ArrayList<>();
    private SingleLiveEvent<Void> toDoListChanged = new SingleLiveEvent<>();
    private MutableLiveData<BottomMenus> currentMenu = new MutableLiveData<>(BottomMenus.ADD);

    private TodoItemsHandler toDoItemsHandler = ModelProvider.getToDoItemsHandler();

    public SingleLiveEvent<Void> getToDoListChangedLiveData() {
        return toDoListChanged;
    }

    public List<TodoViewModel> getTodoViewModels() {
        List<TodoViewModel> todoViewModels = new ArrayList<>(todos);
        return todoViewModels;
    }

    public TodoListViewModel() {
        toDoItemsHandler.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                onTodoItemHandlerPropertyChanged(sender, propertyId);
            }
        });
        UpdateTodoItemViewModels();
    }

    public LiveData<BottomMenus> getCurrentMenu() {
        return currentMenu;
    }

    public void onStop() {
    }

    public void onAddClicked(){
        addTodo();
    }

    public void onDeleteClicked(){
        ArrayList<Integer> idsToDelete = new ArrayList<>();
        for (TodoViewModel todoViewModel : todos){
            if (todoViewModel.getSelectedLiveData().getValue()) {
                idsToDelete.add(todoViewModel.getIdLiveData().getValue());
            }
        }
        for (Integer id : idsToDelete){
            toDoItemsHandler.removeTodoItem(id);
        }
        isInSelectionMode=false;
        currentMenu.setValue(BottomMenus.ADD);
    }

    public void onReminderClicked(){

    }

    public void onMoveClicked(){

    }

    private void onTodoItemHandlerPropertyChanged(Observable sender, int propertyId) {
        if (propertyId == BR.todoItems) {
            UpdateTodoItemViewModels();
        }
    }

    //Implement OnTodoItemsChanged, items that are already in the list should not be removed and added again. Remove Items that are not in the list anymore
    private void UpdateTodoItemViewModels(){

        //Add new items to the list
        for (TodoItem todoItem : toDoItemsHandler.getCurrentTodoItems()) {
            if (!CheckIsInTodoViewModelList(todoItem.getId())) {
                TodoViewModel todoViewModel= new TodoViewModel(todoItem, this);
                todoViewModel.getSelectedLiveData().observeForever(selected -> {
                    updateSelectionMode();
                });
                todos.add(todoViewModel);
            }
        }

        //Remove items that are not in the list anymore
        Iterator<TodoViewModel> iterator = todos.iterator();
        while (iterator.hasNext()) {
            TodoViewModel todoViewModel = iterator.next();
            if (!CheckIsInTodoItemList(todoViewModel.getIdLiveData().getValue())){
                iterator.remove();
            }
        }

        toDoListChanged.call();
    }

    private boolean CheckIsInTodoItemList(int id){
        for (TodoItem todoItem : toDoItemsHandler.getCurrentTodoItems()){
            if (todoItem.getId() == id){
                return true;
            }
        }
        return false;
    }

    private boolean CheckIsInTodoViewModelList(int id){
        for (TodoViewModel todoViewModel : todos){
            if (todoViewModel.getIdLiveData().getValue() == id){
                return true;
            }
        }
        return false;
    }

    int id = 0;
    private void addTodo(){
        toDoItemsHandler.createNewTodoItem(toDoItemsHandler.getCurrentTodoType());
    }

    private void updateSelectionMode(){
        isInSelectionMode = false;
        for (TodoViewModel todoViewModel : todos){
            if (todoViewModel.getSelectedLiveData().getValue()){
                isInSelectionMode = true;
                break;
            }
        }
        if (isInSelectionMode){
            currentMenu.setValue(BottomMenus.OPTIONS);
        }
        else{
            currentMenu.setValue(BottomMenus.ADD);
        }
    }

    @Override
    public boolean getIsInSelectionMode() {
        return isInSelectionMode;
    }
}
