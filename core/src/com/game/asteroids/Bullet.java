package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Bullet extends GameObject{

    private final Vector2 movementDirection;
    private final float speed;

    public Bullet(Vector2 movementDirection) {
        Texture texture = new Texture(Gdx.files.internal("Bullet.png"));
        setSprite(texture);
        setRectangle(new Rectangle(texture.getWidth(), texture.getHeight()));

        this.movementDirection = movementDirection;
        speed = 100;
    }

    @Override
    public void update(float deltaTime) {
        Vector2 currentPosition = getPosition();
        currentPosition.x += movementDirection.x * speed * deltaTime;
        currentPosition.y += movementDirection.y * speed * deltaTime;
        setPosition(currentPosition);
    }
}
