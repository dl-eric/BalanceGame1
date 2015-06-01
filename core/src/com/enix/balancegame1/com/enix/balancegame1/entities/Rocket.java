package com.enix.balancegame1.com.enix.balancegame1.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by Eric on 5/30/2015.
 */

public class Rocket extends InputAdapter implements ContactListener
{
    private Body body;
    private Fixture fixture;
    private final float width, height;
    private Vector2 velocity = new Vector2();
    private float jumpPower = 50;
    private Sprite boxSprite;

    private int fuel;

    public Rocket(World world, float x, float y, float height)
    {
        this.width = height / 2.5f;
        this.height = height;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        bodyDef.fixedRotation = true;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        //Fixture Definition
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10f + fuel / 10;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);

        boxSprite = new Sprite(new Texture(Gdx.files.internal("img/rocket.png")));
        boxSprite.setSize(width, height);
        body.setUserData(boxSprite);

        shape.dispose();

        fuel = 100;

    }

    public void update()
    {
        body.applyForceToCenter(velocity, true);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        body.applyLinearImpulse(0, jumpPower, body.getWorldCenter().x, body.getWorldCenter().y, true);
        return true;
    }

    public Body getBody()
    {
        return body;
    }

    public int getFuel() { return fuel; }

    public void setFuel(int x) { fuel = x; }

    @Override
    public void beginContact(Contact contact)
    {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {

    }

    @Override
    public void endContact(Contact contact)
    {
    }
}
