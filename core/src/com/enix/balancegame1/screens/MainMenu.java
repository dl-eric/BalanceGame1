package com.enix.balancegame1.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * Created by Eric on 5/26/2015.
 */
public class MainMenu implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonPlay, buttonExit;
    private BitmapFont white;
    private Label heading;

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);

        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {
        atlas = new TextureAtlas("ui/button.pack");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        white = new BitmapFont(Gdx.files.internal("font/white.fnt"), false);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");

        buttonExit = new TextButton("Exit", textButtonStyle);
        buttonExit.pad(20);

        table.add(buttonExit);
        table.debug();
        table.addActor(table);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }
}
