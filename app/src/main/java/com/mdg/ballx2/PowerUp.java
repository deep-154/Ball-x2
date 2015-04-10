package com.mdg.ballx2;

import android.graphics.Bitmap;

import com.mdg.ballx2.Levels.BaseLevel;

public class PowerUp {

	public float giftX,giftY;
	public int id;
	public boolean giftTaken=false;
	float manX;
	public Bitmap gift;
    int timer = 420;
   public boolean timeGoingToOver = false;
   public boolean istimeUp=false;
	
	public PowerUp(float a,float b,int c,Bitmap d){
		giftX = a;
		giftY = b;
		id = c;
		gift = d;
		
	}
	
	public void dropGift(){
		giftY = giftY+2*(BaseLevel.gameAreaHeight/700);
		if(giftY>14* BaseLevel.gameAreaHeight/15){
			giftY = 14* BaseLevel.gameAreaHeight/15	;
		}
		manX = BaseLevel.manX;
		if(manX+ BaseLevel.gameAreaWidth/20>giftX&&manX<giftX){
			if(giftY+ BaseLevel.gameAreaHeight/15>9* BaseLevel.gameAreaHeight/10){
			giftTaken = true;
			}
		}
        timer--;
        if(timer<80){
           timeGoingToOver=true;
        }
        if(timer<0){
            istimeUp=true;
        }
	}
}
