package com.cirelios.android.deepstone.category;

import com.cirelios.android.deepstone.R;

public class CategoryStruct {

    public String Name;
    public String Description;
    public int Color;
    public int Icon;

    public static int getColorID(String colorName) {
        switch (colorName.toLowerCase()) {
            case "light red":
                return R.color.Scarlet;
            case "dark red":
                return R.color.Firebrick;
            case "orange":
                return R.color.PumpkinOrange;
            case "yellow":
                return R.color.RubberDuckyYellow;
            case "light green":
                return R.color.NebulaGreen;
            case "dark green":
                return R.color.DarkSeaGreen;
            case "light blue":
                return R.color.CyanAqua;
            case "dark blue":
                return R.color.MidnightBlue;
            case "light purple":
                return R.color.HeliotropePurple;
            case "dark purple":
                return R.color.PurpleIris;
            case "pink":
                return R.color.DeepPink;
            case "brown":
                return R.color.Mocha;
            case "white":
                return R.color.Platinum;
            case "light gray":
                return R.color.DimGray;
            case "dark gray":
                return R.color.Smoke;
            case "black":
                return R.color.Night;
            default:
                return R.color.EerieBlack;
        }
    }

    public static int getIconID(String iconName) {
        switch (iconName.toLowerCase()) {
            case "biohazard":
                return R.drawable.biohazard;
            default:
                System.out.println("Something else");
                return R.drawable.alert;
        }
    }

}