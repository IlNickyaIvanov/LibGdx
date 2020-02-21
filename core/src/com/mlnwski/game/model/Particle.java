package com.mlnwski.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

public class Particle extends Object{
    public Particle(World world, float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(x,y));
        CircleShape circle = new CircleShape();
        circle.setRadius(0.12f);
        bodyDef.fixedRotation = true;

        body = world.createBody(bodyDef);
        body.createFixture(circle,0f);
        circle.dispose();
    }
}
