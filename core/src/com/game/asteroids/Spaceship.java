package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Spaceship extends GameObject {
    private final int speed;
    private final SpaceshipInput spaceshipInput;

    public Spaceship(SpaceshipInput input) {
        Texture texture = new Texture(Gdx.files.internal("Spaceship.png"));
        setTexture(texture);
        setRectangle(new Rectangle(texture.getWidth(), texture.getHeight()));
        speed = 200;

        spaceshipInput = input;
    }

    @Override
    public void update() {
        Move();
        Rotate();
    }

    private void Move() {
        int movementInput = spaceshipInput.getMovementInput();
        Vector2 currentPosition = getPosition();

        currentPosition.x += movementInput * speed * Gdx.graphics.getDeltaTime();
        currentPosition.y += movementInput * speed * Gdx.graphics.getDeltaTime();

        setPosition(currentPosition);
    }

    private void Rotate() {

    }
}