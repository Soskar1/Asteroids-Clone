package com.game.asteroids.objectpool;

import com.game.asteroids.Bullet;

public class BulletObjectPool extends ObjectPool<Bullet> {
    public BulletObjectPool(int initialSize) {
        for (int i = 0; i < initialSize; ++i) {
            enqueue(new Bullet(this));
        }
    }

    @Override
    public void enqueue(Bullet object) {
        object.setActive(false);
        super.enqueue(object);
    }

    @Override
    public Bullet dequeue() {
        if (size() == 0) {
            return new Bullet(this);
        }

        Bullet bullet = super.dequeue();
        bullet.setActive(true);
        return bullet;
    }
}
