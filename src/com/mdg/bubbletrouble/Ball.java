package com.mdg.bubbletrouble;

import android.util.Log;


public class Ball {

	final int MAN_HEIGHT = 69, MAN_WIDTH = 50;

	float H, W;
	public float ballX = -1;
	public float ballY = -1;
	float velocityX = (float) 2.2;
	float velocityY = 4;
	float gravity = (float) 0.2;
	float arrowX, arrowY, manX, manY;
	int ballHit = 0;
	int manHit = 0;
	final int BASE_RADIUS = 20;
    
	
	public Ball(int a, int b,double c) {
		// TODO Auto-generated constructor stub
		ballX = a;
		ballY = b;
		velocityY =(float) c;
	}

	void movingBall(float radius,float vY, double n) {
		float side_strip = (float) (0.0134 * W);
		 
			H = Levels.H;
			W = Levels.W;
			arrowX = Levels.arrowX;
			arrowY = Levels.arrowY;
			manX = Levels.currentX;
		    manY = H - MAN_HEIGHT;
		    ballHit = 0;
		    
		    
			ballX = ballX + velocityX;
			ballY = ballY + velocityY;
			if ((ballX > W - radius - side_strip) || (ballX < side_strip)) {
				velocityX = -velocityX;
			}
			if ((ballY > H - radius)) {

				velocityY = (float) (-1 * vY);
			}
			if (velocityY > 0) {
				velocityY = velocityY + gravity;
			}
			if (velocityY < 0) {
				velocityY = velocityY + gravity;
			}
		

		if (arrowX > ballX && arrowX < ballX + radius) {
			if (arrowY != H) {
				if (ballY + radius > arrowY) {
					ballHit = 1;
					if(velocityX>0){
					velocityX = velocityX*-1;
					}
					velocityY =-4;
					 Log.d("Ball",""+velocityY);
					}
			}
		}
		if (ballX < manX + MAN_WIDTH && ballX + radius > manX
				&& ballY < manY + MAN_HEIGHT && radius + ballY > manY) {
			manHit = 1;
		}

	}

}
