package com.georg.todos.views;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.georg.todos.R;
import com.georg.todos.viewModels.MainActivityViewModel;
import com.georg.todos.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.Map;

public class MainActivityView extends AppCompatActivity {

    private Map<Class, String> titleMap = new HashMap<Class, String>() {{
        put(ListToday.class, "Heute");
        put(ListTomorrow.class, "Morgen");
        put(ListTotal.class, "Gesamt");
        put(ListFinished.class, "Erldeigt");
    }};

    //Holds information on how the NavigationUI should interact with the App Bar
    private ActivityMainBinding binding;
    //private NavController navController;
    private MainActivityViewModel viewModel;

    private void setFragment(Class<? extends Fragment> fragmentclass){
        String title = titleMap.get(fragmentclass);
        getSupportActionBar().setTitle(title);
        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), fragmentclass, null).commit();
    }

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
        viewModel.getCloseAppEvent().observe(this, v -> finish());

        viewModel.onViewCreated();

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
}