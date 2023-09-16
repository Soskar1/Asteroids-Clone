package com.game.asteroids.flow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.asteroids.input.MainMenuInputProcessor;

public class MainMenuScreen implements Screen {
    private final BitmapFont FONT;
    private final SpriteBatch BATCH;
    private final MainMenuInputProcessor MAIN_MENU_INPUT;
    private final OrthographicCamera CAMERA;

    public MainMenuScreen(Asteroids game, MainMenuInputProcessor inputProcessor) {
        FONT = game.getBitmapFont();
        BATCH = game.getSpriteBatch();
        MAIN_MENU_INPUT = inputProcessor;

        CAMERA = new OrthographicCamera();
        CAMERA.setToOrtho(false, 1280, 720);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(MAIN_MENU_INPUT);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        BATCH.begin();
        FONT.draw(BATCH, "Asteroids", 560, 500);
        FONT.draw(BATCH, "Press any key to start", 520, 360);
        BATCH.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}