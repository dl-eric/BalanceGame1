package com.enix.balancegame1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Eric on 5/31/2015.
 */
public class MeterGenerator
{
    private final float meter = 1;

    private Body environment;
    private float leftEdge;
    private float rightEdge;
    private float height;
    private float y;

    public MeterGenerator(Body environment, float leftEdge, float rightEdge, float height, float y)
    {
        this.environment = environment;
        this.leftEdge = leftEdge;
        this.rightEdge = rightEdge;
        this.height = height;
        this.y = y;
    }

    public void generate(float topEdge)
    {
        if(y + meter > topEdge)
            return;

        y = topEdge;
        float x = 50;

        ChainShape shape = new ChainShape();

        shape.createChain(new float[]{-x, y, x, y});

        Fixture fixture = environment.createFixture(shape, 0);
        fixture.setSensor(true);
        fixture.setUserData("meter");


        shape.dispose();
    }

    public Body getEnvironment()
    {
        return environment;
    }
}
