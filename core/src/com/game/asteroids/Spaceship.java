package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Spaceship extends GameObject {
    private final int moveSpeed;
    private final int rotationSpeed;
    private final SpaceshipInput spaceshipInput;

    public Spaceship(SpaceshipInput input) {
        Texture texture = new Texture(Gdx.files.internal("Spaceship.png"));
        setSprite(texture);
        setRectangle(new Rectangle(texture.getWidth(), texture.getHeight()));

        spaceshipInput = input;
        moveSpeed = 200;
        rotationSpeed = 200;

        class ShootAction implements InputAction {

            @Override
            public void execute() {
                shoot();
            }
        }

        spaceshipInput.registerInputAction(Input.Keys.SPACE, new ShootAction());
    }

    @Override
    public void update(float deltaTime) {
        rotate(deltaTime);
        move(deltaTime);
    }

    private void move(float deltaTime) {
        int movementInput = spaceshipInput.getMovementInput();
        Vector2 movementDirection = rotateVector(new Vector2(0, movementInput));
        Vector2 currentPosition = getPosition();

        currentPosition.x += movementDirection.x * moveSpeed * deltaTime;
        currentPosition.y += movementDirection.y * moveSpeed * deltaTime;

        setPosition(currentPosition);
    }

    private void rotate(float deltaTime) {
        float rotationInput = spaceshipInput.getRotationInput();
        float rotation = getRotation();
        setRotation(rotation + rotationInput * rotationSpeed * deltaTime);
    }

    private void shoot() {
        Vector2 bulletMovementDirection = rotateVector(new Vector2(0,1));
        Bullet bullet = new Bullet();

        Vector2 spaceshipPosition = getPosition();
        bullet.setPosition(new Vector2(spaceshipPosition.x + getSprite().getWidth() / 2, spaceshipPosition.y + getSprite().getWidth() / 2));

        GameScreen.addGameObject(bullet);
    }

    private Vector2 rotateVector(Vector2 source) {
        float rotation = getRotation();
        return source.rotateDeg(rotation).nor();
    }
}