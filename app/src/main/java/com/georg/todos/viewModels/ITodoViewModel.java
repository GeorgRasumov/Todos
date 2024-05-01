package com.georg.todos.viewModels;

import androidx.lifecycle.MutableLiveData;

public interface ITodoViewModel {

    public MutableLiveData<Integer> getIdLiveData();
    public MutableLiveData<String> getTextLiveData();

    public MutableLiveData<Integer> getPositionLiveData();

    public MutableLiveData<Boolean> getDoneLiveData();

    public MutableLiveData<Boolean> getSelectedLiveData();

    public boolean onTextClicked();
    public boolean onTextLongClicked();

    public boolean onEditClicked();



}
