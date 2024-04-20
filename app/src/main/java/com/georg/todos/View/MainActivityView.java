package com.georg.todos.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.georg.todos.R;
import com.georg.todos.ViewModel.MainActivityViewModel;
import com.georg.todos.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivityView extends AppCompatActivity {

    //Holds information on how the NavigationUI should interact with the App Bar
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new MainActivityViewModel();

        //A binding Class for each xml layout Object is automatically created. Binding classes provide direct references for views in that layout
        //getLayoutInflater returns an LayoutInflater Object which is used to instantiate XML-files to View Object
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //getRoot returns a reference to the top level View of the underlying xml file
        setContentView(binding.getRoot());

        //set a toolbar as the Action bar for this activity
        setSupportActionBar(binding.toolbar);

        //the NavController is used to Navigate through the different Screens
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //These Methods are used to make the toolbar interact with the navigation Controller
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.today) {
            navController.navigate(R.id.action_global_Today);
            return true;
        } else if ((id == R.id.tomorrow)) {
            navController.navigate(R.id.action_global_Tomorrow);
            return true;
        } else if ((id == R.id.total)) {
            navController.navigate(R.id.action_global_Total);
            return true;
        } else if ((id == R.id.finished)) {
            navController.navigate(R.id.action_global_Finished);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //alternative to NavController.navigateUp() when appBarConfiguration must be considered
        navController.navigate(R.id.action_global_Today);
        return true;
    }
}