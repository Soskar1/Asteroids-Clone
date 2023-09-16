package com.game.asteroids.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;

public class SpaceshipInput implements InputProcessor {
    private int MOVEMENT_INPUT;
    private float ROTATION_INPUT;
    private final HashMap<Integer, InputAction> INPUT_ACTIONS;

    public SpaceshipInput() {
        MOVEMENT_INPUT = 0;
        ROTATION_INPUT = 0;
        INPUT_ACTIONS = new HashMap<>();
    }

    public int getMovementInput() {
        return MOVEMENT_INPUT;
    }
    public float getRotationInput() {
        return ROTATION_INPUT;
    }

    public void registerInputAction(int keyCode, InputAction action) {
        INPUT_ACTIONS.put(keyCode, action);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W) {
            ++MOVEMENT_INPUT;
        }
        if (keycode == Input.Keys.S) {
            --MOVEMENT_INPUT;
        }

        if (keycode == Input.Keys.A) {
            ++ROTATION_INPUT;
        }
        if (keycode == Input.Keys.D) {
            --ROTATION_INPUT;
        }

        if (INPUT_ACTIONS.containsKey(keycode)) {
            INPUT_ACTIONS.get(keycode).execute();
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
            --MOVEMENT_INPUT;
        }
        if (keycode == Input.Keys.S) {
            ++MOVEMENT_INPUT;
        }

        if (keycode == Input.Keys.A) {
            --ROTATION_INPUT;
        }
        if (keycode == Input.Keys.D) {
            ++ROTATION_INPUT;
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
