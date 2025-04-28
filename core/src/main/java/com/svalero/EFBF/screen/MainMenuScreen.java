package com.svalero.EFBF.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.svalero.EFBF.EFBF;
import com.svalero.EFBF.manager.R;

import static com.svalero.EFBF.util.Constants.GAME_NAME;

public class MainMenuScreen implements Screen {
    private EFBF game;
    private Stage stage;

    public Music music;


    public MainMenuScreen(EFBF game) {
        this.game = game;
    }

    private void loadStage(){
        if (!VisUI.isLoaded()) {
            VisUI.load();
        }

        stage = new Stage();

        VisTable table = new VisTable(true);
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        VisLabel titleLabel = new VisLabel(GAME_NAME);
        titleLabel.setFontScale(2.5f);

        VisTextButton startButton = new VisTextButton("Start");
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(game,1));
                music.stop();
            }
        });

        VisTextButton configButton = new VisTextButton("Configuration");
        configButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new ConfigurationScreen(game, game.getScreen(), music));
            }
        });

        VisTextButton exitButton = new VisTextButton("Exit");
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Gdx.app.exit();
            }
        });

        table.row();
        table.add(titleLabel).center().padBottom(30);;
        table.row();
        table.add(startButton).center().width(200).height(50).padBottom(20);;
        table.row();
        table.add(configButton).center().width(200).height(50).padBottom(20);;
        table.row();
        table.add(exitButton).center().width(200).height(50).padBottom(20);;

        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void show() {
        loadStage();
        music = R.getMusic("menuMusic");
        music.setLooping(true);
        music.setVolume(game.prefs.getFloat("volume", 50)/100f);
        if (game.prefs.getBoolean("music")) {
            music.play();
        } else {
            music.stop();
        }

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
