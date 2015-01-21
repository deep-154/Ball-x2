package com.mdg.bubbletrouble;

public class PowerUp {

	float giftX;
	float giftY;
	int id;
	boolean giftTaken=false;
	float manX;
	
	public PowerUp(float a,float b,int c){
		giftX = a;
		giftY = b;
		id = c;
		
	}
	
	public void dropGift(){
		giftY = giftY+2;
		if(giftY>14*Levels.gameAreaHeight/15){
			giftY = 14*Levels.gameAreaHeight/15	;
		}
		manX = Levels.manX;
		if(manX+Levels.gameAreaWidth/20>giftX&&manX<giftX){
			if(giftY+Levels.gameAreaHeight/15>9*Levels.gameAreaHeight/10){
			giftTaken = true;
			}
		}
	}
}
