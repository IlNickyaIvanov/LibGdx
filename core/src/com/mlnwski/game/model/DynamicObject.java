package com.mlnwski.game.model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class DynamicObject extends  Object {
    Sprite sprite;
    float width,height;
    public DynamicObject(World world, Texture texture, float x, float y, float width, float height){
        this.world = world;
        this.height = height;
        this.width = width;
        sprite = new Sprite(texture,(int)width,(int)height);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x-width/2, y-height/2);

        body = world.createBody(bodyDef);

        //Vector2[] vertices = new Vector2[4];  // An array of 4 vectors
        //vertices[0] = new Vector2(-0.15f, 0.25f);
        //vertices[1] = new Vector2(0.15f, 0f);
        //vertices[2] = new Vector2(0.20f, -0.15f);
        //vertices[3] = new Vector2(-0.10f, -0.10f);

        //PolygonShape box = new PolygonShape();
        //box.setAsBox(width,height);
        //box.set(vertices);

        CircleShape circle = new CircleShape();
        circle.setRadius(height*0.75f);
        //circle.setPosition(new Vector2(-width,height));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;

        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 1f;

        body.createFixture(fixtureDef);
        body.applyForce(new Vector2(0,-3),new Vector2(0,0),true);

        //body.createFixture(box,0.1f);
        //body.createFixture(circle,0.1f);
        //circle.setPosition(new Vector2(width,height));
        //body.createFixture(circle,0.1f);


        //box.dispose();
        circle.dispose();
    }

    public void draw(SpriteBatch batch){
        //sprite.setPosition(body.getPosition().x, body.getPosition().y);
        //batch.draw(sprite,sprite.getX(),sprite.getY());
    }

    public boolean contains(Vector2 vec){
        //Vector2 vec = new Vector2(x,y);
        float a = body.getAngle();
        vec.x = vec.x - body.getPosition().x;
        vec.y = vec.y - body.getPosition().y;

        Vector2 nvec = new Vector2();
        nvec.x = (float) (Math.cos(a)*vec.x - Math.sin(a)*vec.y);
        nvec.y = (float) (Math.sin(a)*vec.x + Math.cos(a)*vec.y);

        return (Math.abs(nvec.x)<width && Math.abs(nvec.y)<height);
    }

    public void applyForce(Vector2 vec2){
        Vector2 vec = new Vector2(vec2.x - body.getPosition().x, vec2.y - body.getPosition().y);
        vec.x *= 0.10;
        vec.y *= 0.10;
        body.applyForce(vec,new Vector2(0,0),true);
    }

}
