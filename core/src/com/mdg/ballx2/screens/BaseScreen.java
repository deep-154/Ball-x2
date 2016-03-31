package com.mdg.ballx2.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mdg.ballx2.Ballx2;

/**
 * @author DEEPANKAR
 * @since 23-03-2016.
 */
public abstract class BaseScreen implements Screen {

    protected Ballx2 mGame;
    protected OrthographicCamera mCamera;

    public BaseScreen(final Ballx2 game){
        mGame = game;
        mCamera = game.mCamera;
    }

    @Override
    public void show() {

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
