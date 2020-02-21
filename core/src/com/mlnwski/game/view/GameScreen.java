package com.mlnwski.game.view;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.mlnwski.game.control.MouseJointMaker;
import com.mlnwski.game.model.DynamicObject;
import com.mlnwski.game.model.Pair;
import com.mlnwski.game.model.Particle;
import com.mlnwski.game.model.StaticObject;
import com.mlnwski.game.model.Surface;

import java.util.ArrayList;

import sun.java2d.opengl.WGLSurfaceData;

public class GameScreen extends InputAdapter implements Screen {

    private ArrayList<DynamicObject> boxes;
    private ArrayList<Particle> bridge;
    private StaticObject p1;
    private StaticObject p2;
    private Surface surface;

    private MouseJointMaker mjm;

    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private float k;
    @Override
    public void show() {
        Box2D.init();
        world = new World(new Vector2(0, -7f), false);
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        boxes = new ArrayList<>();
        bridge = new ArrayList<>();

        Gdx.input.setInputProcessor(this);

        resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(0,0,0);

        k = camera.viewportWidth/Gdx.graphics.getWidth();
        //platform = new StaticObject(world,0,-2,1,0.1f);
        surface = new Surface(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera,k,world);

        /*Vector2 vec = new Vector2(-1,Gdx.graphics.getHeight()/2);
        vec = pixelsToWorld(vec);
        p1 = new StaticObject(world,vec.x,vec.y,0.07f,0.07f);
        for (int i = 0; i < 40; i++){
            vec = new Vector2(Gdx.graphics.getWidth()/40*i,Gdx.graphics.getHeight()/2);
            vec = pixelsToWorld(vec);
            bridge.add(new Particle(world,vec.x,vec.y));
            if(i==0)
                new Pair(world,p1,bridge.get(0));
            else
                new Pair(world,bridge.get(i-1),bridge.get(i));
        }
        vec = new Vector2(Gdx.graphics.getWidth()+1,Gdx.graphics.getHeight()/2);
        vec = pixelsToWorld(vec);
        p2 = new StaticObject(world,vec.x,vec.y,0.07f,0.07f);
        new Pair(world,p2,bridge.get(bridge.size()-1));
        */
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, camera.combined);
        if(Gdx.input.isKeyPressed(Input.Keys.Q))
            camera.zoom+=0.01f;
        if(Gdx.input.isKeyPressed(Input.Keys.E))
            camera.zoom-=0.01f;
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            camera.position.x-=0.01f;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            camera.position.x+=0.01f;
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            camera.position.y-=0.01f;
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            camera.position.y+=0.01f;
        camera.update();
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
            k = camera.viewportWidth/Gdx.graphics.getWidth();

            Vector2 vec2 = pixelsToWorld(new Vector2(Gdx.input.getX(),Gdx.input.getY()));

            boxes.add(new DynamicObject(world, new Texture("box.png"),
                    vec2.x, vec2.y, 0.3f, 0.2f) {
                @Override
                public void draw(SpriteBatch batch) {
                    super.draw(batch);
                }
            });
        }
        /*
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            checkClikOnObject(new Vector2(Gdx.input.getX(),Gdx.input.getY()));
        }
        if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mjm!=null){
            mjm.deleteJoin();
            mjm = null;
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mjm!=null){
            mjm.update(pixelsToWorld(new Vector2(Gdx.input.getX(),Gdx.input.getY())));
        }
        */

        //if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){

        // }

        //batch.setProjectionMatrix(camera.combined);
        //batch.begin();
        for(int i = 0; i < boxes.size(); i++){
        //   if(boxes.get(i).bo)boxes.get(i).draw(batch);
            if(boxes.get(i).checkForDelete(camera))
                boxes.remove(i);
        }
        //batch.end();

        world.step(delta, 6, 2);

    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(8f,
                8f*(Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth()));
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    Vector2 worldToPixels(Vector2 vec2){
        vec2.x = (vec2.x - camera.position.x)/(k*camera.zoom) + Gdx.graphics.getWidth()/2f ;
        vec2.y = -((vec2.y - camera.position.y)/(k*camera.zoom) - Gdx.graphics.getHeight() + Gdx.graphics.getHeight()/2f) ;
        return vec2;
    }
    Vector2 pixelsToWorld(Vector2 vec){
        vec.x = (vec.x-Gdx.graphics.getWidth()/2f)*k*camera.zoom+ camera.position.x;
        vec.y = (Gdx.graphics.getHeight()-vec.y-Gdx.graphics.getHeight()/2f)*k*camera.zoom+camera.position.y;
        return vec;
    }

    void checkClikOnObject(Vector2 vec){
        Vector2 vec2 = pixelsToWorld(vec);
        for (int i = 0; i < boxes.size(); i++){
            if(boxes.get(i).contains(vec2)){
                mjm = new MouseJointMaker(world,vec,boxes.get(i).body);
            }
        }
    }

    private QueryCallback queryCallback = new QueryCallback() {
        @Override
        public boolean reportFixture(Fixture fixture) {

            mjm = new MouseJointMaker(world,worldToPixels(
                    new Vector2(Gdx.input.getX(),Gdx.input.getY())),fixture.getBody());
            return false;
        }
    };

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 vec = pixelsToWorld(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        camera.unproject(new Vector3(vec.x,vec.y,0));
        world.QueryAABB(queryCallback,vec.x,vec.y,vec.x,vec.y);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(mjm!=null)
            mjm.deleteJoin();
        mjm = null;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        camera.unproject(new Vector3(screenX,screenY,0));
        if(mjm==null)
            return true;
        mjm.update(worldToPixels(
                (new Vector2(Gdx.input.getX(),Gdx.input.getY()))));
        return true;
    }
}
