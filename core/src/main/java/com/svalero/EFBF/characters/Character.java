package com.svalero.EFBF.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svalero.EFBF.manager.LevelManager;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.svalero.EFBF.util.Constants.TILE_HEIGHT;
import static com.svalero.EFBF.util.Constants.TILE_WIDTH;

@Data
@NoArgsConstructor
public abstract class Character {
    protected TextureRegion currentFrame;
    protected Vector2 position;
    protected Rectangle rectangle;
    protected Vector2 velocity;
    protected boolean isJumping;
    protected enum State {RIGHT, LEFT, IDLE_RIGHT, IDLE_LEFT}
    protected State state;
    protected Animation<TextureRegion> rightAnimation, leftAnimation;
    protected float stateTime;

    public Character(TextureRegion texture, Vector2 position) {
        this.currentFrame = texture;
        this.position = position;
        initialize();

    }

    public Character(TextureRegion texture) {
        this.currentFrame = texture;
        position = new Vector2(0, 0);
      initialize();
    }

    private void initialize(){
        rectangle = new Rectangle(position.x, position.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        velocity = new Vector2(0, 0);
        isJumping = false;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void move(float x, float y) {
        position.x += x;
        position.y += y;
        rectangle.setPosition(position);
    }

    public void setVelocity(float x, float y) {
        velocity.x = x;
        velocity.y = y;
    }

    public float getWidth() {
        return currentFrame.getRegionWidth();
    }

    public float getHeight() {
        return currentFrame.getRegionHeight();
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


}
