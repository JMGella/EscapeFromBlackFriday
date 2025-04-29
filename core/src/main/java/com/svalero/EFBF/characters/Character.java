package com.svalero.EFBF.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Character {
    protected TextureRegion currentFrame;
    protected Vector2 position;
    protected Rectangle rectangle;
    protected Vector2 velocity;
    protected boolean isJumping;

    public Character(TextureRegion texture, Vector2 position) {
        this.currentFrame = texture;
        this.position = position;
        initialize();

    }

    public Character(TextureRegion texture) {
        this.currentFrame = texture;
        position = new Vector2(0, 0);
      initialize();
    }

    private void initialize(){
        rectangle = new Rectangle(position.x, position.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        velocity = new Vector2(0, 0);
        isJumping = false;
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

    public void setVelocity(float x, float y) {
        velocity.x = x;
        velocity.y = y;
    }

    public float getWidth() {
        return currentFrame.getRegionWidth();
    }

    public float getHeight() {
        return currentFrame.getRegionHeight();
    }


}
