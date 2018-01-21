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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cirelios.android.deepstone.category.CategoriesFragment;
import com.cirelios.android.deepstone.category.CreateCategoryFragment;
import com.cirelios.android.deepstone.fragments.CalendarFragment;
import com.cirelios.android.deepstone.fragments.HomeFragment;
import com.cirelios.android.deepstone.fragments.ProfileFragment;
import com.cirelios.android.deepstone.fragments.SettingsFragment;
import com.cirelios.android.deepstone.fragments.SkillsFragment;
import com.cirelios.android.deepstone.managers.CategoriesManager;
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
                if (CategoriesManager.getCategoriesList().isEmpty()) {
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

        if (id == R.id.ds_overlay_assignments) {
            sendToFragment(new TasksFragment());
        } else if (id == R.id.ds_overlay_calendar) {
            sendToFragment(new CalendarFragment());
        } else if (id == R.id.ds_overlay_classes) {
            sendToFragment(new CategoriesFragment());
        } else if (id == R.id.ds_overlay_skills) {
            sendToFragment(new SkillsFragment());
        } else if (id == R.id.ds_overlay_profile) {
            sendToFragment(new ProfileFragment());
        } else if (id == R.id.ds_overlay_settings) {
            sendToFragment(new SettingsFragment());
        } else if (id == R.id.ds_overlay_home) {
            sendToFragment(new HomeFragment());
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
}