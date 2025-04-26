package com.svalero.EFBF.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Character {
    protected TextureRegion currentFrame;
    protected Vector2 position;
    protected Rectangle rectangle;
    protected Vector2 velocity;
    protected boolean isJumping;

    public Character(TextureRegion texture, Vector2 position) {
        this.currentFrame = texture;
        this.position = position;
        rectangle = new Rectangle(position.x, position.y, texture.getRegionWidth(), texture.getRegionHeight());
        this.velocity = new Vector2();
    }

    public Character(TextureRegion texture) {
        this.currentFrame = texture;
        position = new Vector2(0, 0);
        rectangle = new Rectangle(position.x, position.y, texture.getRegionWidth(), texture.getRegionHeight());
        this.velocity = new Vector2();
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void move(float x, float y) {
        position.x += x;
        position.y += y;
        rectangle.setPosition(position);
    }


}
