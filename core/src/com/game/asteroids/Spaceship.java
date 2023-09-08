package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class Spaceship {
    private final Texture texture;
    private final Rectangle rectangle;
    private final int maxSpeed;

    public Spaceship(int maxSpeed) {
        texture = new Texture(Gdx.files.internal("Spaceship.png"));
        rectangle = new Rectangle(texture.getWidth(), texture.getHeight());
        this.maxSpeed = maxSpeed;
    }



    public Texture getTexture() {
        return texture;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public float getX() {
        return rectangle.x;
    }

    public float getY() {
        return rectangle.y;
    }
}
