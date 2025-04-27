package com.svalero.EFBF.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.svalero.EFBF.EFBF;

import static com.svalero.EFBF.util.Constants.GAME_NAME;

public class ConfigurationScreen implements Screen {

    private EFBF game;
    private Screen previousScreen;

    private Preferences preferences;
    public ConfigurationScreen(EFBF game, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;
        loadPreferences();
    }

    private void loadPreferences() {
        preferences = Gdx.app.getPreferences(GAME_NAME);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
