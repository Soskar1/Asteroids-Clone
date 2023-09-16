package com.game.asteroids.flow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.asteroids.*;
import com.game.asteroids.input.SpaceshipInput;
import com.game.asteroids.objectpool.AsteroidObjectPool;
import com.game.asteroids.objectpool.BulletObjectPool;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen implements Screen {
    private final SpriteBatch BATCH;
    private final SpaceshipInput INPUT_PROCESSOR;
    private final OrthographicCamera CAMERA = new OrthographicCamera();
    private final ArrayList<GameObject> GAME_OBJECTS = new ArrayList<>();
    private final static Queue<GameObjectUpdateRequest> GAME_OBJECT_UPDATE_REQUESTS = new Queue<>();
    private final ShapeRenderer SHAPE_RENDERER;
    private final boolean SHOW_SHAPES = false;
    private final int BULLET_POOL_INITIAL_SIZE = 10;
    private final int ASTEROID_POOL_INITIAL_SIZE = 10;
    private final AsteroidObjectPool ASTEROID_OBJECT_POOL = new AsteroidObjectPool(ASTEROID_POOL_INITIAL_SIZE);
    private final BulletObjectPool BULLET_OBJECT_POOL = new BulletObjectPool(BULLET_POOL_INITIAL_SIZE);

    public GameScreen(Asteroids game, final SpaceshipInput inputProcessor) {
        BATCH = game.getSpriteBatch();

        CAMERA.setToOrtho(false, 1280, 720);

        this.INPUT_PROCESSOR = inputProcessor;
        Spaceship spaceship = new Spaceship(inputProcessor, BULLET_OBJECT_POOL);
        GAME_OBJECTS.add(spaceship);
        GAME_OBJECTS.add(new AsteroidSpawner(ASTEROID_OBJECT_POOL, spaceship));

        SHAPE_RENDERER = new ShapeRenderer();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(INPUT_PROCESSOR);
    }

    @Override
    public void render(float delta) {
//        System.out.println("GameObject count: " + GAME_OBJECTS.size());
//        System.out.println("BulletPool count: " + BULLET_OBJECT_POOL.size());

        for (GameObject gameObject : GAME_OBJECTS) {
            gameObject.update(delta);
        }

        ScreenUtils.clear(0, 0, 0, 1);
        CAMERA.update();
        BATCH.setProjectionMatrix(CAMERA.combined);

        BATCH.begin();
        for (GameObject gameObject : GAME_OBJECTS) {
            Sprite sprite = gameObject.getSprite();
            if (sprite != null) {
                sprite.draw(BATCH);
            }
        }
        BATCH.end();

        while (!GAME_OBJECT_UPDATE_REQUESTS.isEmpty()) {
            GameObjectUpdateRequest request = GAME_OBJECT_UPDATE_REQUESTS.removeFirst();
            if (request.OPERATION == GameObjectOperation.ADD) {
                GAME_OBJECTS.add(request.GAME_OBJECT);
            } else {
                GAME_OBJECTS.remove(request.GAME_OBJECT);
            }
        }

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

    public static void requestGameObjectUpdate(GameObject gameObject, GameObjectOperation operation) {
        GAME_OBJECT_UPDATE_REQUESTS.addFirst(new GameObjectUpdateRequest(gameObject, operation));
    }
}
