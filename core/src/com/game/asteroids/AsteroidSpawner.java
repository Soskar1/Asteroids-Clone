package com.game.asteroids;

import com.badlogic.gdx.math.Vector2;
import com.game.asteroids.flow.GameScreen;
import com.game.asteroids.objectpool.AsteroidObjectPool;

import java.util.Random;

public class AsteroidSpawner extends GameObject {
    private final AsteroidObjectPool OBJECT_POOL;
    private final Spaceship SPACESHIP;
    private final Random random = new Random();
    private final float MAX_SPAWN_TIME = 1;
    private float timer;

    public AsteroidSpawner(AsteroidObjectPool objectPool, Spaceship spaceship) {
        OBJECT_POOL = objectPool;
        SPACESHIP = spaceship;
        timer = MAX_SPAWN_TIME;
    }

    @Override
    public void update(float deltaTime) {
        if (timer <= 0) {
            Asteroid asteroid = OBJECT_POOL.dequeue();
            Vector2 spawnPosition = randomizeAsteroidPosition();
            asteroid.setPosition(spawnPosition);

            Vector2 movementDirection = getDirectionToSpaceship(spawnPosition);
            asteroid.setMovementDirection(movementDirection);

            GameScreen.requestGameObjectUpdate(asteroid, GameObjectOperation.ADD);

            timer = MAX_SPAWN_TIME;
        } else {
            timer -= deltaTime;
        }
    }

    private Vector2 randomizeAsteroidPosition() {
        OutOfBoundsArea area = OutOfBounds.OUT_OF_BOUNDS_AREAS[random.nextInt(OutOfBounds.OUT_OF_BOUNDS_AREAS.length)];

        int randomX = random.nextInt((int)(Math.abs(area.END.x - area.START.x))) + (int)area.START.x;
        int randomY = random.nextInt((int)(Math.abs(area.END.y - area.START.y))) + (int)area.START.y;

        return new Vector2(randomX, randomY);
    }

    private Vector2 getDirectionToSpaceship(Vector2 asteroidPosition) {
        Vector2 spaceshipPosition = SPACESHIP.getPosition();
        float x = spaceshipPosition.x - asteroidPosition.x;
        float y = spaceshipPosition.y - asteroidPosition.y;

        return new Vector2(x, y).nor();
    }
}


