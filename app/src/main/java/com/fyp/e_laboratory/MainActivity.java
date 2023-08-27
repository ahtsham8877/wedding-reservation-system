package com.fyp.e_laboratory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.fyp.e_laboratory.Fragments.ChatFragments;
import com.fyp.e_laboratory.Fragments.SettingsFragments;
import com.fyp.e_laboratory.Fragments.ViewHotelsFragment;
import com.fyp.e_laboratory.Googlemap.MapConsole;
import com.fyp.e_laboratory.UserPanel.Instruction;
import com.fyp.e_laboratory.UserPanel.ViewBookingHistory;
import com.fyp.e_laboratory.UserPanel.ViewHotels;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomAppBar;
    Toolbar toolbar;
    DrawerLayout drawer_layout;
    NavigationView navigationView;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomAppBar =  findViewById(R.id.bottomNavigationView);
        toolbar=findViewById(R.id.toolbarid);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.nvView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.nav_open, R.string.nav_close);
        setSupportActionBar(toolbar);
        drawer_layout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        // This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view

        bottomAppBar.setOnNavigationItemSelectedListener(bottomNav);
        HomeFragment();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.mnewholtel:

                        startActivity(new Intent(MainActivity.this, ViewHotels.class));
                        //close drawer
                        drawer_layout.closeDrawer(GravityCompat.START);

                        break;

                    case R.id.mhistory:
                        //Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_SHORT).show();
                        //close drawer
                        startActivity(new Intent(MainActivity.this, ViewBookingHistory.class));
                        drawer_layout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.mheadoffice:
                       // Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                        //close drawer
                        startActivity(new Intent(MainActivity.this, MapConsole.class));
                        drawer_layout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.mhelpcenter:
                      //  Toast.makeText(getApplicationContext(),"Privacy",Toast.LENGTH_SHORT).show();
                        //close drawer
                        startActivity(new Intent(MainActivity.this, Instruction.class));
                        drawer_layout.closeDrawer(GravityCompat.START);
                        break;


                }

                return true;
            }
        });

    }
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()){
                case R.id.home:
                    fragment =new ViewHotelsFragment();
                    break;
                case R.id.chat:
                    fragment=new ChatFragments();
                    break;
                case R.id.settings:
                    fragment= new SettingsFragments();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,fragment).commit();
            return true;
        }
    };
    private void HomeFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,new ViewHotelsFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
