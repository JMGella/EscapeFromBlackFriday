package com.svalero.EFBF.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svalero.EFBF.characters.Character;

public class Item extends Character {

    public String name;

    public Item(TextureRegion texture, Vector2 position, String name) {
        super(texture, position);
        this.name = name;
        rectangle = new Rectangle(position.x, position.y, texture.getRegionWidth(), texture.getRegionHeight());
    }
}
