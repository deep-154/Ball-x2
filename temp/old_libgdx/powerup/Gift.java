package com.mdg.ballx2.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.mdg.ballx2.player.Player;

public class Gift {
	
	public Texture giftimage;
	public int giftHeight;
	public int giftWidth;
	private int timeCounter = 400;
	public boolean isCriticalTime = false;
	public boolean timeOut;
	public boolean isTaken;
	public float posX;
	public float posY;
	public int id;
	private int speed = -2;
	
	public Gift(int id,int posX,int posY,int height,int width){
		this.posX = posX;
		this.posY = posY;
		this.id = id;
		this.giftHeight = height;
		this.giftWidth  = width;
	}
	
	public void updateGiftPosition(){
		posY = posY+speed;	
		timeCounter--;
		if(timeCounter<100){
			isCriticalTime = true;
		}
		if(timeCounter<0){
			timeOut = true;
		}
	}
	
	public void detectCollision(Player player){
		collisionEdges();
		collisonPlayer(player);
	}
	
	public void collisionEdges(){	
		if(posY<5){
			posY = 5;
		}
	}
	
    public void collisonPlayer(Player player){
    	if(player.posX+ player.playerWidth > posX && player.posX< posX
    	   && posY  < 0+player.playerHeight){  		
				isTaken = true;
			
    	}
    }
}
