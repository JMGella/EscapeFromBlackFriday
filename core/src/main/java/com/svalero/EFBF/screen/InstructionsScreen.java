package com.svalero.EFBF.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.svalero.EFBF.EFBF;

public class InstructionsScreen implements Screen {

    private EFBF game;
    private Screen previousScreen;
    private Stage stage;


    public InstructionsScreen(EFBF game, Screen previousScreen) {
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

        String instructions = "INSTRUCTIONS\n" +
                "You are a Retail Employee that has just finished his shift the most frightening day:\n" +
                "BLACK FRIDAY.\n\n" +
                "You have to collect all your items in the store and avoid the costumers in your way home.\n\n" +
                "1. Use the arrow keys to move.\n" +
                "2. Press arrow up to jump.\n" +
                "3. Collect items.\n" +
                "4. Avoid costumers and other dangers to stay alive.\n" +
                "5. Reach the exit to win.";

        VisLabel instructionsLabel = new VisLabel(instructions);

        VisTextButton backButton = new VisTextButton("Back");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(previousScreen);
            }
        });

        table.row();
        table.add(instructionsLabel).center().pad(10);
        table.row();
        table.add(backButton).pad(10);
        table.row();

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
        VisUI.dispose();

    }
}
