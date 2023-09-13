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
import java.util.Iterator;

public class GameScreen implements Screen {
    private final SpriteBatch BATCH;
    private final BitmapFont FONT;
    private final SpaceshipInput INPUT_PROCESSOR;
    private final OrthographicCamera CAMERA = new OrthographicCamera();
    private final static ArrayList<GameObject> GAME_OBJECTS = new ArrayList<>();
    private final ShapeRenderer SHAPE_RENDERER;
    private final boolean SHOW_SHAPES = false;
    private final int BULLET_POOL_INITIAL_SIZE = 10;

    public GameScreen(Asteroids game, final SpaceshipInput inputProcessor) {
        BATCH = game.getSpriteBatch();
        FONT = game.getBitmapFont();

        CAMERA.setToOrtho(false, 1280, 720);

        this.INPUT_PROCESSOR = inputProcessor;
        GAME_OBJECTS.add(new Spaceship(inputProcessor, new BulletObjectPool(BULLET_POOL_INITIAL_SIZE)));

        SHAPE_RENDERER = new ShapeRenderer();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(INPUT_PROCESSOR);
    }

    @Override
    public void render(float delta) {
        updateGameObjects(delta);

        ScreenUtils.clear(0, 0, 0, 1);
        CAMERA.update();
        BATCH.setProjectionMatrix(CAMERA.combined);

        BATCH.begin();
        for (GameObject gameObject : GAME_OBJECTS) {
            gameObject.getSprite().draw(BATCH);
        }
        BATCH.end();

        if (!SHOW_SHAPES) {
            return;
        }

        SHAPE_RENDERER.setProjectionMatrix(CAMERA.combined);
        SHAPE_RENDERER.begin(ShapeRenderer.ShapeType.Line);
        SHAPE_RENDERER.setColor(Color.GREEN);
        for (GameObject gameObject : GAME_OBJECTS) {
            Rectangle rectangle = gameObject.getRectangle();
            SHAPE_RENDERER.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        SHAPE_RENDERER.end();
    }

    private void updateGameObjects(float delta) {
        Iterator<GameObject> iterator = GAME_OBJECTS.iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();

            if (gameObject.isActive()) {
                gameObject.update(delta);
            } else {
                iterator.remove();
            }
        }
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
        GAME_OBJECTS.add(gameObject);
    }
}
