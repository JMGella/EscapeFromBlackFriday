package com.svalero.EFBF.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svalero.EFBF.manager.LevelManager;
import com.svalero.EFBF.manager.R;

import static com.svalero.EFBF.util.Constants.*;

public class Enemy extends Character {

    public int speed;
    public boolean ghosted;

    private boolean isMovingRight;

    private int intEnemyNumber;



    public Enemy(TextureRegion texture, Vector2 position, String EnemyNumber) {
        super(texture);
        this.position = position;
        this.currentFrame = texture;
        rightAnimation = new Animation<>(0.15f, R.getAnimation("enemy" + EnemyNumber + "_walk_right"));
        leftAnimation = new Animation<>(0.15f, R.getAnimation("enemy" + EnemyNumber + "_walk_left"));
        this.rectangle = new Rectangle(position.x, position.y, texture.getRegionWidth(), texture.getRegionHeight());
        this.state = State.IDLE_LEFT;
        this.stateTime = 0;
        this.intEnemyNumber = Integer.parseInt(EnemyNumber);
        if (intEnemyNumber == 1) {
            this.speed = 50;
        } else if (intEnemyNumber == 2) {
            this.speed = 75;
        } else if (intEnemyNumber == 3) {
            this.speed = 100;
        }
        this.ghosted = false;
    }

    public void setGhosted(boolean ghosted) {
        this.ghosted = ghosted;
    }




    public void update(float dt) {

        velocity.y += GRAVITY * dt;
        stateTime += dt;
        if (isMovingRight) {
            velocity.x = speed;
        } else {
            velocity.x = -speed;
        }
        float nextX = position.x + velocity.x * dt;
        float nextY = position.y + velocity.y * dt;
        if (ghosted){
            position.x = nextX;
            position.y = nextY;
        } else {

            if (!isCellBlocked(nextX, position.y)) {
                position.x = nextX;
            } else {
                isMovingRight = !isMovingRight;
            }

            if (!isCellBlocked(position.x, nextY)) {
                position.y = nextY;
            } else {
                if (velocity.y < 0) {
                    isJumping = false;
                }
                velocity.y = 0;
            }
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
                currentFrame = R.getTexture("enemy" + intEnemyNumber + "_idle_right");
                break;
            case IDLE_LEFT:
                currentFrame = R.getTexture("enemy" + intEnemyNumber + "_idle_left");
                break;
        }

    }
}
