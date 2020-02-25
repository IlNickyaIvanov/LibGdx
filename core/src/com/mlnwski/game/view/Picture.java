package com.mlnwski.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Picture {
    float radius;
    Texture texture;
    SpriteBatch batch;
    Sprite sprite;
    Picture(SpriteBatch spriteBatch, Texture texture,float r){
        sprite = new Sprite(texture);
        sprite.setSize(100,100);
        sprite.setPosition(0,0);
        batch = spriteBatch;
    }
    public void draw(SpriteBatch batch,float zoom, Vector2 vec){
        sprite.setPosition(vec.x,vec.y);

        sprite.draw(batch);
    }
}
