package com.svalero.EFBF.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.svalero.EFBF.characters.Player;

import static com.svalero.EFBF.util.Constants.GAME_NAME;

public class LevelManager {

    public TiledMap map;

    public static TiledMapTileLayer groundLayer;

    public Music music;

    private int level;

    private Preferences prefs;


    private LogicManager logicManager;
    public LevelManager(LogicManager logicManager) {
        this.logicManager = logicManager;
        level = 1;
        prefs = Gdx.app.getPreferences(GAME_NAME);
        loadCurrentLevel(level);
    }

    public void loadCurrentLevel(int level) {
        map = new TmxMapLoader().load("levels/level" + level +".tmx");
        groundLayer = (TiledMapTileLayer) map.getLayers().get("ground");
        music = R.getMusic("level" + level + "Music");
        music.setLooping(true);
        music.setVolume(prefs.getFloat("volume",50)/100f);
        if (prefs.getBoolean("music")) {
            music.play();
        } else {
            music.stop();
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
