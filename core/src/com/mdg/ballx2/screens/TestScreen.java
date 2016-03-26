package com.mdg.ballx2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mdg.ballx2.Ballx2;

/**
 * @author DEEPANKAR
 * @since 20-03-2016.
 */
public class TestScreen extends BaseScreen {

    Texture img;

    public TestScreen(final Ballx2 game){
        super(game);
    }

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        mGame.batch.setProjectionMatrix(mCamera.combined);
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mGame.batch.begin();
        mGame.batch.draw(img, 0, 0,360,360);
        mGame.batch.end();
    }

}
