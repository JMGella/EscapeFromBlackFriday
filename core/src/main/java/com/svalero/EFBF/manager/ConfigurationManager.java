package com.svalero.EFBF.manager;

import com.badlogic.gdx.Preferences;

import static com.svalero.EFBF.util.Constants.GAME_NAME;

public class ConfigurationManager {

    private static Preferences preferences;

    public static void loadPreferences() {
        preferences = com.badlogic.gdx.Gdx.app.getPreferences(GAME_NAME);
    }

    public static boolean isMusicEnabled(){
        return preferences.getBoolean("music", true);
    }


}
