package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.asteroids.flow.GameScreen;
import com.game.asteroids.objectpool.BulletObjectPool;

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
        move(deltaTime);

        if (OutOfBounds.isInOutOfBounds(getPosition())) {
            disable();
        }
    }

    @Override
    public void disable() {
        GameScreen.requestGameObjectUpdate(this, GameObjectOperation.REMOVE);
        BULLET_OBJECT_POOL.enqueue(this);
    }

    @Override
    public void onCollisionEnter(GameObject gameObject) {
        if (gameObject instanceof Asteroid) {
            disable();
            gameObject.disable();
        }
    }

    private void move(float deltaTime) {
        Vector2 position = getPosition();
        position.x += movementDirection.x * SPEED * deltaTime;
        position.y += movementDirection.y * SPEED * deltaTime;
        setPosition(position);
    }

    public void setMovementDirection(Vector2 movementDirection) {
        this.movementDirection = movementDirection;
    }
}
