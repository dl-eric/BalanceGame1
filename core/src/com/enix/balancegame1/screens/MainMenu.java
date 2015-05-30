package com.enix.balancegame1.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
        stage = new Stage();

        atlas = new TextureAtlas("ui/button.pack");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        white = new BitmapFont(Gdx.files.internal("font/white.fnt"), false);

        //Buttons
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.font = white;
        textButtonStyle.fontColor = Color.BLACK;

        buttonExit = new TextButton("Exit", textButtonStyle);
        buttonExit.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        buttonExit.pad(20);

        //Heading
        LabelStyle headingStyle = new LabelStyle(white, Color.WHITE);
        heading = new Label("Balance", headingStyle);

        //Putting stuff together
        table.add(heading);
        table.row();
        table.add(buttonExit);
        table.debug();          //TODO REMOVE LATER
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void dispose()
    {
        stage.dispose();
        atlas.dispose();
        skin.dispose();
        white.dispose();
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
