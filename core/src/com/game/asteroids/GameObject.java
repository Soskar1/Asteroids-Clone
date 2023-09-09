package com.game.asteroids;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public abstract class GameObject {
    private Texture texture;
    private Sprite sprite;
    private Rectangle rectangle;

    public abstract void update();

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture newTexture) {
        texture = newTexture;
        sprite = new Sprite(texture);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite newSprite) {
        sprite = newSprite;
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

    public void setPosition(Vector2 position) {
        rectangle.x = (int)position.x;
        rectangle.y = (int)position.y;

        if (sprite != null) {
            sprite.setPosition(rectangle.x, rectangle.y);
        }
    }
}
