package com.svalero.EFBF.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.svalero.EFBF.util.Constants.GRAVITY;
import static com.svalero.EFBF.util.Constants.SIGN_VELOCITY;

public class Sign extends Character {
    private Texture signTexture;

    public boolean isFalling;

    public boolean isHitted;

    public Sign(Texture texture, Vector2 position) {
        signTexture = texture;
        this.position = position;
        rectangle = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        velocity = new Vector2(0, 0);
        isFalling = false;
        isHitted = false;

    }

    public Texture getSignTexture() {
        return signTexture;
    }


    public void update(float dt) {
        if (isFalling) {
            move(0, -SIGN_VELOCITY * dt);
        }

        rectangle.setPosition(position);
    }


}
