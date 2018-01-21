package com.cirelios.android.deepstone;

import com.cirelios.android.deepstone.category.CategoryStruct;
import com.cirelios.android.deepstone.task.TaskStruct;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class Utils {

    public static final ImmutableMap<String, Integer> COLORS = ImmutableMap.<String, Integer>builder()
            .put("light red", R.color.Scarlet)
            .put("dark red", R.color.Firebrick)
            .put("orange", R.color.PumpkinOrange)
            .put("yellow", R.color.RubberDuckyYellow)
            .put("light green", R.color.NebulaGreen)
            .put("dark green", R.color.DarkSeaGreen)
            .put("light blue", R.color.CyanAqua)
            .put("dark blue", R.color.MidnightBlue)
            .put("light purple", R.color.HeliotropePurple)
            .put("dark purple", R.color.PurpleIris)
            .put("pink", R.color.DeepPink)
            .put("brown", R.color.Mocha)
            .put("white", R.color.Platinum)
            .put("light gray", R.color.DimGray)
            .put("dark gray", R.color.Smoke)
            .put("black", R.color.Night)
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

    public static int calculateXP(int diff, int time) {
        return (int) Math.round(Math.sqrt(diff) * time);
    }

    public static List<TaskStruct> getSortedTasks() {
        return Lists.newArrayList(TASKS.values());
        /*return TASKS.values().stream()
                .sorted(Comparator.comparing(o -> o.Experience))
                .collect(Collectors.toList());*/
    }

    public static List<CategoryStruct> getSortedCategories() {
        return Lists.newArrayList(CATEGORIES.values());
        /*return CATEGORIES.values().stream()
                .sorted(Comparator.comparing(o -> o.Name))
                .collect(Collectors.toList());*/
    }

}