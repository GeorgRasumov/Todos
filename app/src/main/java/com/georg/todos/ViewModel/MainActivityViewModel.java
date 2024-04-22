package com.georg.todos.ViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.georg.todos.View.ListFinished;
import com.georg.todos.View.ListToday;
import com.georg.todos.View.ListTomorrow;
import com.georg.todos.View.ListTotal;
import com.georg.todos.R;
import com.georg.todos.types.SingleLiveEvent;

public class MainActivityViewModel extends ViewModel {

    private SingleLiveEvent<Void> closeApp = new SingleLiveEvent<>();
    private MutableLiveData<Class<? extends Fragment>> currentFrag = new MutableLiveData<Class<? extends Fragment>>();

    public LiveData<Class<? extends Fragment>> getCurrentFrag() {
        return currentFrag;
    }

    public SingleLiveEvent<Void> getCloseApp() {
        return closeApp;
    }

    public void onViewCreated(){
        changeFragment(ListToday.class);
    }

    public void onOptionsItemSelected(int id){
        if (id == R.id.today) {
            changeFragment(ListToday.class);
        } else if ((id == R.id.tomorrow)) {
            changeFragment(ListTomorrow.class);
        } else if ((id == R.id.total)) {
            changeFragment(ListTotal.class);
        } else if ((id == R.id.finished)) {
            changeFragment(ListFinished.class);
        }
    }

    public void onBackPressed() {
        if(currentFrag.getValue()!=ListToday.class){
            changeFragment(ListToday.class);
        }
        else{
            closeApp.call();
        }
    }

    private void changeFragment(Class<? extends Fragment> fragment){
        currentFrag.setValue(fragment);
    }
}
