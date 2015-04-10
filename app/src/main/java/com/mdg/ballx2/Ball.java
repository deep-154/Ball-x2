package com.mdg.ballx2;


import com.mdg.ballx2.Levels.BaseLevel;

public class Ball {

	float H = BaseLevel.gameAreaHeight, W = BaseLevel.gameAreaWidth;
	public float ballX = -1;
	public float ballY = -1;
    float unit = H/700;
	float velocityX = 0;
	float velocityY = 0;
	float gravity = (float) 0.2*unit;
	float arrowX, arrowY, manX, manY = 9*H/10;
	public int ballHit = 0;
	public boolean manballCollison= false;
	int BASE_RADIUS = BaseLevel.BASE_RADIUS;
	float arrowWidth,arrowHeight;

    
	
	public Ball(int a, int b,double c,float d) {
		// TODO Auto-generated constructor stub
		ballX = a;
		ballY = b;
		velocityY =(float) c;
		velocityX = d;
	}

	public void moveBall(float radius) {

			arrowX = BaseLevel.arrowX;
			arrowY = BaseLevel.arrowY;
			manX = BaseLevel.manX;
		    ballHit = 0;
		    manballCollison= false;


        ballX = ballX + velocityX;
			ballY = ballY + velocityY;
			if ((ballX > W - radius ) || (ballX < 0)) {
				velocityX = -velocityX;
			}
			if(MainActivity.currentLevel==4){
			if(((ballX>47*W/100-radius)&&ballX<53*W/100)||((ballX<53*W/100)&&ballX>47*W/100)){
				velocityX = -velocityX;
			}
			}
			
			if ((ballY > H - radius)) {
                    if(radius>6*BASE_RADIUS){
                        velocityY =  (-12*unit);
                    }else if(radius==6*BASE_RADIUS){
                        velocityY =  (-11*unit);
                    }else{
                        velocityY =  (-1 *( 6*unit +(radius/ BASE_RADIUS)));
                    }
			}
			
				velocityY = velocityY + gravity;
		
				if(BaseLevel.powerUpArrow)	{
					arrowWidth = 15*W/1000;
					arrowHeight = H;
				}else if(BaseLevel.powerUpTornado){
					arrowWidth = 45*W/1000;
					arrowHeight = arrowY+30*W/1000;
				}
		if (arrowX < ballX + radius &&
				   arrowX +arrowWidth  > ballX &&
				   arrowY < ballY + radius &&
				   arrowHeight> ballY) {
			if(arrowY!=H){
			ballHit = 1;
			if(velocityX>0||velocityX==0){
			velocityX = -24*W/10000;
			}
			if(radius<8*BASE_RADIUS)velocityY =-4*unit;
			else velocityY = -(25/10)*unit;
			}
				}

		
		if(ballY<manY+H/20){
		if (ballX < manX + W/23 && ballX + radius > manX+W/100
				&& ballY < manY + H/10 && radius+ ballY > manY) {
			
			manballCollison= true;
		}else{
			if (ballX < manX + W/25 && ballX + radius > manX+W/100
					&& ballY < manY + H/10 && radius+ ballY > manY) {				
				manballCollison= true;
		}
		}
        
	}

}
}