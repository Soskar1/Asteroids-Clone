package com.game.asteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class Bullet extends GameObject{

    public Bullet() {
        Texture texture = new Texture(Gdx.files.internal("Bullet.png"));
        setSprite(texture);
        setRectangle(new Rectangle(texture.getWidth(), texture.getHeight()));
    }

    @Override
    public void update(float deltaTime) {

    }
}
