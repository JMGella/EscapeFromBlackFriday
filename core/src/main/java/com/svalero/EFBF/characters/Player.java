package com.svalero.EFBF.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class Player extends Character {
    private int lives;
    private int energy;
    private int score;
    private List<Item> personalObjects;

    public Player(TextureRegion texture, Vector2 position) {
        super(texture, position);
    }
}
