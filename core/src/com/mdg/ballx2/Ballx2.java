package com.mdg.ballx2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mdg.ballx2.screens.TestScreen;
import com.mdg.ballx2.screens.start.PlayGameScreen;

public class Ballx2 extends Game {

	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();  //Using Default Arial font

		//Loading required res

		//setting first screen
		setScreen(new PlayGameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.app.log("dispose","in game");
		batch.dispose();
		font.dispose();
	}
}
