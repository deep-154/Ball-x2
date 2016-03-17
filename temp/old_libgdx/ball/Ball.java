package com.mdg.ballx2.ball;

import com.mdg.ballx2.arrow.Arrow;
import com.mdg.ballx2.obstacles.Wall;
import com.mdg.ballx2.player.Player;

public class Ball {
	
	public float posX = 0;
	public float posY = 0;
	float velocityX = 0;
	public float velocityY = 0;
	int gravity = -1;
	public int radius;
	public boolean isArrowHit = false;
	public boolean isPlayerHit = false;
	
	
	public Ball(int radius,float posX,float posY,float velocityX,float velocityY){
		this.radius = radius;
		this.posX = posX;
		this.posY = posY;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
	
	public void updateBallPosition(){
		
		posX = posX+velocityX;
		posY = posY+velocityY;
		velocityY = velocityY+gravity;		
	}
	
	/*
	 * @param arrow : object of arrow to get its coordinates
	 * @param player: player object to get its coordinates
	 * @param wall  : any obstacles such as wall to get its coordinates(First appeared in level 4)  
	 */
	
	public void detectCollision(Arrow arrow,Player player,Wall wall){
		collisionEdges();      // detect collision from screen edges
		collisionArrow(arrow); // detect collision with arrow
		if(wall!=null){
			collisionWall(wall);
		}
		collisionPlayer(player);
		
	}
	
	public void collisionEdges(){
		if(posX>1280-2*radius||posX<0){
			velocityX = -velocityX;
		}
		if(posY<5){
			velocityY = 30;
		}
	}
	
	public void collisionArrow(Arrow arrow){
		if (arrow.posX < posX + radius && arrow.posX +arrow.width > posX &&
			arrow.posY < posY + radius && arrow.height> posY) {			
			 	isArrowHit = true;
		}
			
	}

	public void collisionWall(Wall wall){
		if(((posX>wall.posX-radius)&& posX<wall.posX+wall.width)||
		   ((posX<wall.posX+wall.width)&& posX>wall.posX)){
			velocityX = -velocityX;
		}
	}

	public void collisionPlayer(Player player){
		
	}
}
