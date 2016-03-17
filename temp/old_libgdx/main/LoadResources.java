package com.mdg.ballx2.main;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class LoadResources{
	
	public static TextureAtlas textureAtlas;
	public static TextureAtlas.AtlasRegion ball;
	public Texture gift;
	public static TextureAtlas.AtlasRegion player;
	public static TextureAtlas.AtlasRegion simpleArrow;
	public static TextureAtlas.AtlasRegion spikeArrow;
	public static TextureAtlas.AtlasRegion tornadoArrow;	
	public static TextureAtlas.AtlasRegion playerMove;
	
	public static void loadResources(){
		textureAtlas = new TextureAtlas("pack.txt");
		ball         = textureAtlas.findRegion("bt_muted");
		player       = textureAtlas.findRegion("player_idle");
		playerMove   = textureAtlas.findRegion("player_move",0);
		simpleArrow  = textureAtlas.findRegion("bullet_simple");
		spikeArrow   = textureAtlas.findRegion("bullet_spike");
		tornadoArrow = textureAtlas.findRegion("bullet_tornado");
		
		
	}
	
	public static void disposeResources(){
		textureAtlas.dispose();
	}

}
