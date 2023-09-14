package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Asteroid extends GameObject {
    private Vector2 movementDirection;
    private final int SPEED = 250;
    private final int ROTATION_SPEED = 250;
    private final int OUT_OF_BOUNDS_OFFSET = 50;

    public Asteroid() {
        Texture texture = new Texture(Gdx.files.internal("Asteroid.png"));
        setSprite(texture);
        setRectangle(new Rectangle(texture.getWidth(), texture.getHeight()));
    }

    @Override
    public void update(float deltaTime) {
        Vector2 newPosition = move(deltaTime);
        rotate(deltaTime);

        if (newPosition.x > Gdx.graphics.getWidth() + OUT_OF_BOUNDS_OFFSET || newPosition.x < -OUT_OF_BOUNDS_OFFSET ||
                newPosition.y > Gdx.graphics.getHeight() + OUT_OF_BOUNDS_OFFSET || newPosition.y < -OUT_OF_BOUNDS_OFFSET) {
            setActive(false);
        }
    }

    private Vector2 move(float deltaTime) {
        Vector2 currentPosition = getPosition();
        currentPosition.x += movementDirection.x * SPEED * deltaTime;
        currentPosition.y += movementDirection.y * SPEED * deltaTime;
        setPosition(currentPosition);

        return getPosition();
    }

    private void rotate(float deltaTime) {
        Sprite sprite = getSprite();
        sprite.rotate(ROTATION_SPEED * deltaTime);
    }

    public void setMovementDirection(Vector2 movementDirection) {
        this.movementDirection = movementDirection;
    }
}
