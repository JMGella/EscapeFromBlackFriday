package com.svalero.EFBF.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.svalero.EFBF.EFBF;
import com.svalero.EFBF.characters.Player;
import com.svalero.EFBF.manager.ConfigurationManager;
import com.svalero.EFBF.manager.LevelManager;
import com.svalero.EFBF.manager.LogicManager;
import com.svalero.EFBF.manager.RenderManager;

import static com.badlogic.gdx.Input.Keys.R;

public class GameScreen implements Screen {

    private EFBF game;

    private LogicManager logicManager;
    private RenderManager renderManager;
    private LevelManager levelManager;

    private int currentLevel;



    public GameScreen(EFBF game, int currentLevel) {
        this.game = game;
        this.currentLevel = currentLevel;
        ConfigurationManager.loadPreferences();
        loadManagers(currentLevel);

    }

    private void loadManagers(int currentLevel) {
        logicManager = new LogicManager(game);
        levelManager = new LevelManager(logicManager, currentLevel);
        renderManager = new RenderManager(logicManager, levelManager.map);

    }
    @Override
    public void show() {
        game.isGameOver = false;
        game.isPaused = false;

    }

    @Override
    public void render(float v){
        logicManager.update(v);
        renderManager.render();
        if(game.isGameOver){
            levelManager.music.stop();
        }
        else if (game.isPaused) {
            levelManager.music.pause();
        }
        else {
            levelManager.music.play();
        }

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
       levelManager.dispose();
       renderManager.dispose();


    }
}
