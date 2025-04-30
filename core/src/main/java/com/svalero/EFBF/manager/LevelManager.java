package com.svalero.EFBF.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.svalero.EFBF.characters.Enemy;
import com.svalero.EFBF.characters.Player;
import com.svalero.EFBF.items.Item;

import static com.svalero.EFBF.util.Constants.GAME_NAME;

public class LevelManager {

    public TiledMap map;

    public static TiledMapTileLayer groundLayer;

    public static MapLayer objectsLayer;

    public static Music music;

    private int level;

    private Preferences prefs;


    private LogicManager logicManager;
    public LevelManager(LogicManager logicManager, int currentLevel) {
        this.logicManager = logicManager;
        level = currentLevel;
        prefs = Gdx.app.getPreferences(GAME_NAME);
        loadCurrentLevel(level);
    }

    public void loadCurrentLevel(int level) {
        map = new TmxMapLoader().load("levels/level" + level +".tmx");
        groundLayer = (TiledMapTileLayer) map.getLayers().get("ground");
        objectsLayer =  map.getLayers().get("objects");
        setMusic();
        loadEnemies();
        loadItems();
    }

    private void setMusic(){

            music = R.getMusic("level" + level + "Music");
            music.setLooping(true);
            music.setVolume(prefs.getFloat("volume", 50) / 100f);
        if(ConfigurationManager.isMusicEnabled()) {
            music.play();
        }
    }


    private void loadEnemies(){
        for (MapObject mapObject : objectsLayer.getObjects()) {
            String type = mapObject.getProperties().get("type", String.class);
            if (type.equals("enemy")) {
                float x =  mapObject.getProperties().get("x", Float.class);
                float y =  mapObject.getProperties().get("y", Float.class);
                String enemyNumber = mapObject.getProperties().get("enemyNumber", String.class);
                Enemy enemy = new Enemy(R.getTexture("enemy" + enemyNumber + "_idle_left"), new Vector2(x, y), enemyNumber);
                logicManager.enemies.add(enemy);
            }
        }
    }

    private void loadItems(){
        for (MapObject mapObject : objectsLayer.getObjects()) {
            String type = mapObject.getProperties().get("type", String.class);
            if (type.equals("item")) {
                float x =  mapObject.getProperties().get("x", Float.class);
                float y =  mapObject.getProperties().get("y", Float.class);
                String name = mapObject.getProperties().get("name", String.class);
                logicManager.items.add(new Item(R.getTexture(name), new Vector2(x, y), name));
            }
        }

    }

    public void dispose() {
        if (map != null) {
            map.dispose();
        }
        if (music != null) {
            music.dispose();
        }
    }


}
