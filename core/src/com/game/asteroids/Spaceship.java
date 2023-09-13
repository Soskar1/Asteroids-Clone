package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Spaceship extends GameObject {
    private final int MOVE_SPEED = 200;
    private final int ROTATION_SPEED = 200;
    private final SpaceshipInput SPACESHIP_INPUT;

    public Spaceship(SpaceshipInput input) {
        Texture texture = new Texture(Gdx.files.internal("Spaceship.png"));
        setSprite(texture);
        setRectangle(new Rectangle(texture.getWidth(), texture.getHeight()));

        SPACESHIP_INPUT = input;

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
        Vector2 currentPosition = getPosition();

        currentPosition.x += movementDirection.x * MOVE_SPEED * deltaTime;
        currentPosition.y += movementDirection.y * MOVE_SPEED * deltaTime;

        setPosition(currentPosition);
    }

    private void rotate(float deltaTime) {
        float rotationInput = SPACESHIP_INPUT.getRotationInput();
        float rotation = getRotation();
        setRotation(rotation + rotationInput * ROTATION_SPEED * deltaTime);
    }

    private void shoot() {
        Vector2 bulletMovementDirection = rotateVector(new Vector2(0,1));
        Bullet bullet = new Bullet();
        bullet.setMovementDirection(bulletMovementDirection);

        Vector2 spaceshipPosition = getPosition();
        bullet.setPosition(new Vector2(spaceshipPosition.x + getSprite().getWidth() / 2, spaceshipPosition.y + getSprite().getWidth() / 2));

        GameScreen.addGameObject(bullet);
    }

    private Vector2 rotateVector(Vector2 source) {
        float rotation = getRotation();
        return source.rotateDeg(rotation).nor();
    }
}