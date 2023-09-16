package com.game.asteroids.flow;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.asteroids.input.MainMenuInputProcessor;
import com.game.asteroids.input.SpaceshipInput;

public class Asteroids extends Game {
	private SpriteBatch batch;
	private BitmapFont font;
	private MainMenuScreen mainMenuScreen;
	private GameScreen gameScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		SpaceshipInput gameScreenInputProcessor = new SpaceshipInput();
		gameScreen = new GameScreen(this, gameScreenInputProcessor);

		MainMenuInputProcessor inputProcessor = new MainMenuInputProcessor(this, gameScreen);
		mainMenuScreen = new MainMenuScreen(this, inputProcessor);
		setScreen(mainMenuScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		mainMenuScreen.dispose();
		gameScreen.dispose();
	}

	public BitmapFont getBitmapFont() {
		return font;
	}

	public SpriteBatch getSpriteBatch() {
		return batch;
	}
}