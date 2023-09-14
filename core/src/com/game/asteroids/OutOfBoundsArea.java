package com.game.asteroids;

import com.badlogic.gdx.math.Vector2;

public class OutOfBoundsArea {
    public final Vector2 START;
    public final Vector2 END;

    public OutOfBoundsArea(Vector2 start, Vector2 end) {
        START = start;
        END = end;
    }
}
