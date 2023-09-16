package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.game.asteroids.objectpool.AsteroidObjectPool;

import java.awt.*;

public class Asteroid extends GameObject {
    private Vector2 movementDirection;
    private final int SPEED = 100;
    private final int ROTATION_SPEED = 250;
    private final AsteroidObjectPool OBJECT_POOL;

    public Asteroid(AsteroidObjectPool objectPool) {
        Texture texture = new Texture(Gdx.files.internal("Asteroid.png"));
        setSprite(texture);
        setRectangle(new Rectangle(texture.getWidth(), texture.getHeight()));

        OBJECT_POOL = objectPool;
    }

    @Override
    public void update(float deltaTime) {
        move(deltaTime);
        rotate(deltaTime);

        if (OutOfBounds.isInOutOfBounds(getPosition())) {
            OBJECT_POOL.enqueue(this);
        }
    }

    private void move(float deltaTime) {
        Vector2 position = getPosition();
        position.x += movementDirection.x * SPEED * deltaTime;
        position.y += movementDirection.y * SPEED * deltaTime;
        setPosition(position);
    }

    private void rotate(float deltaTime) {
        Sprite sprite = getSprite();
        sprite.rotate(ROTATION_SPEED * deltaTime);
    }

    public void setMovementDirection(Vector2 movementDirection) {
        this.movementDirection = movementDirection;
    }
}
