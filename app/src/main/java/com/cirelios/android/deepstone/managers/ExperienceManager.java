package com.cirelios.android.deepstone.managers;

import com.cirelios.android.deepstone.MainActivity;

public class ExperienceManager extends MainActivity {

    public static int calcExperience(int diff, int time) {
        return (int) Math.round(Math.sqrt(diff) * time);
    }

    public static int addExperience(int addXP) {
        int addLevel = 0;
        while (addXP >= experienceBar.getMax()) {
            addXP -= experienceBar.getMax();
            experienceBar.setProgress(experienceBar.getProgress() + addXP);
            addLevel += 1;
        }
        if (addXP >= experienceBar.getMax()-experienceBar.getProgress()) {

            experienceBar.setProgress(experienceBar.getProgress() + addXP - experienceBar.getMax());
            addLevel += 1;
        } else {
            experienceBar.setProgress(experienceBar.getProgress() + addXP);
        }
        return addLevel;
    }

    public static void setExperienceBar(int level) {
        experienceBar.setMax((int) (20*Math.pow(level, 2) + 80));
        experienceLevel.setText("Level " + level);
    }

    public static void setProfileTitle(int level) {
        String title = "Novice";
        if (level >= 5 && level < 10)
            title = "Apprentice";
        if (level >= 10 && level < 15)
            title = "Initiate";
        if (level >= 15 && level < 20)
            title = "Journeyman";
        if (level >= 20 && level < 30)
            title = "Adept";
        if (level >= 30 && level < 40)
            title = "Magus";
        if (level >= 40 && level < 50)
            title = "Master";
        if (level >= 50 && level < 60)
            title = "Grandmaster";
        if (level >= 60 && level < 70)
            title = "Legendary";
        if (level >= 70 && level < 80)
            title = "Transcendent";
        if (level >= 80 && level < 90)
            title = "Archmage";
        if (level >= 90 && level < 100)
            title = "Promethean";
        if (level >= 100 && level < 110)
            title = "Exalted";
        if (level >= 110 && level < 1000)
            title = "Prodigious";
        if (level >= 1000)
            title = "Overlord";
        ds_appBar_title.setText(title);
    }
}







































