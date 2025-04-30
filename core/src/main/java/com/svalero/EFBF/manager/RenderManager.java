package com.svalero.EFBF.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.svalero.EFBF.characters.Enemy;
import com.svalero.EFBF.characters.Rat;
import com.svalero.EFBF.items.Item;

public class RenderManager {

    private LogicManager logicManager;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer mapRender;
    private Batch batch;

    public RenderManager(LogicManager logicManager, TiledMap map){
        this.logicManager = logicManager;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 540, 360);
//        this.camera.setToOrtho(false, 640, 360);
        this.camera.update();

        this.mapRender = new OrthogonalTiledMapRenderer(map);
        this.batch = mapRender.getBatch();
    }

    public void render(){

        camera.position.set(logicManager.player.getX(),160 , 0);
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
        batch.end();
    }

    public void dispose() {
        if (mapRender != null) {
            mapRender.dispose();
        }
    }
}
