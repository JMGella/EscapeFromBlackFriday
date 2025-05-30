package com.svalero.EFBF.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.svalero.EFBF.characters.Enemy;
import com.svalero.EFBF.characters.Rat;
import com.svalero.EFBF.characters.Sign;
import com.svalero.EFBF.items.Item;

import static com.svalero.EFBF.util.Constants.TILE_WIDTH;

public class RenderManager {

    private LogicManager logicManager;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer mapRender;
    private Batch batch;

    private BitmapFont font;


    public RenderManager(LogicManager logicManager, TiledMap map) {
        this.logicManager = logicManager;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 540, 360);
        this.camera.update();

        this.mapRender = new OrthogonalTiledMapRenderer(map);
        this.batch = mapRender.getBatch();
        this.font = new BitmapFont();
        this.font.getData().setScale(0.75f);

    }

    public void render() {

        camera.position.set(logicManager.player.getX(), 160, 0);
        camera.update();
        camera.zoom = 0.9f;

        mapRender.setView(camera);


        mapRender.render();


        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        batch.draw(logicManager.player.getCurrentFrame(), logicManager.player.getX(), logicManager.player.getY());

        for (Enemy enemy : logicManager.enemies) {
            batch.draw(enemy.getCurrentFrame(), enemy.getX(), enemy.getY());
        }
        for (Item item : logicManager.items) {
            batch.draw(item.getCurrentFrame(), item.getX(), item.getY());
        }
        for (Rat rat : logicManager.rats) {
            batch.draw(rat.getCurrentFrame(), rat.getX(), rat.getY());
        }
        for (Sign sign : logicManager.signs) {
            batch.draw(sign.getSignTexture(), sign.getX(), sign.getY());

        }


        float x = -250;
        float spacing = 25;
        int itemsLeft = 4;
        int itemsCount = 0;

        for (String item : logicManager.player.items) {
            Texture texture = R.getTextureFile(item);
            itemsLeft--;
            if (texture != null) {
                batch.draw(texture, camera.position.x + x, camera.position.y + 130);
                x += spacing;
            }
            itemsCount++;
        }
        if (itemsCount == 4) {
            font.getData().setScale(1f);
            font.draw(batch, "GO TO THE EXIT -->", camera.position.x -50, camera.position.y - 130);
            font.getData().setScale(0.75f);
        }

            int health = logicManager.player.lives * 100 / 5;

            font.draw(batch, "Items left: " + itemsLeft, camera.position.x + +160, camera.position.y + 150);
            font.draw(batch, "Health: " + health + "%", camera.position.x + 150, camera.position.y + 125);


            batch.end();
        }

        public void dispose () {
            if (mapRender != null) {
                mapRender.dispose();
            }
     }
}

