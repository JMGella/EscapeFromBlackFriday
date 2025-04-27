package com.svalero.EFBF.manager;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.svalero.EFBF.characters.Player;

public class LevelManager {

    public TiledMap map;

    public static TiledMapTileLayer groundLayer;


    private LogicManager logicManager;
    public LevelManager(LogicManager logicManager) {
        this.logicManager = logicManager;
        loadCurrentLevel();
    }

    public void loadCurrentLevel() {
        map = new TmxMapLoader().load("levels/level1.tmx");
        groundLayer = (TiledMapTileLayer) map.getLayers().get("ground");


    }


}
