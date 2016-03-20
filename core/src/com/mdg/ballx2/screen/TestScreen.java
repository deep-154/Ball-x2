package com.mdg.ballx2.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mdg.ballx2.Ballx2;

/**
 * @author DEEPANKAR
 * @since 20-03-2016.
 */
public class TestScreen implements Screen {

    Ballx2 game;
    Texture img;
    OrthographicCamera camera;

    public TestScreen(final Ballx2 game){
        this.game = game;
        camera = new OrthographicCamera(640, 360);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

    @Override
    public void show() {
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(img, 0, 0,360,360);
        game.batch.end();
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
