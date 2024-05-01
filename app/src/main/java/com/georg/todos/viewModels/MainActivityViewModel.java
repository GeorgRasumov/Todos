package com.georg.todos.viewModels;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.georg.todos.views.ListFinished;
import com.georg.todos.views.ListToday;
import com.georg.todos.views.ListTomorrow;
import com.georg.todos.views.ListTotal;
import com.georg.todos.R;
import com.georg.todos.types.SingleLiveEvent;

public class MainActivityViewModel extends ViewModel {

    private SingleLiveEvent<Void> _closeApp = new SingleLiveEvent<>();
    private MutableLiveData<Class<? extends Fragment>> _currentFragment = new MutableLiveData<Class<? extends Fragment>>();

    public LiveData<Class<? extends Fragment>> getCurrentFragmentLiveData() {return _currentFragment;}

    public SingleLiveEvent<Void> getCloseAppEvent() {
        return _closeApp;
    }

    public void onViewCreated(){
        _currentFragment.setValue(ListToday.class);
    }

    public void onOptionsItemSelected(int id){
        if (id == R.id.today) {
            _currentFragment.setValue(ListToday.class);
        } else if ((id == R.id.tomorrow)) {
            _currentFragment.setValue(ListTomorrow.class);
        } else if ((id == R.id.total)) {
            _currentFragment.setValue(ListTotal.class);
        } else if ((id == R.id.finished)) {
            _currentFragment.setValue(ListFinished.class);
        }
    }

    public void onBackPressed() {
        if(_currentFragment.getValue()!=ListToday.class){
            _currentFragment.setValue(ListToday.class);
        }
        else{
            _closeApp.call();
        }
    }
}
