package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Bullet extends GameObject {
    private Vector2 movementDirection;
    private final float SPEED = 750;
    private final BulletObjectPool BULLET_OBJECT_POOL;

    public Bullet(BulletObjectPool objectPool) {
        Texture texture = new Texture(Gdx.files.internal("Bullet.png"));
        setSprite(texture);
        setRectangle(new Rectangle(texture.getWidth(), texture.getHeight()));

        movementDirection = Vector2.Zero;
        BULLET_OBJECT_POOL = objectPool;
    }

    @Override
    public void update(float deltaTime) {
        Vector2 currentPosition = getPosition();
        currentPosition.x += movementDirection.x * SPEED * deltaTime;
        currentPosition.y += movementDirection.y * SPEED * deltaTime;
        setPosition(currentPosition);

        if (currentPosition.x > Gdx.graphics.getWidth() || currentPosition.x < 0 ||
            currentPosition.y > Gdx.graphics.getHeight() || currentPosition.y < 0) {
            BULLET_OBJECT_POOL.enqueue(this);
        }
    }

    public void setMovementDirection(Vector2 movementDirection) {
        this.movementDirection = movementDirection;
    }
}
