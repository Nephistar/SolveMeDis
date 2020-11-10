package com.example.solvemedis;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.solvemedis.ui.home.HomeFragment;
import com.example.solvemedis.ui.home.HomeViewModel;
import com.example.solvemedis.ui.notifications.*;
import com.example.solvemedis.ui.dashboard.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private Button start_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        final AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_easy, R.id.navigation_normal, R.id.navigation_hard)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        start_selected = (Button) findViewById(R.id.start_selected);
        start_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openEasyMode(view);
                int selected_id = navView.getSelectedItemId();
                chooseDestination(selected_id, view);

            }
        });
    }

    public void chooseDestination(int selected_id, View view){
        if(selected_id == (R.id.navigation_easy)){
            openEasyMode(view);
        }
            else if(selected_id == (R.id.navigation_normal)){
            openNormalMode(view);
        }
            else if(selected_id == (R.id.navigation_hard)){
            openHardMode(view);
        }
            else{
                    //ERROR WITH DESTINATION -> Do nothing?
        }
    }

    public void openEasyMode(View view){
        Intent intent_easy = new Intent(this, Math_Easy.class);
        startActivity(intent_easy);
    }

    public void openNormalMode(View view){
        Intent intent_normal = new Intent(this, Math_Normal.class);
        startActivity(intent_normal);

    }

    public void openHardMode(View view){
        Intent intent_hard = new Intent(this, Math_Hard.class);
        startActivity(intent_hard);
    }
}