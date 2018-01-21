package com.cirelios.android.deepstone;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
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
    public static boolean activity1 = false;
    public static boolean activity2 = false;
    public static boolean activity3 = false;
    public static boolean activity4 = false;
    public static boolean activity5 = false;


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

    public void day1(View view) {
        System.out.println("Day 1 was clicked");
    }

    public void day2(View view) {
        System.out.println("Day 2 was clicked");
    }

    public void day3(View view) {
        System.out.println("Day 3 was clicked");
    }

    public void day4(View view) {
        System.out.println("Day 4 was clicked");
    }

    public void day5(View view) {
        System.out.println("Day 5 was clicked");
    }

    public void day6(View view) {
        System.out.println("Day 6 was clicked");
    }

    public void day7(View view) {
        System.out.println("Day 7 was clicked");
    }

    public void day8(View view) {
        System.out.println("Day 8 was clicked");
    }

    public void day9(View view) {
        System.out.println("Day 9 was clicked");
    }

    public void day10(View view) {
        System.out.println("Day 10 was clicked");
    }

    public void day11(View view) {
        System.out.println("Day 11 was clicked");
    }

    public void day12(View view) {
        System.out.println("Day 12 was clicked");
    }

    public void day13(View view) {
        System.out.println("Day 13 was clicked");
    }

    public void day14(View view) {
        System.out.println("Day 14 was clicked");
    }

    public void day15(View view) {
        System.out.println("Day 15 was clicked");
    }

    public void day16(View view) {
        System.out.println("Day 16 was clicked");
    }

    public void day17(View view) {
        System.out.println("Day 17 was clicked");
    }

    public void day18(View view) {
        System.out.println("Day 18 was clicked");
    }

    public void day19(View view) {
        System.out.println("Day 19 was clicked");
    }

    public void day20(View view) {
        System.out.println("Day 20 was clicked");
    }

    public void day21(View view) {
        System.out.println("Day 21 was clicked");
    }

    public void day22(View view) {
        System.out.println("Day 22 was clicked");
    }

    public void day23(View view) {
        System.out.println("Day 23 was clicked");
    }

    public void day24(View view) {
        System.out.println("Day 24 was clicked");
    }

    public void day25(View view) {
        System.out.println("Day 25 was clicked");
    }

    public void day26(View view) {
        System.out.println("Day 26 was clicked");
    }

    public void day27(View view) {
        System.out.println("Day 10 was clicked");
    }

    public void day28(View view) {
        System.out.println("Day 28 was clicked");
    }

    public void day29(View view) {
        System.out.println("Day 29 was clicked");
    }

    public void day30(View view) {
        System.out.println("Day 30 was clicked");
    }

    public void day31(View view) {
        System.out.println("Day 31 was clicked");
    }

    public void day311(View view) {
        System.out.println("Day 31 was clicked");
    }

    public void day122(View view) {
        System.out.println("Day 1 was selected");
    }

    public void day222(View view) {
        System.out.println("Day 2 was selected");
    }

    public void day322(View view) {
        System.out.println("Day 3 was selected");
    }


    public void activity1(View view) {
        Button activity1Button = (Button) findViewById(R.id.activity1);
        Button day17 = (Button) findViewById(R.id.day17);
        Button day19 = (Button) findViewById(R.id.day19);
        if (activity1) { //if the color is red and it is selected (want to unselect here)
            activity1Button.setTextColor(getResources().getColor(R.color.MidnightBlue));
            day17.setBackgroundResource(R.drawable.button_border);
            day19.setBackgroundResource(R.drawable.button_border);
            activity1 = false;
        } else { //if the color is blue (want to select calendar days here)
            activity1Button.setTextColor(getResources().getColor(R.color.RedWine));
            day17.setBackgroundResource(R.drawable.button_border3);
            day19.setBackgroundResource(R.drawable.button_border3);
            activity1 = true;
        }
    }

    public void activity2(View view) {
        Button activity2Button = (Button) findViewById(R.id.activity2);
        Button day19 = (Button) findViewById(R.id.day19);
        Button day20 = (Button) findViewById(R.id.day20);
        Button day21 = (Button) findViewById(R.id.day21);

        if (activity2) { //if the color is red and it is selected (want to unselect here)
            activity2Button.setTextColor(getResources().getColor(R.color.MidnightBlue));
            day19.setBackgroundResource(R.drawable.button_border);
            day20.setBackgroundResource(R.drawable.button_border);
            day21.setBackgroundResource(R.drawable.button_border);
            activity2 = false;
        } else { //if the color is blue (want to select calendar days here)
            activity2Button.setTextColor(getResources().getColor(R.color.RedWine));
            day19.setBackgroundResource(R.drawable.button_border3);
            day20.setBackgroundResource(R.drawable.button_border3);
            day21.setBackgroundResource(R.drawable.button_border3);
            activity2 = true;
        }

    }

    public void activity3(View view) {
        Button activity3Button = (Button) findViewById(R.id.activity3);
        Button day1 = (Button) findViewById(R.id.day1);
        Button day5 = (Button) findViewById(R.id.day5);
        Button day8 = (Button) findViewById(R.id.day8);
        Button day12 = (Button) findViewById(R.id.day12);
        Button day15 = (Button) findViewById(R.id.day15);
        Button day19 = (Button) findViewById(R.id.day19);
        Button day22 = (Button) findViewById(R.id.day22);
        Button day26 = (Button) findViewById(R.id.day26);
        Button day29 = (Button) findViewById(R.id.day29);
        if (activity3) { //if the color is red and it is selected (want to unselect here)
            activity3Button.setTextColor(getResources().getColor(R.color.MidnightBlue));
            day1.setBackgroundResource(R.drawable.button_border);
            day5.setBackgroundResource(R.drawable.button_border);
            day8.setBackgroundResource(R.drawable.button_border);
            day12.setBackgroundResource(R.drawable.button_border);
            day15.setBackgroundResource(R.drawable.button_border);
            day19.setBackgroundResource(R.drawable.button_border);
            day22.setBackgroundResource(R.drawable.button_border);
            day26.setBackgroundResource(R.drawable.button_border);
            day29.setBackgroundResource(R.drawable.button_border);
            activity3 = false;
        } else { //if the color is blue (want to select calendar days here)
            activity3Button.setTextColor(getResources().getColor(R.color.RedWine));
            day1.setBackgroundResource(R.drawable.button_border3);
            day5.setBackgroundResource(R.drawable.button_border3);
            day8.setBackgroundResource(R.drawable.button_border3);
            day12.setBackgroundResource(R.drawable.button_border3);
            day15.setBackgroundResource(R.drawable.button_border3);
            day19.setBackgroundResource(R.drawable.button_border3);
            day22.setBackgroundResource(R.drawable.button_border3);
            day26.setBackgroundResource(R.drawable.button_border3);
            day29.setBackgroundResource(R.drawable.button_border3);
            activity3 = true;
        }
    }

    public void activity4(View view) {
        Button activity4Button = (Button) findViewById(R.id.activity4);
        Button day1 = (Button) findViewById(R.id.day1);
        Button day12 = (Button) findViewById(R.id.day12);
        Button day26 = (Button) findViewById(R.id.day26);

        if (activity4) { //if the color is red and it is selected (want to unselect here)
            activity4Button.setTextColor(getResources().getColor(R.color.MidnightBlue));
            day1.setBackgroundResource(R.drawable.button_border);
            day12.setBackgroundResource(R.drawable.button_border);
            day26.setBackgroundResource(R.drawable.button_border);
            activity4 = false;
        } else { //if the color is blue (want to select calendar days here)
            activity4Button.setTextColor(getResources().getColor(R.color.RedWine));
            day1.setBackgroundResource(R.drawable.button_border3);
            day12.setBackgroundResource(R.drawable.button_border3);
            day26.setBackgroundResource(R.drawable.button_border3);
            activity4 = true;
        }

    }

    public void activity5(View view) {
        Button activity5Button = (Button) findViewById(R.id.activity5);
        Button day4 = (Button) findViewById(R.id.day4);
        Button day11 = (Button) findViewById(R.id.day11);
        Button day18 = (Button) findViewById(R.id.day18);
        Button day25 = (Button) findViewById(R.id.day25);

        if (activity5) { //if the color is red and it is selected (want to unselect here)
            activity5Button.setTextColor(getResources().getColor(R.color.MidnightBlue));
            day4.setBackgroundResource(R.drawable.button_border);
            day11.setBackgroundResource(R.drawable.button_border);
            day18.setBackgroundResource(R.drawable.button_border);
            day25.setBackgroundResource(R.drawable.button_border);
            activity5 = false;
        } else { //if the color is blue (want to select calendar days here)
            activity5Button.setTextColor(getResources().getColor(R.color.RedWine));
            day4.setBackgroundResource(R.drawable.button_border3);
            day11.setBackgroundResource(R.drawable.button_border3);
            day18.setBackgroundResource(R.drawable.button_border3);
            day25.setBackgroundResource(R.drawable.button_border3);
            activity5 = true;
        }

    }


}