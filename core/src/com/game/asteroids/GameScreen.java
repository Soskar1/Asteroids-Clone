package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final GameScreenInputProcessor inputProcessor;
    private final Spaceship spaceship;
    private final OrthographicCamera camera;

    public GameScreen(Asteroids game, final GameScreenInputProcessor inputProcessor) {
        batch = game.getSpriteBatch();
        font = game.getBitmapFont();

        spaceship = new Spaceship();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        this.inputProcessor = inputProcessor;
        class MoveAction implements InputAction {

            @Override
            public void execute() {
                spaceship.Move(inputProcessor.getMovementInput());
            }
        }

        MoveAction moveAction = new MoveAction();
        inputProcessor.RegisterInputAction(Input.Keys.W, moveAction);
        inputProcessor.RegisterInputAction(Input.Keys.A, moveAction);
        inputProcessor.RegisterInputAction(Input.Keys.S, moveAction);
        inputProcessor.RegisterInputAction(Input.Keys.D, moveAction);
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

        Sprite spaceshipSprite = new Sprite(spaceship.getTexture());
        spaceshipSprite.setPosition(spaceship.getX(), spaceship.getY());
        //spaceshipSprite.setRotation();

        batch.begin();
        spaceshipSprite.draw(batch);
        batch.end();

        //spaceship.Move(inputProcessor.getMovementInput());
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
