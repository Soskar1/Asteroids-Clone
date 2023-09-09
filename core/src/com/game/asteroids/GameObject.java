package com.game.asteroids;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public abstract class GameObject {
    private Sprite sprite;
    private Rectangle rectangle;

    public abstract void update(float deltaTime);

    public void setSprite(Texture newTexture) {
        sprite = new Sprite(newTexture);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setRectangle(Rectangle newRectangle) {
        rectangle = newRectangle;
    }

    public Vector2 getPosition() {
        return new Vector2(rectangle.x, rectangle.y);
    }

    public void setPosition(Vector2 position) {
        rectangle.x = (int)position.x;
        rectangle.y = (int)position.y;

        if (sprite != null) {
            sprite.setPosition(position.x, position.y);
        }
    }

    public float getRotation() {
        return sprite.getRotation();
    }

    public void setRotation(float rotation) {
        sprite.setRotation(rotation);
    }
}
