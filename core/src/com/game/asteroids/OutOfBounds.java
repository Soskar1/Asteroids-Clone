package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class OutOfBounds {
    public static final int OUT_OF_BOUNDS_OFFSET = 50;
    public static final OutOfBoundsArea[] OUT_OF_BOUNDS_AREAS = new OutOfBoundsArea[] {
            new OutOfBoundsArea(new Vector2(-OUT_OF_BOUNDS_OFFSET, Gdx.graphics.getHeight()), new Vector2(Gdx.graphics.getWidth() + OUT_OF_BOUNDS_OFFSET, Gdx.graphics.getHeight() + OUT_OF_BOUNDS_OFFSET)),
            new OutOfBoundsArea(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + OUT_OF_BOUNDS_OFFSET), new Vector2(Gdx.graphics.getWidth() + OUT_OF_BOUNDS_OFFSET, -OUT_OF_BOUNDS_OFFSET)),
            new OutOfBoundsArea(new Vector2(-OUT_OF_BOUNDS_OFFSET, -OUT_OF_BOUNDS_OFFSET), new Vector2(Gdx.graphics.getWidth() + OUT_OF_BOUNDS_OFFSET, 0)),
            new OutOfBoundsArea(new Vector2(-OUT_OF_BOUNDS_OFFSET, -OUT_OF_BOUNDS_OFFSET), new Vector2(0, Gdx.graphics.getHeight() + OUT_OF_BOUNDS_OFFSET))
    };

    public static boolean isInOutOfBounds(Vector2 position) {
        return position.x > Gdx.graphics.getWidth() + OUT_OF_BOUNDS_OFFSET || position.x < -OUT_OF_BOUNDS_OFFSET ||
                position.y > Gdx.graphics.getHeight() + OUT_OF_BOUNDS_OFFSET || position.y < -OUT_OF_BOUNDS_OFFSET;
    }
}
