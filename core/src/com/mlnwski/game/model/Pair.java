package com.mlnwski.game.model;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;

public class Pair {

    Particle a;
    Particle b;
    StaticObject c;

    public Pair(World world,Particle a, Particle b){
        DistanceJointDef djd = new DistanceJointDef();
        djd.bodyA = a.body;
        djd.bodyB = b.body;
        djd.length  = 0.12f;
        djd.frequencyHz  = 0f;
        djd.dampingRatio = 0f;
        DistanceJoint dj = (DistanceJoint) world.createJoint(djd);
    }
    public Pair(World world, StaticObject a, Particle b){
        DistanceJointDef djd = new DistanceJointDef();
        djd.bodyA = a.body;
        djd.bodyB = b.body;
        djd.length  = 0.12f;
        djd.frequencyHz  = 0f;
        djd.dampingRatio = 0f;
        DistanceJoint dj = (DistanceJoint) world.createJoint(djd);
    }

}
