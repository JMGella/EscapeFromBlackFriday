package com.svalero.EFBF.manager;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
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
        assetManager.load("sounds/hit.mp3", Sound.class);
        assetManager.load("sounds/ouch.mp3", Sound.class);
        assetManager.load("sounds/coin.mp3", Sound.class);
        assetManager.load("sounds/die.mp3", Sound.class);
        assetManager.load("sounds/jump.mp3", Sound.class);
        assetManager.load("sounds/exit.mp3", Sound.class);
        assetManager.load("sounds/game-over.mp3", Sound.class);
        assetManager.load("levels/ceilingSign.png", Texture.class);
        assetManager.load("levels/wallet.png", Texture.class);
        assetManager.load("levels/bottle.png", Texture.class);
        assetManager.load("levels/keys.png", Texture.class);
        assetManager.load("levels/phone.png", Texture.class);
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

    public static Sound getSound(String name) {
        return assetManager.get("sounds/" + name + ".mp3", Sound.class);
    }

    public static Texture getTextureFile(String name) {
        return assetManager.get("levels/" + name + ".png", Texture.class);
    }
}






