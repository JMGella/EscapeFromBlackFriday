package com.svalero.EFBF.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svalero.EFBF.manager.R;

import static com.svalero.EFBF.util.Constants.GRAVITY;
import static com.svalero.EFBF.util.Constants.TILE_WIDTH;

public class Rat extends Character{

    public boolean ghosted;

    private int speed;

    private boolean isMovingRight;

    private float startX;

    public Rat(TextureRegion texture, Vector2 position){
        super(texture, position);
        this.currentFrame = texture;
        rightAnimation = new Animation<>(0.15f, R.getAnimation("rat_walk_right"));
        leftAnimation = new Animation<>(0.15f, R.getAnimation("rat_walk_left"));
        rectangle.setPosition(position.x, position.y);
        this.state = State.RIGHT;
        this.stateTime = 0;
        this.ghosted = false;
        this.speed = 100;
        this.startX = position.x;
        this.isMovingRight = true;

    }

    public void setGhosted(boolean ghosted) {
        this.ghosted = ghosted;
    }



    public void update(float dt){
        velocity.y += GRAVITY * dt;
        stateTime += dt;
        if (isMovingRight) {
            velocity.x = this.speed;
        } else {
            velocity.x = -this.speed;
        }

        float nextX = position.x + velocity.x * dt;
        float nextY = position.y + velocity.y * dt;



        if (isMovingRight && nextX > startX + TILE_WIDTH *2) {
            isMovingRight = false;
        } else if (!isMovingRight && nextX < startX - TILE_WIDTH *2) {
            isMovingRight = true;
        }


        if (ghosted){
            position.x = nextX;
            position.y = nextY;
        } else {


                position.x = nextX;


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
        } else {
            state = State.LEFT;
        }
        switch (state) {
            case RIGHT:
                currentFrame = rightAnimation.getKeyFrame(stateTime, true);
                break;
            case LEFT:
                currentFrame = leftAnimation.getKeyFrame(stateTime, true);
                break;
        }

    }

}





