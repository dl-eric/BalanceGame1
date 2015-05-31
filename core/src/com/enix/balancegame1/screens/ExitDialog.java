package com.enix.balancegame1.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Eric on 5/31/2015.
 */
public class ExitDialog extends Dialog
{

    public ExitDialog(String title, Skin skin) {
        super(title, skin);
    }

    {
        text("Are you sure you want to exit?");
        button("Yes");
    }

    @Override
    protected void result(Object object) {
        Gdx.app.exit();
    }
}
