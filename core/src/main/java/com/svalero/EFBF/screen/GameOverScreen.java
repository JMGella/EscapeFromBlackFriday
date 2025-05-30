package com.svalero.EFBF.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.svalero.EFBF.EFBF;

public class GameOverScreen implements Screen {


    private EFBF game;

    private Texture texture;

    private Image image;

    private Stage stage;

    public GameOverScreen(EFBF game) {
        this.game = game;
        loadScreen();
    }

    private void loadScreen() {

        texture = new Texture("gameover.png");
        image = new Image(texture);
        stage = new Stage();
        stage.addActor(image);


    }
    @Override
    public void show() {
        game.isGameOver = true;
        game.currentLevel = 1;

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        image.setSize(texture.getWidth(), texture.getHeight());


        image.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f),
            Actions.delay(2.5f), Actions.run(() -> game.setScreen(new MainMenuScreen(game)))));

        table.add(image).center();
        stage.addActor(table);

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_CLEAR_VALUE);

        stage.act();
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
        texture.dispose();
        stage.dispose();

    }
}
