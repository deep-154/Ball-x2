package com.mdg.ballx2.main;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game{
	
	public SpriteBatch batch;
    public BitmapFont font;
    

	@Override
	public void create() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        LoadResources.loadResources();
        this.setScreen(new MainMenuScreen(this));	
	}
	
	public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        LoadResources.disposeResources();
    }

}
