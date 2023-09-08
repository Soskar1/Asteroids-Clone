package com.game.asteroids;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Asteroids extends Game {
	private SpriteBatch batch;
	private BitmapFont font;
	private MainMenuScreen mainMenuScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		mainMenuScreen = new MainMenuScreen(this);
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
	}

	public BitmapFont getBitmapFont() {
		return font;
	}

	public SpriteBatch getSpriteBatch() {
		return batch;
	}
}
