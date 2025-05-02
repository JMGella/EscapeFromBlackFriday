package com.svalero.EFBF.manager;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;

import static com.svalero.EFBF.util.Constants.GAME_NAME;

public class ConfigurationManager {

    private static Preferences preferences;

    public static void loadPreferences() {
        preferences = com.badlogic.gdx.Gdx.app.getPreferences(GAME_NAME);
    }

    public static boolean isMusicEnabled(){
        return preferences.getBoolean("music", true);
    }

    public static boolean isSoundEnabled(){
        return preferences.getBoolean("sound", true);
    }

    public static float getSoundVolume(){
        return preferences.getFloat("volume", 1.0f) / 100;
    }


}
