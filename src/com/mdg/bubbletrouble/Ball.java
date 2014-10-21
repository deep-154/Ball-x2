package com.mdg.bubbletrouble;



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
	final int BASE_RADIUS = 15;
    
	
	public Ball(int a, int b,double c) {
		// TODO Auto-generated constructor stub
		ballX = a;
		ballY = b;
		velocityY =(float) c;
	}

	void moveBall(float radius) {
		 
			H = Levels.gameAreaHeight;
			W = Levels.gameAreaWidth;
			arrowX = Levels.arrowX;
			arrowY = Levels.arrowY;
			manX = Levels.manX;
		    manY = H - MAN_HEIGHT;
		    ballHit = 0;
		    manHit =0;
		    
			ballX = ballX + velocityX;
			ballY = ballY + velocityY;
			if ((ballX > W - radius ) || (ballX < 0)) {
				velocityX = -velocityX;
			}
			if ((ballY > H - radius)) {
				if (radius > 4 * BASE_RADIUS) {
					velocityY = (float) ((-1)*(6 + (radius / (1.5 * BASE_RADIUS))));
				} else {
					velocityY = (float) (-1 *( 6 +(radius/ BASE_RADIUS)));
				}
				
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
					}
			}
		}
		if (ballX < manX + MAN_WIDTH && ballX + radius > manX
				&& ballY < manY + MAN_HEIGHT && radius + ballY > manY) {
			manHit = 1;
		}

	}

}
