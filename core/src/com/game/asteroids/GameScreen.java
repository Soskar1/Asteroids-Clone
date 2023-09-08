package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final GameScreenInputProcessor inputProcessor;
    private final Spaceship spaceship;
    private final OrthographicCamera camera;

    public GameScreen(Asteroids game, GameScreenInputProcessor inputProcessor) {
        batch = game.getSpriteBatch();
        font = game.getBitmapFont();

        spaceship = new Spaceship();
        this.inputProcessor = inputProcessor;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(spaceship.getTexture(), spaceship.getX(), spaceship.getY());
        batch.end();

        spaceship.Move(inputProcessor.getMovementInput());
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
