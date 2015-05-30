package com.enix.balancegame1;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.enix.balancegame1.screens.MainMenu;
import com.enix.balancegame1.screens.Splash;

public class BalanceGame extends Game {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create ()
	{
		setScreen(new MainMenu());
	}

	@Override
	public void dispose()
	{
		super.dispose();
	}

	@Override
	public void render()
	{
	super.render();
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
	}

	@Override
	public void pause()
	{
		super.pause();
	}

	@Override
	public void resume()
	{
		super.resume();
	}

}
