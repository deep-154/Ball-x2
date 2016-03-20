package com.mdg.ballx2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mdg.ballx2.screen.TestScreen;

public class Ballx2 extends Game {

	@Override
	public void create () {

		setScreen(new TestScreen());
	}

}
