package com.mlnwski.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.mlnwski.game.view.GameScreen;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;

public class Surface {
        ArrayList<Vector2> surface;
        OrthographicCamera camera;
        public Surface(float width, float height, OrthographicCamera camera, float k, World world) {

            this.camera = camera;

            surface = new ArrayList<>();
            for(int i = -300; i < 300; i++){
                surface.add(new Vector2(
                        (float)(width/2+width/200f*i),height/2f+(float)(height/4 -height/4*Math.sin(3*Math.PI/200*i))
                ));
                //surface.add(new Vector2(width/2 + width/200*i,(float)(height-height/10000*Math.pow(i,2))));
            }



            ChainShape chain = new ChainShape();
            Vector2[] vertices = new Vector2[surface.size()];

            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = surface.get(i);
                vertices[i].x = ( vertices[i].x- width/2f)*k+camera.position.x;
                vertices[i].y = (height- vertices[i].y-height/2f)*k+camera.position.y;
            }

            chain.createChain(vertices);

            BodyDef bd = new BodyDef();
            Body body = world.createBody(bd);
            body.createFixture(chain, 1);

        }

}
