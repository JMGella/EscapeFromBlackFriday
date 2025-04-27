package com.svalero.EFBF.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.*;
import com.svalero.EFBF.EFBF;

import static com.svalero.EFBF.util.Constants.GAME_NAME;

public class ConfigurationScreen implements Screen {

    private EFBF game;
    private Screen previousScreen;

    private Preferences preferences;

    private Stage stage;

    VisLabel volumeLabel;

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
        VisCheckBox musicCheckBox = new VisCheckBox("Music");
        musicCheckBox.setChecked(preferences.getBoolean("music", true));
        musicCheckBox.addListener(new ClickListener()  {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                preferences.putBoolean("music", musicCheckBox.isChecked());
                preferences.flush();
            }
        });

        volumeLabel = new VisLabel("Volume :" + preferences.getFloat("volume", 50));
        VisSlider volumeSlider = new VisSlider(0, 100, 1, false);
        volumeSlider.setValue(preferences.getFloat("volume", 50));
        volumeSlider.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                preferences.putFloat("volume", volumeSlider.getValue());
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
        table.add(soundCheckBox).padBottom(20).center();
        table.row();
        table.add(musicCheckBox).padBottom(20).center();
        table.row();
        table.add(volumeLabel).padBottom(20);
        table.row();
        table.add(volumeSlider).padBottom(20);
        table.row();
        table.add(backButton).padBottom(20);

    }


    @Override
    public void show() {
        loadStage();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        stage.act(v);
        stage.draw();
        volumeLabel.setText("Volume : " + preferences.getFloat("volume", 50));

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
