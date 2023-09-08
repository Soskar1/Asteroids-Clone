package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Spaceship {
    private final Texture texture;
    private final Rectangle rectangle;
    private final int speed;
    public Boolean continuousMove;

    public Spaceship() {
        texture = new Texture(Gdx.files.internal("Spaceship.png"));
        rectangle = new Rectangle(texture.getWidth(), texture.getHeight());
        speed = 200;
    }

    public void Move(Vector2 direction) {
        rectangle.x += direction.x * speed * Gdx.graphics.getDeltaTime();
        rectangle.y += direction.y * speed * Gdx.graphics.getDeltaTime();
        //Gdx.graphics.requestRendering();
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