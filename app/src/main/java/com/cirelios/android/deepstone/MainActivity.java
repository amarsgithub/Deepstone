package com.cirelios.android.deepstone;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cirelios.android.deepstone.category.CategoriesFragment;
import com.cirelios.android.deepstone.category.CreateCategoryFragment;
import com.cirelios.android.deepstone.fragments.CalendarFragment;
import com.cirelios.android.deepstone.fragments.HomeFragment;
import com.cirelios.android.deepstone.fragments.SettingsFragment;
import com.cirelios.android.deepstone.task.CreateTaskFragment;
import com.cirelios.android.deepstone.task.TasksFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView = null;
    private Toolbar toolbar = null;
    public static ProgressBar experienceBar = null;
    public static TextView experienceLevel = null;
    public static TextView ds_appBar_title = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);

        sendToFragment(new HomeFragment());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.create_task_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Debug");
                if (Utils.CATEGORIES.isEmpty()) {
                    new AlertDialog.Builder(MainActivity.this, R.style.AppTheme_NoActionBar)
                            .setMessage("You must have an existing category to create an Assignment!\n\nCreate a new category?\n")
                            .setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getSupportFragmentManager().beginTransaction()
                                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                                            .replace(R.id.fragment_container, new CreateCategoryFragment())
                                            .commit();
                                }
                            })
                            .setNegativeButton("No.", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            .create().show();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                            .replace(R.id.fragment_container, new CreateTaskFragment())
                            .commit();
                }
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        experienceBar = findViewById(R.id.experienceBar);
        experienceLevel = findViewById(R.id.ds_appBar_level);
        ds_appBar_title = findViewById(R.id.ds_appBar_title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ds_overlay_home) {
            Utils.initializeDefaults();
            sendToFragment(new HomeFragment());
        } else if (id == R.id.ds_overlay_tasks) {
            sendToFragment(new TasksFragment());
        } else if (id == R.id.ds_overlay_calendar) {
            sendToFragment(new CalendarFragment());
        } else if (id == R.id.ds_overlay_categories) {
            sendToFragment(new CategoriesFragment());
        } else if (id == R.id.ds_overlay_skills) {
            //sendToFragment(new SkillsFragment());
            Toast.makeText(getApplicationContext(), "The skills page is still in development.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.ds_overlay_profile) {
            //sendToFragment(new ProfileFragment());
            Toast.makeText(getApplicationContext(), "The profile page is still in development.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.ds_overlay_settings) {
            sendToFragment(new SettingsFragment());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void sendToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public static int addExperience(int addXP) {
        int level = Integer.parseInt(experienceLevel.getText().toString().substring(6));
        int addLevel = 0;
        while (addXP >= experienceBar.getMax()) {
            addXP -= experienceBar.getMax();
            experienceBar.setProgress(experienceBar.getProgress() + addXP);
            levelUp(++addLevel + level);
        }
        if (addXP >= experienceBar.getMax()-experienceBar.getProgress()) {
            experienceBar.setProgress(experienceBar.getProgress() + addXP - experienceBar.getMax());
            levelUp(++addLevel + level);
        } else {
            experienceBar.setProgress(experienceBar.getProgress() + addXP);
        }
        return addLevel != 0 ? level : 0;
    }

    public static void levelUp(int level) {
        experienceBar.setMax((int) (20*Math.pow(level, 2) + 80));
        experienceLevel.setText("Level " + level);
        ds_appBar_title.setText(Utils.getTitle(level));
    }

}