package com.svalero.EFBF.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svalero.EFBF.manager.LevelManager;

import static com.svalero.EFBF.util.Constants.TILE_HEIGHT;
import static com.svalero.EFBF.util.Constants.TILE_WIDTH;

public class Enemy extends Player {

    protected int speed;
    private boolean ghosted;

    public Enemy(TextureRegion texture, Vector2 position, String speed) {
        super(texture);
        this.position = position;
        this.currentFrame = texture;
        this.rectangle = new Rectangle(position.x, position.y, texture.getRegionWidth(), texture.getRegionHeight());
        this.state = State.IDLE_LEFT;
        this.speed = Integer.parseInt(speed);
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

    }
}
