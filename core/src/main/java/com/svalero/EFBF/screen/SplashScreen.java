package com.svalero.EFBF.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.svalero.EFBF.EFBF;
import com.svalero.EFBF.manager.R;

public class SplashScreen implements Screen {

    private EFBF game;

    private Texture splashTexture;

    private Image splashImage;

    private Stage stage;
    private boolean splashDone = false;

    public SplashScreen(EFBF game) {
        this.game = game;
        splashTexture = new Texture(Gdx.files.internal("splashImage.png"));
        splashImage = new Image(splashTexture);
        stage = new Stage();
        R.loadResources();


    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        splashImage.setSize(splashTexture.getWidth(), splashTexture.getHeight());


        splashImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f),
            Actions.delay(1.5f), Actions.run(() -> splashDone = true)));

        table.add(splashImage).center();
        stage.addActor(table);
        R.loadResources();

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_CLEAR_VALUE);

        stage.act();
        stage.draw();
        if (splashDone) {
            game.setScreen(new MainMenuScreen(game));
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
        splashTexture.dispose();
        stage.dispose();

    }
}
