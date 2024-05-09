package com.georg.todos.features.mainActivity;

import android.content.Context;

import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.georg.todos.BR;
import com.georg.todos.R;
import com.georg.todos.features.todoList.views.ToDoListView;
import com.georg.todos.misc.SingleLiveEvent;
import com.georg.todos.models.MainModel;
import com.georg.todos.models.ModelProvider;
import com.georg.todos.types.TodoTypes;

import java.util.HashMap;
import java.util.Map;

public class MainActivityViewModel extends ViewModel {

    private Map<TodoTypes, String> titleMap = new HashMap<TodoTypes, String>() {{
        put(TodoTypes.TODAY, "Heute");
        put(TodoTypes.TOMORROW, "Morgen");
        put(TodoTypes.TOTAL,  "Gesamt");
        put(TodoTypes.FINISHED, "Erldeigt");
    }};
    private MainModel mainModel;
    private SingleLiveEvent<Void> closeApp = new SingleLiveEvent<>();
    private MutableLiveData<Class<? extends Fragment>> currentFragment = new MutableLiveData<Class<? extends Fragment>>();
    private MutableLiveData<String> currentTitle = new MutableLiveData<>();
    public LiveData<Class<? extends Fragment>> getCurrentFragmentLiveData() {return currentFragment;}
    public LiveData<String> getCurrentTitleLiveData() {return currentTitle;}


    public void onViewCreated(Context context){
        currentFragment.setValue(ToDoListView.class);
        mainModel = ModelProvider.getMainModel();
        mainModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                onMainModelPropertyChanged(propertyId);
            }
        });
        mainModel.onCreate(context);
        mainModel.setCurrentTodoType(TodoTypes.TODAY);
    }

    public SingleLiveEvent<Void> getCloseAppEvent() {
        return closeApp;
    }

    public void onOptionsItemSelected(int id){
        if (id == R.id.today) {
            mainModel.setCurrentTodoType(TodoTypes.TODAY);
        } else if ((id == R.id.tomorrow)) {
            mainModel.setCurrentTodoType(TodoTypes.TOMORROW);
        } else if ((id == R.id.total)) {
            mainModel.setCurrentTodoType(TodoTypes.TOTAL);
        } else if ((id == R.id.finished)) {
            mainModel.setCurrentTodoType(TodoTypes.FINISHED);
        }
    }
    public void onBackPressed() {
        mainModel.goBack();
    }

    public void onDestroy(){
        mainModel.onDestroy();
    }

    public void onCreate(Context context){
        mainModel.onCreate(context);
    }

    private void onMainModelPropertyChanged(int propertyId){
        if(propertyId == BR.currentTodoType){
            currentTitle.setValue(titleMap.get(mainModel.getCurrentTodoType()));
        }
        else if(propertyId == BR.closeApp){
            if (mainModel.getCloseApp()){
                closeApp.call();
            }
        }
    }
}
