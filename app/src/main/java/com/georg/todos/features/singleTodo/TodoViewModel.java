package com.georg.todos.features.singleTodo;

import androidx.databinding.Observable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.georg.todos.misc.SelectionStateProvider;
import com.georg.todos.misc.SingleLiveEvent;
import com.georg.todos.models.TodoItem;
import com.georg.todos.features.todoList.TodoListViewModel;

public class TodoViewModel extends ViewModel {

    private TodoListViewModel todoListViewModel;
    private MutableLiveData<String> text = new MutableLiveData<String>();
    private MutableLiveData<Integer> position = new MutableLiveData<Integer>();
    private MutableLiveData<Boolean> done = new MutableLiveData<Boolean>(false);
    private MutableLiveData<Boolean> selected = new MutableLiveData<Boolean>(false);
    private MutableLiveData<Integer> id = new MutableLiveData<Integer>();
    private SingleLiveEvent<Void> editTextEvent = new SingleLiveEvent<>();
    private TodoItem todoItem;
    private SelectionStateProvider selectionStateProvider;


    public TodoViewModel(TodoItem todoItem, SelectionStateProvider selectionStateProvider) {
        this.todoItem = todoItem;
        this.selectionStateProvider = selectionStateProvider;
        loadBaseValues();
        todoItem.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                TodoViewModel.this.onTodoItemPropertyChanged(sender, propertyId);
            }
        });
    }

    public MutableLiveData<String> getTextLiveData() {
        return text;
    }
    public MutableLiveData<Integer> getPositionLiveData() {
        return position;
    }
    public MutableLiveData<Boolean> getDoneLiveData() {
        return done;
    }
    public MutableLiveData<Boolean> getSelectedLiveData() {
        return selected;
    }
    public MutableLiveData<Integer> getIdLiveData() {
        return id;
    }
    public SingleLiveEvent<Void> getEditTextEvent() {
        return editTextEvent;
    }


    public boolean onTextClicked() {
        if (selectionStateProvider.getIsInSelectionMode()) {
            selected.setValue(!selected.getValue());
        }
        else {
            todoItem.setDone(!todoItem.isDone());
            return true;
        }
        return true;
    }

    public boolean onTextLongClicked(){
        selected.setValue(!selected.getValue());
        return true;
    }

    public boolean onEditClicked() {
        editTextEvent.call();
        return true;
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Update the text LiveData with the new text
        todoItem.setText(s.toString());
    }

    private void onTodoItemPropertyChanged(Observable sender, int propertyId) {
        loadBaseValues();
    }

    private void loadBaseValues(){
        text.setValue(todoItem.getText());
        done.setValue(todoItem.isDone());
        id.setValue(todoItem.getId());
    }
}
