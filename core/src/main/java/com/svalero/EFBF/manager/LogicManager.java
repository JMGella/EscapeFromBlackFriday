package com.svalero.EFBF.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.svalero.EFBF.EFBF;
import com.svalero.EFBF.characters.Player;

import static com.svalero.EFBF.util.Constants.TILE_WIDTH;


public class LogicManager {

    public int currentLevel;

    private EFBF game;

    public Player player;


    public LogicManager(EFBF game) {
        this.game = game;
        currentLevel = 1;
        createPlayer();

    }




    private void createPlayer() {
        player = new Player(R.getTexture("player_idle_right"));
    }

    private void managePlayerInput(float dt) {
        player.setVelocity(0,0);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveRight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.jump();
        }
    }




    public void update(float dt) {


        managePlayerInput(dt);
        player.update(dt);


    }
}


