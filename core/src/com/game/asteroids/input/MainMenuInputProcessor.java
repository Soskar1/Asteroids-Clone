package com.game.asteroids.input;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.game.asteroids.flow.GameScreen;

public class MainMenuInputProcessor implements InputProcessor {
    private final GameScreen GAME_SCREEN;
    private final Game GAME;

    public MainMenuInputProcessor(Game game, GameScreen gameScreen) {
        this.GAME = game;
        this.GAME_SCREEN = gameScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        GAME.setScreen(GAME_SCREEN);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
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