package com.svalero.EFBF.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.svalero.EFBF.EFBF;
import com.svalero.EFBF.characters.Player;



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
            player.moveRight(dt);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveLeft(dt);
        }
    }

    public void update(float dt) {


        managePlayerInput(dt);
        player.update(dt);


    }
}


