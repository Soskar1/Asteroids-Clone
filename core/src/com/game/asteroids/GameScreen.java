package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final SpaceshipInput inputProcessor;
    private final OrthographicCamera camera;
    private final ArrayList<GameObject> gameObjects;

    public GameScreen(Asteroids game, final SpaceshipInput inputProcessor) {
        batch = game.getSpriteBatch();
        font = game.getBitmapFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        gameObjects = new ArrayList<GameObject>();

        this.inputProcessor = inputProcessor;
        gameObjects.add(new Spaceship(inputProcessor));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }

        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for (GameObject gameObject : gameObjects) {
            gameObject.getSprite().draw(batch);
        }
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
