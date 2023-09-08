package com.game.asteroids;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class GameScreenInputProcessor implements InputProcessor {
    private final Vector2 movementInput;

    public GameScreenInputProcessor() {
        movementInput = new Vector2();
    }

    public Vector2 getMovementInput() {
        return movementInput;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W) {
            ++movementInput.y;
        }

        if (keycode == Input.Keys.A) {
            --movementInput.x;
        }

        if (keycode == Input.Keys.S) {
            --movementInput.y;
        }

        if (keycode == Input.Keys.D) {
            ++movementInput.x;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
            --movementInput.y;
        }

        if (keycode == Input.Keys.A) {
            ++movementInput.x;
        }

        if (keycode == Input.Keys.S) {
            ++movementInput.y;
        }

        if (keycode == Input.Keys.D) {
            --movementInput.x;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
