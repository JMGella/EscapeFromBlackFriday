package com.svalero.EFBF;


import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.svalero.EFBF.manager.R;
import com.svalero.EFBF.screen.GameScreen;
import com.svalero.EFBF.screen.SplashScreen;

import static com.svalero.EFBF.util.Constants.GAME_NAME;


public class EFBF extends Game {

    public Preferences prefs;
    public boolean isPaused;

    public boolean isGameOver;

    public int currentLevel = 1;


    @Override
    public void create() {
      setScreen(new SplashScreen(this));
      prefs = Gdx.app.getPreferences(GAME_NAME);
    }

    @Override
    public void render() {
    super.render();
    }

    @Override
    public void dispose() {
       super.dispose();
    }
}
