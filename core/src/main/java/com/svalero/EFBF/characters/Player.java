package com.svalero.EFBF.characters;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.svalero.EFBF.manager.R;

import static com.svalero.EFBF.util.Constants.PLAYER_SPEED;
import static com.svalero.EFBF.util.Constants.TILE_HEIGHT;

public class Player extends Character {
    private int score;
    private int lives;
    private int currentLevel;

    private enum State {RIGHT, LEFT, IDLE_RIGHT, IDLE_LEFT}
    public State state;
    private Animation<TextureRegion> rightAnimation, leftAnimation;
    private float stateTime;




    public Player(TextureRegion texture) {
        super(texture);
        score = 0;
        lives = 3;
        currentLevel = 1;
        rightAnimation = new Animation<>(0.15f, R.getAnimation("player_run_right"));
        leftAnimation = new Animation<>(0.15f, R.getAnimation("player_run_left"));
        currentFrame = R.getTexture("player_idle_right");
        state = State.IDLE_RIGHT;
        setPosition(new Vector2(0, TILE_HEIGHT * 1));
        rectangle.setPosition(position.x, position.y);


    }

    public void moveRight(float delta) {
        velocity.x = PLAYER_SPEED;
    }

    public void moveLeft(float delta) {
        velocity.x = -PLAYER_SPEED;
    }


    public void update(float dt){
        position.x += velocity.x * dt;
        position.y += velocity.y * dt;
        rectangle.setPosition(position);

        stateTime += dt;

        if (velocity.x > 0 ) {
            state = State.RIGHT;
        } else if (velocity.x < 0) {
            state = State.LEFT;
        } else {
            state = State.IDLE_RIGHT;
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
