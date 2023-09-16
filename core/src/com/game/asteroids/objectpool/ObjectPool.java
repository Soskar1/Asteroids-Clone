package com.game.asteroids.objectpool;

import com.badlogic.gdx.utils.Queue;

public class ObjectPool<T> {
    private final Queue<T> POOL;

    public ObjectPool() {
        POOL = new Queue<>();
    }

    public void enqueue(T object) {
        POOL.addFirst(object);
    }

    public T dequeue() {
        return POOL.removeFirst();
    }

    public int size() {
        return POOL.size;
    }
}
