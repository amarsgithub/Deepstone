package com.cirelios.android.deepstone;

import android.view.View;

import com.cirelios.android.deepstone.category.CategoryStruct;
import com.cirelios.android.deepstone.task.TaskStruct;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Utils {

    public static final ImmutableMap<String, Integer> COLORS = ImmutableMap.<String, Integer>builder()
            .put("Light Red", R.color.Scarlet)
            .put("Dark Red", R.color.Firebrick)
            .put("Orange", R.color.PumpkinOrange)
            .put("Yellow", R.color.RubberDuckyYellow)
            .put("Light Green", R.color.NebulaGreen)
            .put("Dark Green", R.color.DarkSeaGreen)
            .put("Light Blue", R.color.CyanAqua)
            .put("Dark Blue", R.color.MidnightBlue)
            .put("Light Purple", R.color.HeliotropePurple)
            .put("Dark Purple", R.color.PurpleIris)
            .put("Pink", R.color.DeepPink)
            .put("Brown", R.color.Mocha)
            .put("White", R.color.Platinum)
            .put("Light Gray", R.color.DimGray)
            .put("Dark Gray", R.color.Smoke)
            .put("Black", R.color.Night)
            .build();

    public static final ImmutableMap<String, Integer> ICONS = ImmutableMap.<String, Integer>builder()
            .put("Biohazard", R.drawable.biohazard)
            .put("Bone", R.drawable.bone)
            .put("Open Book", R.drawable.book_open_page_variant)
            .put("Brush", R.drawable.brush)
            .put("Calculator", R.drawable.calculator)
            .put("Graph", R.drawable.chart_bar)
            .put("Euro", R.drawable.currency_eur)
            .put("Pound", R.drawable.currency_gbp)
            .put("Dollar", R.drawable.currency_usd)
            .put("Shapes", R.drawable.drawing)
            .put("Basketball", R.drawable.dribble)
            .put("Dumbbell", R.drawable.dumbbell)
            .put("Earth", R.drawable.earth)
            .put("Fan", R.drawable.fan)
            .put("Flash", R.drawable.flask)
            .put("Apple", R.drawable.food_apple)
            .put("Football", R.drawable.football)
            .put("Helmet", R.drawable.football_helmet)
            .put("Exponent", R.drawable.format_superscript)
            .put("Function", R.drawable.function)
            .put("Controller", R.drawable.gamepad_variant)
            .put("Gavel", R.drawable.gavel)
            .put("Heart", R.drawable.heart)
            .put("House", R.drawable.home_variant)
            .put("Infinity", R.drawable.infinity)
            .put("Magnet", R.drawable.magnet_on)
            .put("Compass", R.drawable.math_compass)
            .put("Matrix", R.drawable.matrix)
            .put("Computer Chip", R.drawable.memory)
            .put("Microscope", R.drawable.microscope)
            .put("Music Note", R.drawable.music_note)
            .put("Paw", R.drawable.paw)
            .put("Pencil", R.drawable.pencil)
            .put("Piano", R.drawable.piano)
            .put("Pokeball", R.drawable.pokeball)
            .put("Radioactive", R.drawable.radioactive)
            .put("School", R.drawable.school)
            .put("Gear", R.drawable.settings)
            .put("Stack", R.drawable.buffer)
            .put("Soccer Ball", R.drawable.soccer)
            .put("Sword", R.drawable.sword)
            .put("Tennis", R.drawable.tennis)
            .put("Test Tube", R.drawable.test_tube)
            .put("Language", R.drawable.translate)
            .put("Violin", R.drawable.violin)
            .put("Wrench", R.drawable.wrench)
            .put("X-Box Controller", R.drawable.xbox_controller)
            .build();

    public static final Map<String, TaskStruct> TASKS = Maps.newHashMap();
    public static final Map<String, CategoryStruct> CATEGORIES = Maps.newHashMap();
    public static final Map<Integer, Integer> CALENDAR = Maps.newHashMap();

    public static int calculateXP(int diff, int time) {
        return (int) Math.round(Math.sqrt(diff) * time);
    }

    public static String getTitle(int level) {
        if (level < 5)
            return "Novice";
        if (level >= 5 && level < 10)
            return "Apprentice";
        if (level >= 10 && level < 15)
            return "Initiate";
        if (level >= 15 && level < 20)
            return "Journeyman";
        if (level >= 20 && level < 30)
            return "Adept";
        if (level >= 30 && level < 40)
            return "Magus";
        if (level >= 40 && level < 50)
            return "Master";
        if (level >= 50 && level < 60)
            return "Grandmaster";
        if (level >= 60 && level < 70)
            return "Legendary";
        if (level >= 70 && level < 80)
            return "Transcendent";
        if (level >= 80 && level < 90)
            return "Archmage";
        if (level >= 90 && level < 100)
            return "Promethean";
        if (level >= 100 && level < 110)
            return "Exalted";
        if (level >= 110 && level < 1000)
            return "Prodigious";
        return "Overlord";
    }

    public static List<TaskStruct> getSortedTasks() {
        List<TaskStruct> tasks = Lists.newArrayList(TASKS.values());
        Collections.sort(tasks, new Comparator<TaskStruct>() {
            @Override
            public int compare(TaskStruct t1, TaskStruct t2) {
                boolean o1 = t1.DueDate - System.currentTimeMillis() < 0;
                boolean o2 = t2.DueDate - System.currentTimeMillis() < 0;
                return o1 ? o2 ? Integer.compare(t2.Experience, t1.Experience) : -1 : o2 ? 1 : Integer.compare(t2.Experience, t1.Experience);
            }
        });
        return tasks;
    }

    public static List<CategoryStruct> getSortedCategories() {
        List<CategoryStruct> Categories = Lists.newArrayList(CATEGORIES.values());
        Collections.sort(Categories, new Comparator<CategoryStruct>() {
            @Override
            public int compare(CategoryStruct c1, CategoryStruct c2) {
                return c2.Name.compareTo(c1.Name);
            }
        });
        return Categories;
    }

    public static void initializeDefaults() {
        CategoryStruct art = createCategory("Art", R.color.DeepPink, R.drawable.brush);
        CategoryStruct calculus = createCategory("Calculus", R.color.HeliotropePurple, R.drawable.matrix);
        CategoryStruct chemistry = createCategory("Chemistry", R.color.NebulaGreen, R.drawable.flask);
        CategoryStruct history = createCategory("History", R.color.Night, R.drawable.earth);
        CategoryStruct english = createCategory("English", R.color.Yellow, R.drawable.book_open_page_variant);
        CategoryStruct physics = createCategory("Physics", R.color.Firebrick, R.drawable.magnet_on);
        CategoryStruct programming = createCategory("Programming", R.color.PumpkinOrange, R.drawable.buffer);
        CategoryStruct swamphacks = createCategory("SwampHacks", R.color.AndroidBlue, R.drawable.alert);
        createTask(art, "Tesselation", 1516548600000L, 7200, 230);
        createTask(calculus, "WebAssign #3", 1516942799000L, 10800, 560);
        createTask(calculus, "Study Group", 1516797000000L, 3600, 110);
        createTask(chemistry, "Titration Lab", 1516643400000L, 12600, 740);
        createTask(physics, "Mechanics HW", 1516894800000L, 5400, 400);
        createTask(programming, "Unity Lessons", 1517350200000L, 25200, 1280);
        createTask(swamphacks, "Time Controller", 1516548600000L, 129600, 9001);
        for (TaskStruct task : TASKS.values()) {
            long time = task.DueDate - System.currentTimeMillis();
            System.out.println(task.Name + ": (" + time + ") --" + task.DueDate + " vs " + System.currentTimeMillis());
        }
    }

    private static void createTask(CategoryStruct category, String name, long dueDate, int time, int experience) {
        TaskStruct task = new TaskStruct();
        task.Category = category;
        task.Name = name;
        task.DueDate = dueDate;
        task.Time = time;
        task.Experience = experience;
        TASKS.put(name, task);
    }

    private static CategoryStruct createCategory(String name, int color, int icon) {
        CategoryStruct category = new CategoryStruct();
        category.Name = name;
        category.Color = color;
        category.Icon = icon;
        CATEGORIES.put(name, category);
        return category;
    }

    private static void storeInDatabase() {
        Connection c = null;
        Statement stmt = null;
        String sql = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SQLite.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            //	below code checks if a table exists. If it doesn't, it creates the table.
            DatabaseMetaData md = c.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            stmt = c.createStatement();
            if (!rs.next()) {
                sql = "CREATE TABLE EVENT (ID INTEGER PRIMARY KEY AUTOINCREMENT, MONTH INT, " +
                        "YEAR INT, YEAR HOUR, HOUR INT, MINUTE INT, EVENT STRING, XP INT)"
                        + " GUESS NOT NULL)";
                stmt.executeUpdate(sql);
            }

            c.commit();
            rs.close();

            sql = "INSERT INTO EVENT (DAY, MONTH, YEAR, HOUR, MINUTE, EVENT, XP) "
                    + "VALUES (21, 1, 2018, 2, 0, 'SwampHacks', 250);";

            stmt.executeUpdate(sql);

            System.out.println("Stored value in the database");

            ResultSet rs2 = stmt.executeQuery("SELECT * FROM GUESS;");

            while (rs2.next()) {
                int id = rs2.getInt("id");
                String name = rs2.getString("FIRSTNAME");
                String nickname = rs2.getString("NICKNAME");
                int userGuess = rs2.getInt("GUESS");

                System.out.println("Id = " + id);
                System.out.println("Name = " + name);
                System.out.println("Nickname = " + nickname);
                System.out.println("Age = " + userGuess);

            }

            rs2.close();

            c.commit();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
            e.printStackTrace();
        }

    }

}