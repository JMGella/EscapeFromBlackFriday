package com.svalero.EFBF.characters;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.svalero.EFBF.manager.ConfigurationManager;
import com.svalero.EFBF.manager.LevelManager;
import com.svalero.EFBF.manager.R;
import com.svalero.EFBF.screen.GameScreen;

import java.util.ArrayList;

import static com.svalero.EFBF.util.Constants.*;

public class Player extends Character {
    public int score;
    public int lives;
    private int currentLevel;

    protected enum State {RIGHT, LEFT, IDLE_RIGHT, IDLE_LEFT}
    protected State state;
    protected Animation<TextureRegion> rightAnimation, leftAnimation;
    protected float stateTime;




    public Player(TextureRegion texture) {
        super(texture);
        score = 0;
        lives = 5;
        currentLevel = 1;
        rightAnimation = new Animation<>(0.15f, R.getAnimation("player_run_right"));
        leftAnimation = new Animation<>(0.15f, R.getAnimation("player_run_left"));
        currentFrame = R.getTexture("player_idle_right");
        state = State.IDLE_RIGHT;
        setPosition(new Vector2(33, TILE_HEIGHT ));
        rectangle.setPosition(position.x, position.y);


    }

    public void moveRight() {
        velocity.x = PLAYER_SPEED;
    }

    public void moveLeft() {
        velocity.x = -PLAYER_SPEED;
    }

    public void jump(){
        if(!isJumping){
            velocity.y = PLAYER_JUMP_SPEED;
            isJumping = true;
            if(ConfigurationManager.isSoundEnabled()) {
                if (position.y > 100) {
                    R.getSound("jump").play();
                }
            }
        }
    }


    protected boolean isCellBlocked(float x, float y) {
        int cellX1 = (int) (x / TILE_WIDTH);
        int cellX2 = (int) ((x + rectangle.getWidth()) / TILE_WIDTH);
        int cellX3 = (int) ((x + rectangle.getWidth() /2 )  / TILE_WIDTH);

        int cellY= (int) (y / TILE_HEIGHT);

        if (cellX1 < 0 || cellY < 0 || cellX2 < 0 || cellX3 < 0) {
            return true;
        }

        if (cellX1 >= LevelManager.groundLayer.getWidth()){
            return true;
        }

        if (cellX2 >= LevelManager.groundLayer.getWidth()) {
            return true;
        }

        if (cellX3 >= LevelManager.groundLayer.getWidth()) {
            return true;
        }

        TiledMapTileLayer.Cell cell1 = LevelManager.groundLayer.getCell(cellX1, cellY);
        TiledMapTileLayer.Cell cell2 = LevelManager.groundLayer.getCell(cellX2, cellY);
        TiledMapTileLayer.Cell cell3 = LevelManager.groundLayer.getCell(cellX3, cellY);

        if (cell1 != null) {
            return true;
        } else if (cell2 != null) {
            return true;
        } else if (cell3 != null) {
            return true;
        } else {
            return false;
        }

    }

    public void getDamage(){
        lives--;
    }



    public void takeItem(String name){
        if (name.equals("phone")){
            score++;
        } else if (name.equals("wallet")){
            score++;
        } else if (name.equals("keys")){
            score++;
        } else if (name.equals("bottle")){
            score++;
        }

    }



    public void update(float dt){

        velocity.y += GRAVITY * dt;
        stateTime += dt;
        float nextX = position.x + velocity.x * dt;
        if (!isCellBlocked(nextX, position.y)) {
            position.x = nextX;
        } else {
            velocity.x = 0;
        }
        float nextY = position.y + velocity.y * dt;
        if (!isCellBlocked(position.x, nextY)) {
            position.y = nextY;
        } else {
            if (velocity.y < 0) {
                isJumping = false;
            }
            velocity.y = 0;
        }
        rectangle.setPosition(position);


        if (velocity.x > 0) {
            state = State.RIGHT;
        } else if (velocity.x < 0) {
            state = State.LEFT;
        } else {
            if (state == State.RIGHT) {
                state = State.IDLE_RIGHT;
            } else if (state == State.LEFT) {
                state = State.IDLE_LEFT;
            }
        }
        switch (state) {
            case RIGHT:
                currentFrame = rightAnimation.getKeyFrame(stateTime, true);
                break;
            case LEFT:
                currentFrame = leftAnimation.getKeyFrame(stateTime, true);
                break;
            case IDLE_RIGHT:
                currentFrame = R.getTexture("player_idle_right");
                break;
            case IDLE_LEFT:
                currentFrame = R.getTexture("player_idle_left");
                break;
        }




    }





}
