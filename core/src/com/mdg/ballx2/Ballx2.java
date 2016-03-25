package com.mdg.ballx2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mdg.ballx2.screens.start.PlayGameScreen;

public class Ballx2 extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
    public OrthographicCamera mCamera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();  //Using Default Arial font

        // creating camera for world
        mCamera = new OrthographicCamera(com.mdg.ballx2.data.Constants.worldWidth, com.mdg.ballx2.data.Constants.worldHeight);
        mCamera.position.set(mCamera.viewportWidth / 2f, mCamera.viewportHeight / 2f, 0);
        mCamera.update();

		//Load required res

		//setting first screen
		setScreen(new PlayGameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();

		batch.dispose();
		font.dispose();
	}
}
