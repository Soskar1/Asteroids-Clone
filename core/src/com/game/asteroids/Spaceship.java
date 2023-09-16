package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.asteroids.flow.GameScreen;
import com.game.asteroids.input.InputAction;
import com.game.asteroids.input.SpaceshipInput;
import com.game.asteroids.objectpool.BulletObjectPool;

import java.awt.*;

public class Spaceship extends GameObject {
    private final int MOVE_SPEED = 200;
    private final int ROTATION_SPEED = 200;
    private final SpaceshipInput SPACESHIP_INPUT;
    private final BulletObjectPool BULLET_OBJECT_POOL;

    public Spaceship(SpaceshipInput input, BulletObjectPool bulletObjectPool) {
        Texture texture = new Texture(Gdx.files.internal("Spaceship.png"));
        setSprite(texture);
        setRectangle(new Rectangle(texture.getWidth(), texture.getHeight()));

        SPACESHIP_INPUT = input;
        BULLET_OBJECT_POOL = bulletObjectPool;

        class ShootAction implements InputAction {

            @Override
            public void execute() {
                shoot();
            }
        }

        SPACESHIP_INPUT.registerInputAction(Input.Keys.SPACE, new ShootAction());
    }

    @Override
    public void update(float deltaTime) {
        rotate(deltaTime);
        move(deltaTime);
    }

    private void move(float deltaTime) {
        int movementInput = SPACESHIP_INPUT.getMovementInput();
        Vector2 movementDirection = rotateVector(new Vector2(0, movementInput));
        Vector2 position = getPosition();

        position.x += movementDirection.x * MOVE_SPEED * deltaTime;
        position.y += movementDirection.y * MOVE_SPEED * deltaTime;

        setPosition(position);
    }

    private void rotate(float deltaTime) {
        float rotationInput = SPACESHIP_INPUT.getRotationInput();
        float rotation = getRotation();
        setRotation(rotation + rotationInput * ROTATION_SPEED * deltaTime);
    }

    private void shoot() {
        Bullet bullet = BULLET_OBJECT_POOL.dequeue();

        Vector2 bulletMovementDirection = rotateVector(new Vector2(0,1));
        bullet.setMovementDirection(bulletMovementDirection);

        Vector2 spaceshipPosition = getPosition();
        bullet.setPosition(new Vector2(spaceshipPosition.x + getSprite().getWidth() / 2, spaceshipPosition.y + getSprite().getWidth() / 2));

        GameScreen.requestGameObjectUpdate(bullet, GameObjectOperation.ADD);
    }

    private Vector2 rotateVector(Vector2 source) {
        float rotation = getRotation();
        return source.rotateDeg(rotation).nor();
    }
}