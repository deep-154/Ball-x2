package com.mdg.ballx2.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mdg.ballx2.Ballx2;
import com.mdg.ballx2.Constants;

/**
 * @author DEEPANKAR
 * @since 23-03-2016.
 */
public abstract class BaseScreen implements Screen {

    protected Ballx2 mGame;
    protected SpriteBatch mSpriteBatch;
    protected BitmapFont  mFont;
    protected OrthographicCamera mCamera;

    public BaseScreen(final Ballx2 game){
        mGame = game;
        mSpriteBatch = game.batch;
        mFont = game.font;
    }

    @Override
    public void show() {
        mCamera = new OrthographicCamera(Constants.worldWidth, Constants.worldHeight);
        mCamera.position.set(mCamera.viewportWidth / 2f, mCamera.viewportHeight / 2f, 0);
        mCamera.update();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
