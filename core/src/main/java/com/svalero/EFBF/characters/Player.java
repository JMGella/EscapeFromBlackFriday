package com.svalero.EFBF.characters;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.svalero.EFBF.manager.ConfigurationManager;
import com.svalero.EFBF.manager.LevelManager;
import com.svalero.EFBF.manager.R;

import java.util.ArrayList;
import java.util.List;

import static com.svalero.EFBF.util.Constants.*;

public class Player extends Character {
    public int score;
    public int lives;

    private List<String> items;


    public Player(TextureRegion texture) {
        super(texture);
        score = 0;
        lives = 5;
        rightAnimation = new Animation<>(0.15f, R.getAnimation("player_run_right"));
        leftAnimation = new Animation<>(0.15f, R.getAnimation("player_run_left"));
        currentFrame = R.getTexture("player_idle_right");
        state = State.IDLE_RIGHT;
        setPosition(new Vector2(33, TILE_HEIGHT ));
        rectangle.setPosition(position.x, position.y);
        items = new ArrayList<>();


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




    public void getDamage(){
        lives--;
    }



    public void takeItem(String name){
        // phone , wallet , keys , bottle
            items.add(name);
            score++;
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
