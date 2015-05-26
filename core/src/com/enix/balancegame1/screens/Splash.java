package com.enix.balancegame1.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sabine on 5/26/2015.
 */
public class Splash
{
    private Sprite splash;
    private SpriteBatch batch;

    @Override
    public void render(float delta)
    {

    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        Texture splashTexture = new Texture(Gdx.files.internal("img/splashscreen.jpeg"));
        splash = new Sprite(splashTexture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void hide()
    {

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

    }
}
