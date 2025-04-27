package com.svalero.EFBF.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.svalero.EFBF.EFBF;

public class PauseScreen implements Screen {

    private EFBF game;
    private Screen previousScreen;

    private Stage stage;

    public PauseScreen(EFBF game, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;

    }
    @Override
    public void show() {
        if (!VisUI.isLoaded()){
            VisUI.load();
        }

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        VisLabel pauseLabel = new VisLabel("Game Paused");
        pauseLabel.setFontScale(2);

        VisTextButton resumeButton = new VisTextButton("Resume");
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.isPaused = false;
                game.setScreen(previousScreen);
            }
        });

        VisTextButton exitButton = new VisTextButton("Exit");
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.isPaused = false;
                previousScreen.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.add(pauseLabel).padBottom(50);
        table.row();
        table.add(resumeButton).width(200).height(50).padBottom(20);
        table.row();
        table.add(exitButton).width(200).height(50).padBottom(20);

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        stage.act(v);
        stage.draw();
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
    stage.dispose();
    }
}
