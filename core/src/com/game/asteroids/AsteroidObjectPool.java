package com.game.asteroids;

public class AsteroidObjectPool extends ObjectPool<Asteroid> {
    public AsteroidObjectPool(int initialSize) {
        for (int i = 0; i < initialSize; ++i) {
            enqueue(new Asteroid(this));
        }
    }

    @Override
    public void enqueue(Asteroid object) {
        object.setActive(false);
        super.enqueue(object);
    }

    @Override
    public Asteroid dequeue() {
        if (size() == 0) {
            return new Asteroid(this);
        }

        Asteroid bullet = super.dequeue();
        bullet.setActive(true);
        return bullet;
    }
}
