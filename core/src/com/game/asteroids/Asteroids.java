package com.game.asteroids;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Asteroids extends Game {
	private SpriteBatch batch;
	private BitmapFont font;
	private MainMenuScreen mainMenuScreen;
	private GameScreen gameScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		gameScreen = new GameScreen(this);

		MainMenuInputProcessor inputProcessor = new MainMenuInputProcessor(this, gameScreen);
		mainMenuScreen = new MainMenuScreen(this, inputProcessor);
		setScreen(mainMenuScreen);

		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
	}

	@Override
	public void render () {
		super.render();

		if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
			setScreen(gameScreen);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		mainMenuScreen.dispose();
	}

	public BitmapFont getBitmapFont() {
		return font;
	}

	public SpriteBatch getSpriteBatch() {
		return batch;
	}
}
