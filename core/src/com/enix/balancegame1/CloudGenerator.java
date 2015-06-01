package com.enix.balancegame1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Eric on 5/31/2015.
 */
public class CloudGenerator
{
    private Body environment;
    private float leftEdge, rightEdge, minGap, maxGap, y;
    private Sprite cloudSprite;

    public CloudGenerator(Body environment, float leftEdge, float rightEdge, float minGap, float maxGap)
    {
        this.environment = environment;
        this.leftEdge = leftEdge;
        this.rightEdge = rightEdge;
        this.minGap = minGap;
        this.maxGap = maxGap;
    }

    public void generate(float topEdge)
    {
        if(y + MathUtils.random(minGap, maxGap) > topEdge)
            return;

        y = topEdge;
        float x = MathUtils.random(leftEdge, rightEdge);

        PolygonShape cloudShape = new PolygonShape();
        cloudShape.setAsBox(2, 1.3f, new Vector2(x + 4 / 2, y + 4 / 2), 0);
        cloudSprite = new Sprite(new Texture(Gdx.files.internal("img/cloud1.png")));
        cloudSprite.setSize(2, 1);

        environment.createFixture(cloudShape, 0);
        environment.setUserData(cloudSprite);

        cloudShape.dispose();
    }
}
