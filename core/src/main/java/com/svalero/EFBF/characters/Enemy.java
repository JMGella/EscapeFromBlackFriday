package com.svalero.EFBF.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svalero.EFBF.manager.LevelManager;
import com.svalero.EFBF.manager.R;

import static com.svalero.EFBF.util.Constants.*;

public class Enemy extends Player {

    public int speed;
    private boolean ghosted;



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
            this.speed = 100;
        } else if (intEnemyNumber == 2) {
            this.speed = 200;
        } else if (intEnemyNumber == 3) {
            this.speed = 300;
        }
        this.ghosted = false;
    }

    public void setGhosted(boolean ghosted) {
        this.ghosted = ghosted;
    }


    private boolean isCellBlocked(int x, int y) {
       if (ghosted) {
           return false;
       } else {
           int cellX1 = (int) (x / TILE_WIDTH);
           int cellY1 = (int) (y / TILE_HEIGHT);

           int cellX2 = (int) ((x + rectangle.getWidth() - 1) / TILE_WIDTH);
           int cellY2 = (int) (y / TILE_HEIGHT);

           if (cellX1 < 0 || cellY1 < 0 || cellX2 < 0 || cellY2 < 0) {
               return true;
           }

           if (cellX1 >= LevelManager.groundLayer.getWidth()){
               return true;
           }

           if (cellX2 >= LevelManager.groundLayer.getWidth()) {
               return true;
           }

           TiledMapTileLayer.Cell cell1 = LevelManager.groundLayer.getCell(cellX1, cellY1);
           TiledMapTileLayer.Cell cell2 = LevelManager.groundLayer.getCell(cellX2, cellY2);

           if (cell1 != null) {
               return true;
           } else if (cell2 != null) {
               return true;
           } else {
               return false;
           }

       }
    }


    @Override
    public void update(float dt) {

        velocity.y += GRAVITY * dt;
        stateTime += dt;
        float nextX = position.x + velocity.x * dt;
        float nextY = position.y + velocity.y * dt;
        if (ghosted){
            position.x = nextX;
            position.y = nextY;
        } else {

            if (!isCellBlocked(nextX, position.y)) {
                position.x = nextX;
            } else {
                velocity.x = 0;
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
