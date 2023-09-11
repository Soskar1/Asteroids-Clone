package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.util.ArrayList;

public class GameScreen implements Screen {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final SpaceshipInput inputProcessor;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private final ShapeRenderer shapeRenderer;
    private final boolean showShapes = false;

    public GameScreen(Asteroids game, final SpaceshipInput inputProcessor) {
        batch = game.getSpriteBatch();
        font = game.getBitmapFont();

        camera.setToOrtho(false, 1280, 720);

        this.inputProcessor = inputProcessor;
        gameObjects.add(new Spaceship(inputProcessor));

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(delta);
        }

        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        batch.begin();
        for (GameObject gameObject : gameObjects) {
            gameObject.getSprite().draw(batch);
        }
        batch.end();

        if (!showShapes)
            return;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        for (GameObject gameObject : gameObjects) {
            Rectangle spaceshipRect = gameObject.getRectangle();
            shapeRenderer.rect(spaceshipRect.x, spaceshipRect.y, spaceshipRect.width, spaceshipRect.height);
        }
        shapeRenderer.end();
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

    public static void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
