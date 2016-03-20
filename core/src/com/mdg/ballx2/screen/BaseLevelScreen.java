package com.mdg.ballx2.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

/**
 * @author DEEPANKAR
 * @since 20-03-2016.
 */
public class BaseLevelScreen implements Screen {

    float accelX = 0.0f;

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Application.ApplicationType appType = Gdx.app.getType();
        
        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            accelX = Gdx.input.getAccelerometerX();
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) accelX = 5f;
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) accelX = -5f;
        }

        //engine.getSystem(BobSystem.class).setAccelX(accelX);

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
