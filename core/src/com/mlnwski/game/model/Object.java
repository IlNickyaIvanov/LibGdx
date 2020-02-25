package com.mlnwski.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Object {
    public Body body;
    World world;
    public static final float RAD = 1f;
    public boolean checkForDelete(OrthographicCamera camera){
        if(Math.pow(body.getPosition().x,2)+ Math.pow(body.getPosition().y,2)<Math.pow(RAD,2)){
        //if(body.getPosition().y < camera.position.y-camera.viewportHeight/2) {
           world.destroyBody(body);
            return true;
        }
        return false;
    }
    Vector2 pixelsToWorld(Vector2 vec,float k){
        vec.x = (vec.x- Gdx.graphics.getWidth()/2f)*k;
        vec.y = (Gdx.graphics.getHeight()-vec.y-Gdx.graphics.getHeight()/2f)*k;
        return vec;
    }

}
