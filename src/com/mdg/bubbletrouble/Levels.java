package com.mdg.bubbletrouble;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Levels extends Fragment{

	static float manX = 0,manY;
	static float arrowY, arrowX;
	float manVelocity,accelerometerSensorX;
	boolean shoot = false;
	int isShooting = 0;
	Bitmap []background = new Bitmap[6] ;
	Bitmap man, man_left, man_right,arrow;
	private ShapeDrawable ballDrawable;

	private Rect sourceRect; // the rectangle to be drawn from the animation  bitmap								
	private int frameNr = 4; // number of frames in animation
	private int currentFrame; // the current frame
	private long frameTicker; // the time of the last frame update
	private int framePeriod; // milliseconds between each frame (1000/fps)
	private int spriteWidth; // the width of the sprite to calculate the cut out
								// rectangle
	private int spriteHeight;
	static float gameAreaHeight, gameAreaWidth;
	ArrayList<Ball> balls = new ArrayList<Ball>();
	ArrayList<Integer> radius = new ArrayList<Integer>();
	final int BASE_RADIUS = 15;
	int NumberOfBalls;
	int ballX = 100;
	int ballY = 150;
	float ballVelocityX = (float) 2.2;
	double risingFactor = 0.1;
    int currentLevel = 1;
    int delay = 0;
    float mid;
    SharedPreferences sharedPreferences;
    
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return new myView(getActivity());

	}
    
    public void getCoordinate(float values, boolean checkShoot) {
		// TODO Auto-generated method stub
		accelerometerSensorX=values;
		shoot = checkShoot;
		
	}
    
    public void initializeGame(){
    	initializeNumberOfBalls(currentLevel);
    	initializeGamePanelArena(currentLevel);
    	initializeListOfPowers(currentLevel);
    }
    
    
    	void initializeNumberOfBalls(int level) {

		switch (level) {
		case 1:
			NumberOfBalls = 1;
			Ball b1 = new Ball(100,150,risingFactor);
			balls.add(b1);
			radius.add(2 * BASE_RADIUS);
			break;
		
		case 2:
			NumberOfBalls = 2;
			Ball b2 = new Ball(100,150,risingFactor);
			Ball b3 = new Ball(600,150,risingFactor);
			balls.add(b2);
			balls.add(b3);
			radius.add(4 * BASE_RADIUS);
			radius.add(2 * BASE_RADIUS);
			break;
		
		case 3:
			NumberOfBalls = 1;
			Ball b4 = new Ball(100,200,risingFactor);
			balls.add(b4);
			radius.add(8 * BASE_RADIUS);
			break;
		case 4:
			NumberOfBalls = 3;
			Ball b5 = new Ball(100,150,risingFactor);
			Ball b6 = new Ball(600,100,risingFactor);
			Ball b7 = new Ball(1000,200,risingFactor);
			balls.add(b5);
			balls.add(b6);
			balls.add(b7);
			radius.add(4 * BASE_RADIUS);
			radius.add(2 * BASE_RADIUS);
			radius.add(8 * BASE_RADIUS);
			break;
		}
		
		
	}
        void initializeGamePanelArena(int level){
        	
        	DisplayMetrics displaymetrics = new DisplayMetrics();
        	getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        	gameAreaHeight = (float) ((0.73)*(displaymetrics.heightPixels));
        	gameAreaWidth = displaymetrics.widthPixels; 
        }
        void initializeListOfPowers(int level){
        }
       
        
        
    public void updateGame(){
    	
    	updateArrowPosition();
    	updateManState();
    	updateBallPosition();
    }
	void updateArrowPosition(){
		if (isShooting == 0) {
			arrowY = gameAreaHeight;
			arrowX = manX + man.getWidth() / 2 - arrow.getWidth()/ 2;
		}
		if (shoot == true) {
			isShooting = 1;
		}
		if (isShooting == 1) {
			arrowY = arrowY - 8;

			if (arrowY < 0) {
				arrowY = gameAreaHeight;
				isShooting = 0;
			}
		}
	}
	void updateManState(){
		
		long gameTime = System.currentTimeMillis();
		if (gameTime > frameTicker + framePeriod) {
			frameTicker = gameTime;
			// incrementing the frame
			currentFrame++;
			if (currentFrame >= frameNr) {
				currentFrame = 0;
			}
		}
		// defining the rectangle to cut out sprite
		sourceRect.left = currentFrame * spriteWidth;
		sourceRect.right = sourceRect.left + spriteWidth;

		spriteHeight = man.getHeight();
		spriteWidth = man_right.getWidth() / 4;
		manY = (int) (gameAreaHeight - man.getHeight());
		mid = gameAreaWidth / 2 - man.getWidth() / 2;
		manVelocity = 2*accelerometerSensorX;
		if(Math.abs(manVelocity)<1){
			manVelocity = 0;
		}
		if(Math.abs(manVelocity)>3){
			manVelocity =(manVelocity/Math.abs(manVelocity))*3;
		}
		manX = manX+manVelocity;
		if(manX<0){
			manX=0;
		}
        if(manX>gameAreaWidth-man.getWidth()){
			manX=gameAreaWidth-man.getWidth();
		}
	}
	void updateBallPosition(){
		
		for (int i = 0; i < balls.size(); i++) {		
			balls.get(i).moveBall(radius.get(i));
			
			if (balls.get(i).manHit == 1) {
				balls.clear();
				radius.clear();
				isShooting=0;
				initializeNumberOfBalls(currentLevel);
				delay=0;
			}
			
		}
	}
	
	
	
	public void renderGame(Canvas c){
		renderArrow(c);
		renderMan(c);
		renderBalls(c);
	}
	
	void renderArrow(Canvas c){
		Rect Rec2 = new Rect((int) arrowX, (int) arrowY,
				(int) (arrowX + arrow.getWidth()), (int) gameAreaHeight);
		c.drawBitmap(arrow, null, Rec2, null);
		
	}
	void renderMan(Canvas c){
		Rect destRect = new Rect((int) manX,(int) manY,
				(int) (manX + spriteWidth), (int)manY + spriteHeight);
		if(manVelocity>0){
			c.drawBitmap(man_right,sourceRect, destRect, null);
		}else
		if(manVelocity<0){
			c.drawBitmap(man_left,sourceRect, destRect, null);
		}else c.drawBitmap(man,null, destRect, null);
	}
	void renderBalls(Canvas c){
		
		ballDrawable = new ShapeDrawable(new OvalShape());
			
		for (int i = 0; i < balls.size(); i++) {
			
			ballDrawable.getPaint().setColor(Color.GREEN);
			
			ballDrawable.setBounds((int) balls.get(i).ballX,
					(int) balls.get(i).ballY, 
					(int) balls.get(i).ballX+ radius.get(i),
					(int) balls.get(i).ballY+ radius.get(i));
			ballDrawable.draw(c);
		}
	}
	
	public void detectCollison(){
		collisonBallArrow();
	}
	
	void collisonBallArrow(){
		for(int i = 0;i<balls.size();i++){
		if (balls.get(i).ballHit == 1) {
			radius.set(i, radius.get(i) / 2);
			arrowY = gameAreaHeight;
			isShooting = 0;

			if (radius.get(i) < BASE_RADIUS) {
				balls.remove(i);
				radius.remove(i);
			} else {
				ballX = (int) (balls.get(i).ballX + radius.get(i));
				ballY = (int) balls.get(i).ballY;
				risingFactor = -4;
				Ball q = new Ball(ballX,ballY,risingFactor);
				balls.add(q);
				radius.add(radius.get(i));
				
			}
			
			//generating bonous powerups
		}	
		}	
	}
	
	
	
	
	//Defining View class
	class myView extends View {
		
		public myView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			background[0] = BitmapFactory.decodeResource(getResources(),
					R.drawable.lv1);
			background[1] = BitmapFactory.decodeResource(getResources(),
					R.drawable.lv2);
			background[2] = BitmapFactory.decodeResource(getResources(),
					R.drawable.lv3);
			background[3] = BitmapFactory.decodeResource(getResources(),
					R.drawable.lv4);
			background[4] = BitmapFactory.decodeResource(getResources(),
					R.drawable.lv5);
			background[5] = BitmapFactory.decodeResource(getResources(),
					R.drawable.lv6);
			man = BitmapFactory.decodeResource(getResources(), R.drawable.man);
			man_left = BitmapFactory.decodeResource(getResources(),
					R.drawable.man_left);
			man_right = BitmapFactory.decodeResource(getResources(),
					R.drawable.man_right);
			arrow = BitmapFactory.decodeResource(getResources(),
					R.drawable.arrow);
			sourceRect = new Rect(0, 0, man_right.getWidth() / 4,
					man_right.getHeight());
			framePeriod = 150;
			frameTicker = 0l;

			initializeGame();
		}

		
		
		@SuppressLint("DrawAllocation") @Override
		protected void onDraw(Canvas c) {
			// TODO Auto-generated method stub
			super.onDraw(c);

			Rect dest = new Rect(0, 0, getWidth(), getHeight());
			Paint paint = new Paint();
			paint.setFilterBitmap(true);
			
			c.drawBitmap(background[currentLevel-1], null, dest, paint);
			// ---------------------------------------------

			
			
			if (delay > 100) {
               
				updateGame(); //condition can be applied here to pause the game
				              //tested but not applied here 
				renderGame(c);
				detectCollison();
				
				if(balls.isEmpty()){
					currentLevel++;
					initializeNumberOfBalls(currentLevel);
					delay=0;
				
				}

			}

			if (delay < 102) {
				manX = mid;
				Paint write = new Paint();
				write.setColor(Color.rgb(253, 238, 0));
				write.setTextSize(50);
				write.setStrokeWidth(30);
				c.drawText("Get Ready", 4 * gameAreaWidth / 10, 6 * gameAreaHeight / 10, write);
				delay++;
			}

			invalidate();
			
			
		}
	}

  // method to save scores or running state of game
  //i.e ballX,ballY,score,currentLevel,manX,manY,life,time,etc.
	// also tested
	
	public void SaveInt(String key, int value){
		sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
	       SharedPreferences.Editor editor = sharedPreferences.edit();
	       editor.putInt(key, value);
	       editor.commit();
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	//	SaveInt("currentLevel", currentLevel);
		
	}





	

	



	

	

}
