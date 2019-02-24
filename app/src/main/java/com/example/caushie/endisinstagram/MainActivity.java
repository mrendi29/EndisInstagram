package com.example.caushie.endisinstagram;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.caushie.endisinstagram.fragments.ComposeFragment;
import com.example.caushie.endisinstagram.fragments.PostsFragment;
import com.example.caushie.endisinstagram.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;



    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment composeFragment = new ComposeFragment();
        final Fragment postsFragment = new PostsFragment();
        final Fragment profileFragment = new ProfileFragment();


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Acativity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.mipmap.nav_logo_whiteout1);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);


        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //Add onclick listener in order to determine which fragment to show
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment active;
                switch (item.getItemId()) {

                    case R.id.action_compose:
                        //TODO: Swap fragment
                        active = composeFragment;
                        //Toast.makeText(MainActivity.this, "Action Compose", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_home:

                        active = postsFragment;
                        //  Toast.makeText(MainActivity.this, "Action home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                        active = profileFragment;
                        break;
//                        Toast.makeText(MainActivity.this, "Action profile", Toast.LENGTH_SHORT).show();
                    default:
                        active = postsFragment;
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, active).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);

    }


}
