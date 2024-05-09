package com.georg.todos.features.mainActivity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.georg.todos.R;
import com.georg.todos.models.ModelProvider;
import com.georg.todos.databinding.ActivityMainBinding;
import com.georg.todos.types.TodoTypes;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivityView extends AppCompatActivity {

    private Map<TodoTypes, String> titleMap = new HashMap<TodoTypes, String>() {{
        put(TodoTypes.TODAY, "Heute");
        put(TodoTypes.TOMORROW, "Morgen");
        put(TodoTypes.TOTAL,  "Gesamt");
        put(TodoTypes.FINISHED, "Erldeigt");
    }};

    //Holds information on how the NavigationUI should interact with the App Bar
    private ActivityMainBinding binding;
    //private NavController navController;
    private MainActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(MainActivityViewModel.class);
        //A binding Class for each xml layout Object is automatically created. Binding classes provide direct references for views in that layout
        //getLayoutInflater returns an LayoutInflater Object which is used to instantiate XML-files to View Object
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //getRoot returns a reference to the top level View of the underlying xml file
        setContentView(binding.getRoot());

        //set a toolbar as the Action bar for this activity
        setSupportActionBar(binding.toolbar);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled */) {
            @Override
            public void handleOnBackPressed() {
                viewModel.onBackPressed();
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);


        viewModel.getCurrentFragmentLiveData().observe(this, this::setFragment);
        viewModel.getCurrentTitleLiveData().observe(this, this::updateTitile);
        viewModel.getCloseAppEvent().observe(this, v -> finish());

        viewModel.onViewCreated(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        viewModel.onOptionsItemSelected(id);

        return super.onOptionsItemSelected(item);
    }

    //Hide the keyboard when the user clicks outside of an EditText
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        viewModel.onDestroy();
    }

    private void setFragment(Class<? extends Fragment> fragmentclass){
        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), fragmentclass, null).commit();
    }

    private void updateTitile(String title){
        getSupportActionBar().setTitle(title);
    }

}