package com.game.asteroids;

import com.badlogic.gdx.Gdx;
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
    }

    @Override
    public void update(float deltaTime) {
        rotate(deltaTime);
        move(deltaTime);
    }

    private void move(float deltaTime) {
        Vector2 rotatedMovement = getRotatedMovement();
        Vector2 currentPosition = getPosition();

        currentPosition.x += rotatedMovement.x * moveSpeed * deltaTime;
        currentPosition.y += rotatedMovement.y * moveSpeed * deltaTime;

        setPosition(currentPosition);
    }

    private void rotate(float deltaTime) {
        float rotationInput = spaceshipInput.getRotationInput();
        float rotation = getRotation();
        setRotation(rotation + rotationInput * rotationSpeed * deltaTime);
    }

    private Vector2 getRotatedMovement() {
        int movementInput = spaceshipInput.getMovementInput();
        float rotation = getRotation();

        Vector2 result = new Vector2(0, movementInput);
        result.rotateDeg(rotation);

        return result.nor();
    }
}