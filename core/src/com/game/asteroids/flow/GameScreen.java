package com.game.asteroids.flow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.asteroids.*;
import com.game.asteroids.input.SpaceshipInput;
import com.game.asteroids.objectpool.AsteroidObjectPool;
import com.game.asteroids.objectpool.BulletObjectPool;

import java.awt.*;
import java.util.ArrayList;

public class GameScreen implements Screen {
    private final SpriteBatch BATCH;
    private final BitmapFont FONT;
    private final SpaceshipInput INPUT_PROCESSOR;
    private final OrthographicCamera CAMERA = new OrthographicCamera();
    private final ArrayList<GameObject> GAME_OBJECTS = new ArrayList<>();
    private final static Queue<GameObjectUpdateRequest> GAME_OBJECT_UPDATE_REQUESTS = new Queue<>();
    private final int BULLET_POOL_INITIAL_SIZE = 20;
    private final int ASTEROID_POOL_INITIAL_SIZE = 20;
    private static boolean gameOver = false;

    private final ShapeRenderer SHAPE_RENDERER;
    private final boolean SHOW_SHAPES = false;

    public GameScreen(Asteroids game, final SpaceshipInput inputProcessor) {
        BATCH = game.getSpriteBatch();
        FONT = game.getBitmapFont();
        CAMERA.setToOrtho(false, 1280, 720);
        this.INPUT_PROCESSOR = inputProcessor;
        SHAPE_RENDERER = new ShapeRenderer();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(INPUT_PROCESSOR);
        initialize();
    }

    private void initialize() {
        Spaceship spaceship = new Spaceship(INPUT_PROCESSOR, new BulletObjectPool(BULLET_POOL_INITIAL_SIZE));
        spaceship.setPosition(new Vector2((float) Gdx.graphics.getWidth() / 2 - 16, (float) Gdx.graphics.getHeight() / 2 - 16));
        GAME_OBJECTS.add(spaceship);
        GAME_OBJECTS.add(new AsteroidSpawner(new AsteroidObjectPool(ASTEROID_POOL_INITIAL_SIZE), spaceship));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        CAMERA.update();
        BATCH.setProjectionMatrix(CAMERA.combined);

        if (gameOver) {
            BATCH.begin();
            FONT.draw(BATCH, "GAME OVER", 565, 360);
            FONT.draw(BATCH, "Press SPACE to restart", 540, 340);
            BATCH.end();

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                restartGame();
            }

            return;
        }

        for (GameObject gameObject : GAME_OBJECTS) {
            if (gameObject.isActive()) {
                gameObject.update(delta);
            }
        }

        detectCollisions();

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

            if (rectangle != null) {
                SHAPE_RENDERER.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
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
        GAME_OBJECT_UPDATE_REQUESTS.clear();
        GAME_OBJECTS.clear();
    }

    public static void requestGameObjectUpdate(GameObject gameObject, GameObjectOperation operation) {
        GAME_OBJECT_UPDATE_REQUESTS.addFirst(new GameObjectUpdateRequest(gameObject, operation));
    }

    private void detectCollisions() {
        for (GameObject gameObject : GAME_OBJECTS) {
            Rectangle r1 = gameObject.getRectangle();

            if (r1 == null) {
                continue;
            }

            for (GameObject other : GAME_OBJECTS) {
                Rectangle r2 = other.getRectangle();

                if (r2 == null) {
                    continue;
                }

                if (gameObject != other) {
                    if (r1.intersects(r2)) {
                        gameObject.onCollisionEnter(other);
                    }
                }
            }
        }
    }

    public static void endGame() {
        gameOver = true;
    }

    private void restartGame() {
        GAME_OBJECTS.clear();
        GAME_OBJECT_UPDATE_REQUESTS.clear();

        initialize();
        gameOver = false;
    }
}