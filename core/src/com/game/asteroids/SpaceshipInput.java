package com.game.asteroids;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;

public class SpaceshipInput implements InputProcessor {
    private int movementInput;
    private float rotationInput;
    private final HashMap<Integer, InputAction> inputActions;

    public SpaceshipInput() {
        movementInput = 0;
        rotationInput = 0;
        inputActions = new HashMap<>();
    }

    public int getMovementInput() {
        return movementInput;
    }
    public float getRotationInput() {
        return rotationInput;
    }

    public void RegisterInputAction(int keyCode, InputAction action) {
        inputActions.put(keyCode, action);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W) {
            ++movementInput;
        }
        if (keycode == Input.Keys.S) {
            --movementInput;
        }

        if (keycode == Input.Keys.A) {
            ++rotationInput;
        }
        if (keycode == Input.Keys.D) {
            --rotationInput;
        }

        if (inputActions.containsKey(keycode)) {
            inputActions.get(keycode).execute();
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
            --movementInput;
        }
        if (keycode == Input.Keys.S) {
            ++movementInput;
        }

        if (keycode == Input.Keys.A) {
            --rotationInput;
        }
        if (keycode == Input.Keys.D) {
            ++rotationInput;
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
