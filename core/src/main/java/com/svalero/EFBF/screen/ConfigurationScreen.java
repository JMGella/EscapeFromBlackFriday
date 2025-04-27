package com.svalero.EFBF.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.svalero.EFBF.EFBF;

import static com.svalero.EFBF.util.Constants.GAME_NAME;

public class ConfigurationScreen implements Screen {

    private EFBF game;
    private Screen previousScreen;

    private Preferences preferences;

    private Stage stage;

    public ConfigurationScreen(EFBF game, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;
        loadPreferences();
    }

    private void loadPreferences() {
        preferences = Gdx.app.getPreferences(GAME_NAME);

    }

    private void loadStage() {
        if (!VisUI.isLoaded()) {
            VisUI.load();
        }
        stage = new Stage();

        VisTable table = new VisTable(true);
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        VisLabel titleLabel = new VisLabel("Configuration");
        titleLabel.setFontScale(2f);

        VisCheckBox soundCheckBox = new VisCheckBox("Sound");
        soundCheckBox.setChecked(preferences.getBoolean("sound", true));
        soundCheckBox.addListener(new ClickListener()  {
            @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    preferences.putBoolean("sound", soundCheckBox.isChecked());
                    preferences.flush();
                }
        });

        VisTextButton backButton = new VisTextButton("Back");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(previousScreen);
            }
        });

        table.row();
        table.add(titleLabel).colspan(2).padBottom(20);
        table.row();
        table.add(soundCheckBox).padBottom(20);
        table.row();
        table.add(backButton).padBottom(20);

    }


    @Override
    public void show() {
        loadStage();
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
