package com.enix.balancegame1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.enix.balancegame1.com.enix.balancegame1.entities.Ball;


/**
 * Created by Eric on 5/26/2015.
 */
public class Play implements Screen {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private final float TIMESTEP = 1 / 60f;
    private final int VELOCITYITERATIONS = 8;
    private final int POSITIONITERATION = 3;

    private Ball ball;

    private boolean isOnGround;

    private Array<Body> temporaryBodies = new Array<Body>();

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        ball.update();
        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATION);

        camera.position.x = ball.getBody().getPosition().x > camera.position.x ? ball.getBody().getPosition().x : camera.position.x;
        camera.position.y = ball.getBody().getPosition().y + 2;
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        world.getBodies(temporaryBodies);
        for(Body body : temporaryBodies)
            if(body.getUserData() != null && body.getUserData() instanceof Sprite)
            {
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
                sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
                sprite.draw(batch);
            }
        batch.end();

        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) //Meter to pixel ratio: 80:1
    {
        camera.viewportWidth = width / 80;
        camera.viewportHeight = height / 80;
        camera.update();
    }

    @Override
    public void show()
    {
        world = new World(new Vector2(0, -9.81f), false);
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 80, Gdx.graphics.getHeight() / 80);

        //Ball
        ball = new Ball(world, 0, 8, 1f);
        world.setContactListener(ball);

        Gdx.input.setInputProcessor(new InputMultiplexer(new InputAdapter()
        {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button)
            {
               if(ball.isBallOnGround())
               {
                   ball.getBody().applyLinearImpulse(0, 12, ball.getBody().getWorldCenter().x, ball.getBody().getWorldCenter().y, true);
               }
               return true;
            }
        }, ball));


//      Button pauseButton = new Button(); TODO

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        //Ground
        //Body Definition
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(0, -5);

        //Ground Shape
        ChainShape groundShape = new ChainShape();
        Vector3 upLeft = new Vector3(0, Gdx.graphics.getHeight(), 0), upRight = new Vector3(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0);
        Vector3 botLeft = new Vector3(0, 0, 0), botRight = new Vector3(Gdx.graphics.getWidth(), 0, 0);

        camera.unproject(botLeft);
        camera.unproject(botRight);

        groundShape.createChain(new float[] {botLeft.x, botLeft.y, botRight.x, botRight.y});

        //Fixture Definition
        fixtureDef.shape = groundShape;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;

        world.createBody(bodyDef).createFixture(fixtureDef);

        groundShape.dispose();
    }

    @Override
    public void hide()
    {
        dispose();
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {
        world.dispose();
        debugRenderer.dispose();
        batch.dispose();
    }
}
