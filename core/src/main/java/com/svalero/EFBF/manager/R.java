package com.svalero.EFBF.manager;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import lombok.Data;


@Data
public class R {



 private static AssetManager assetManager = new AssetManager();

 public static boolean update() {
     return assetManager.update();
 }


    public static void loadResources(){
        assetManager.load("efbf.atlas", TextureAtlas.class);
        assetManager.load("music/menuMusic.mp3", Music.class);
        assetManager.load("music/level1Music.mp3", Music.class);
        assetManager.load("music/level2Music.mp3", Music.class);
        assetManager.finishLoading();

    }

    public static TextureRegion getTexture(String name) {
        return assetManager.get("efbf.atlas", TextureAtlas.class).findRegion(name);
    }

    public static Array<TextureAtlas.AtlasRegion> getAnimation(String name) {
        return assetManager.get("efbf.atlas", TextureAtlas.class).findRegions(name);
    }

    public static Music getMusic(String name) {
        return assetManager.get("music/" + name + ".mp3", Music.class);
    }


}






