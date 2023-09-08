package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    private final BitmapFont font;
    private final SpriteBatch batch;
    private final InputProcessor mainMenuInputProcessor;
    private OrthographicCamera camera;

    public MainMenuScreen(Asteroids game, InputProcessor mainMenuInputProcessor) {
        font = game.getBitmapFont();
        batch = game.getSpriteBatch();
        this.mainMenuInputProcessor = mainMenuInputProcessor;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(mainMenuInputProcessor);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        font.draw(batch, "Asteroids", 560, 500);
        font.draw(batch, "Press any key to start", 520, 360);
        batch.end();
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
