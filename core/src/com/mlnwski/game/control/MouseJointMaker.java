package com.mlnwski.game.control;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.Body;


public class MouseJointMaker {
    MouseJoint mj;
    World world;

    public MouseJointMaker(World world, Vector2 vec, Body targetBody){
        this.world = world;
        MouseJointDef md = new MouseJointDef();

        vec.x -= targetBody.getPosition().x;
        vec.y -= targetBody.getPosition().y;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(vec);
        bodyDef.linearDamping = 0.0f;
        CircleShape c = new CircleShape();
        c.setRadius(0.0001f);
        c.setPosition(vec);
        Body groundBody = world.createBody(bodyDef);
        //groundBody.createFixture(c,1f);

        md.bodyA = groundBody;
        md.bodyB = targetBody;
        md.target.set(groundBody.getPosition().x+targetBody.getPosition().x,
                groundBody.getPosition().y+targetBody.getPosition().y);
        md.collideConnected = true;
        md.maxForce = 1000.0f * targetBody.getMass();

        mj  = (MouseJoint)world.createJoint(md);
        targetBody.setAwake(true);
    }
    public void deleteJoin(){
        world.destroyJoint(mj);
    }
    public void update(Vector2 vec){
        //vec.x -= mj.getBodyB().getPosition().x;
        //vec.y -= mj.getBodyB().getPosition().y;
        mj.setTarget(vec);
    }

}
