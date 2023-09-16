package com.game.asteroids;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public abstract class GameObject {
    private Sprite sprite;
    private Rectangle rectangle;
    private boolean isActive = true;

    public abstract void update(float deltaTime);

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean state) {
        isActive = state;
    }

    public void setSprite(Texture newTexture) {
        sprite = new Sprite(newTexture);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle newRectangle) {
        rectangle = newRectangle;
    }

    public Vector2 getPosition() {
        return new Vector2(rectangle.x, rectangle.y);
    }

    public void setPosition(Vector2 newPosition) {
        rectangle.x = (int)newPosition.x;
        rectangle.y = (int)newPosition.y;

        if (sprite != null) {
            sprite.setPosition(newPosition.x, newPosition.y);
        }
    }

    public float getRotation() {
        return sprite.getRotation();
    }

    public void setRotation(float rotation) {
        sprite.setRotation(rotation);
    }
}