package com.mdg.ballx2.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mdg.ballx2.main.LoadResources;


public class Player {
	
	public float posX = 0;
	public float posY = 17;
	private float velocityX = 0;
	public int playerHeight = 68;
	public int playerWidth = 60;	
	public TextureRegion playerTexture;
	public Sprite playerSprite;
	int i = 0;
	
	// Since player movement is restricted in one dimension only so
	// we only need posX as variable.
	
	public Player(float posX){ 		
		this.posX = posX;
		playerTexture = LoadResources.playerMove;
		playerSprite = new Sprite(playerTexture,0,0,playerTexture.getRegionWidth()/5, playerTexture.getRegionHeight());
		
	}
	
	/* This method update position of player
	   @param controlType : select for type of control i.e. 
	   	0 -> G-Sensor
	   	1 -> Manual with arrows
	 */
	
	public void updatePosition(int controlType){	
		
		velocityX = 2*Gdx.input.getAccelerometerY();		
		if (Math.abs(velocityX) < 1) {
			velocityX = 0;
		} else {
			velocityX = (velocityX / Math.abs(velocityX))*5;
		}
		
		posX= posX + velocityX;	
		
		playerSprite.setRegion((i%10)*playerTexture.getRegionWidth()/5, 0,playerTexture.getRegionWidth()/5, playerTexture.getRegionHeight());
		playerSprite.setBounds(posX, posY, playerWidth, playerHeight);
		i++;
		if(i==49){
			i=0;
		}
		
	}
	
	/* There are three types of collision possible with player
	  1. collision with edges
	  2. collision with gifts to acquire it (it is taken care of in Gift class)
	  3. collision with balls(it is taken care of in Ball class) 	
	*/
	
	public void detectCollision(){
		edgeCollision();
	}
	
	private void edgeCollision(){
		if(posX<0){
			posX=0;
		}
		if(posX>1280-playerWidth){
			posX=1280-playerWidth;
		}
	}

	
	

}
