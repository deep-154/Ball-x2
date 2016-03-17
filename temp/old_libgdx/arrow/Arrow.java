package com.mdg.ballx2.arrow;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mdg.ballx2.levels.BaseLevel;
import com.mdg.ballx2.main.LoadResources;

public class Arrow {
	
	public float posX = -1;
	public float posY = 17;      //tip of the arrow not base
	public boolean isArrowTypeChanged = false; 
	public int height;
	public int width;
	public int speed = 0;
	public TextureRegion arrow;
	public boolean[] selectArrowType = new boolean[3];
	int recordType = -1;
	
	
	public Arrow(){
		selectArrowType[0] = true;	
	}
	
	public void setCoordinates(int type,float posX){
		if(recordType!= type){
			arrowTypeChanged();
		}
		recordType = type;
		for(int i=0;i<3;i++){
			selectArrowType[i]= false;
		}
		selectArrowType[type] = true;
		this.posX = posX;
	}
	
	private void arrowTypeChanged(){
		if(selectArrowType[0]){
			speed = 5;
			width= 16;
			height = (int) posY;
			arrow = LoadResources.simpleArrow;
		}
        if(selectArrowType[1]){
        	speed = 5;
			width= 16;
			height = (int) posY;
			arrow = LoadResources.simpleArrow;
		}
        if(selectArrowType[2]){
        	speed = 10;
			width= 5;
			height = (int) posY;
			arrow = LoadResources.tornadoArrow;
		}
	}

	public void updateArrowPosition(){
		if(selectArrowType[0]){			
			height = height+speed;
		}
        if(selectArrowType[1]){   
        	height = height+speed;			
		}
        if(selectArrowType[1]){   
        	posY = posY+speed;			
		}
        	
	}
	
	/*
	 * There are two types of collision with arrow
	 * 1. with top edge of game arena
	 * 2. with balls (which is taken care of in ball class)
	 */
	public void detectCollisionWithWalls(){
		
		if(height>514){
		 height = 17;	
		 BaseLevel.shoot = false;
		}
	}
	

}
