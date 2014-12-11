package com.mdg.bubbletrouble;

import java.util.ArrayList;

import android.annotation.SuppressLint;
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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Levels extends View{

	
	static float manX = 0,manY;
	static float arrowY, arrowX;
	float manVelocity,accelerometerSensorX = 0;
	boolean shoot = false;
	int isShooting = 0;
	Bitmap man, man_left, man_right,arrow,pause;
	private ShapeDrawable ballDrawable;
	Bitmap background;
	private Rect sourceRect; // the rectangle to be drawn from the animation  bitmap								
	private int frameNr = 4; // number of frames in animation
	private int currentFrame; // the current frame
	private long frameTicker; // the time of the last frame update
	private int framePeriod; // milliseconds between each frame (1000/fps)
	private int spriteWidth; // the width of the sprite to calculate the cut out
								// rectangle
	private int spriteHeight;
	static float gameAreaHeight, gameAreaWidth;
	int currentLevel = 1;
	int touchX,touchY;
	boolean pauseGame = false;
	int maxTime = 0;
	int time = 0;
	//Initializing list to store balls and their radius
	ArrayList<Ball> balls = new ArrayList<Ball>();
	ArrayList<Integer> radius = new ArrayList<Integer>();
	final int BASE_RADIUS = 15;
	int NumberOfBalls;
	int ballX = 100;
	int ballY = 150;
	float ballVelocityX = (float) 2.2;
	double risingFactor = 0.1;
	
    
    SharedPreferences sharedPreferences;
    
   
    public Levels(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		background = BitmapFactory.decodeResource(getResources(),R.drawable.layout2);
		man = BitmapFactory.decodeResource(getResources(), R.drawable.man);
		man_left = BitmapFactory.decodeResource(getResources(),
				R.drawable.man_left);
		man_right = BitmapFactory.decodeResource(getResources(),
				R.drawable.man_right);
		arrow = BitmapFactory.decodeResource(getResources(),
				R.drawable.arrow);
		pause = BitmapFactory.decodeResource(getResources(),
				R.drawable.pause);
		sourceRect = new Rect(0, 0, man_right.getWidth() / 4,
				man_right.getHeight());
		framePeriod = 150;
		frameTicker = 0l;

		initializeGame();
	}

 	@SuppressLint("ClickableViewAccessibility") @Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		int action = event.getAction()& MotionEvent.ACTION_MASK;
		if(action==MotionEvent.ACTION_DOWN){
			shoot = true;
			touchX = (int) event.getX();
			touchY = (int) event.getY();
		}
		return true;
	}
	

    public void initializeGame(){
    	initializeNumberOfBalls(currentLevel);
    	initializeGamePanelArena(currentLevel);
    	initializeTime(currentLevel);
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
        	
        	gameAreaHeight =855*getHeight()/1000;
        	gameAreaWidth = 90*getWidth()/100; 
        }     
        void initializeTime(int level) {
    		switch (level) {
    		case 1:
    			maxTime = 10000;
    			break;
    		case 2:
    			maxTime = 10000;
    			break;
    		case 3:
    			maxTime = 20000;
    			break;
    		case 4:
    			maxTime = 22000;
    			break;
    		}
    		time = maxTime;
    	}
        
        
    public void updateGame(){
    	
    	updateArrowPosition();
    	updateManState();
    	updateBallPosition();
    	updateTimeCounter();
    }
	void updateArrowPosition(){
		if (isShooting == 0) {
			arrowY = gameAreaHeight;
			arrowX = manX + man.getWidth() / 2 - arrow.getWidth()/ 2;
		}
		if (shoot == true) {
			isShooting = 1;
			shoot = false;
		}
		if (isShooting == 1) {
			arrowY = arrowY - 5;

			if (arrowY < 38*gameAreaHeight/100) {
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
		manY = (int) (9*gameAreaHeight/10);
		
		accelerometerSensorX = MainActivity.sensorX;
		manVelocity = 2*accelerometerSensorX;
		if(Math.abs(manVelocity)<1){
			manVelocity = 0;
		}else {
			manVelocity =(manVelocity/Math.abs(manVelocity))*(35/10);
		}
	    manX = manX+manVelocity;
		
		if(manX<11*gameAreaWidth/100){
			manX=11*gameAreaWidth/100;
		}
        if(manX>gameAreaWidth-gameAreaWidth/20){
			manX=gameAreaWidth-gameAreaWidth/20;
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
							}
			
		}
	}
	void updateTimeCounter() {
		time = time - 4;
	}
	
	
	public void renderGame(Canvas c){
		renderArrow(c);
		renderMan(c);
		renderBalls(c);
		renderOtherObjects(c);
	}
	
	void renderArrow(Canvas c){
		Rect Rec2 = new Rect((int) arrowX, (int) arrowY,
				(int) (arrowX + arrow.getWidth()), (int) gameAreaHeight);
		c.drawBitmap(arrow, null, Rec2, null);
		
	}
	void renderMan(Canvas c){
		Rect destRect = new Rect((int) manX,(int) manY,
				(int) (manX + gameAreaWidth/20), (int) (manY +gameAreaHeight/10));
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
			
			ballDrawable.getPaint().setColor(Color.BLACK);
			
			ballDrawable.setBounds((int) balls.get(i).ballX,
					(int) balls.get(i).ballY, 
					(int) balls.get(i).ballX+ radius.get(i),
					(int) balls.get(i).ballY+ radius.get(i));
			ballDrawable.draw(c);
		}
	}
	void renderOtherObjects(Canvas c){
		//Drawing play/pause Button
		Bitmap checkPauseBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pause);			
		Rect r = new Rect((int)(97*gameAreaWidth/100),(int)(11*gameAreaHeight/100),
				(int)(100*gameAreaWidth/100),(int)(18*gameAreaHeight/100));
		c.drawBitmap(pause, null, r, null);
		if(r.contains(touchX, touchY)){
		 //BITMAP TOUCHED
			shoot = false;
			touchX = touchY =0;
			if(pause.sameAs(checkPauseBitmap)){
				pauseGame = true;
				pause = BitmapFactory.decodeResource(getResources(),R.drawable.play);				
			}else{
				pauseGame = false;
				pause = BitmapFactory.decodeResource(getResources(),R.drawable.pause);
			}
		}
		renderingTimer(c);//just displaying timer
	}
	void renderingTimer(Canvas c){
    	if (time < 0) {
			message = "Time Up";
			otherRunTimeFunctionCalls();
		}
		Paint p1 = new Paint();
		p1.setColor(Color.BLACK);
		p1.setStyle(Style.STROKE);
		p1.setStrokeWidth(gameAreaHeight / 61);

		Paint p2 = new Paint();
		p2.setColor(Color.rgb(249, 224, 134));
		p2.setStyle(Style.STROKE);
		p2.setStrokeWidth(gameAreaHeight / 56);
		int Radius = 9 * getHeight() / 100;
		int degrees = -360 * time / maxTime;
		c.drawCircle((int) (88.5 * getWidth() / 100),
				(int) (12 * getHeight() / 100), (int) Radius, p1);

		RectF rectF = new RectF((int) (88.5 * getWidth() / 100 - Radius), 12
				* getHeight() / 100 - Radius,
				(int) (88.5 * getWidth() / 100 + Radius), 12 * getHeight()
						/ 100 + Radius);
		c.drawArc(rectF, 270, degrees, false, p2);

		int glowWidth = (int) (gameAreaHeight / 80);
		Paint p3 = new Paint();
		p3.setStyle(Style.STROKE);
		p3.setStrokeWidth(glowWidth);

		p3.setARGB(100, 249, 224, 134);
		int innerRadius = Radius - glowWidth;
		RectF rectF2 = new RectF((int) (88.5 * getWidth() / 100 - innerRadius),
				12 * getHeight() / 100 - innerRadius,
				(int) (88.5 * getWidth() / 100 + innerRadius), 12 * getHeight()
						/ 100 + innerRadius);
		c.drawArc(rectF2, 270, degrees, false, p3);
		int outerRadius = Radius + glowWidth;
		RectF rectF3 = new RectF((int) (88.5 * getWidth() / 100 - outerRadius),
				12 * getHeight() / 100 - outerRadius,
				(int) (88.5 * getWidth() / 100 + outerRadius), 12 * getHeight()
						/ 100 + outerRadius);
		c.drawArc(rectF3, 270, degrees, false, p3);
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
	
	
	
	 @SuppressLint("DrawAllocation") public void onDraw(Canvas c) {
			// TODO Auto-generated method stub
			super.onDraw(c);

			Rect dest = new Rect(0, 0, getWidth(), getHeight());
			Paint paint = new Paint();
			paint.setFilterBitmap(true);			
			c.drawBitmap(background, null, dest, paint);
			initializeGamePanelArena(currentLevel);
		//----------------------------------------------------------------------------------	
			 //condition to pause the game is applied here
               if(pauseGame == false){
				updateGame();
               }             
				renderGame(c);
				detectCollison();
				
				if(balls.isEmpty()){
					currentLevel++;
					initializeNumberOfBalls(currentLevel);
					
				}
				
				/* 	Typeface tf =Typeface.createFromAsset(getContext().getAssets(), "fonts/chewy.ttf");
		      Paint p = new Paint();
		     p.setTypeface(tf);
		     p.setTextSize(50);
		     c.drawText("Sample text in bold RECOGNITION",11*gameAreaWidth/100, 50*gameAreaHeight/100,p);*/

			
			Paint write1 = new Paint();
			write1.setColor(Color.WHITE);
			write1.setAlpha(80);
		//	c.drawCircle(11*gameAreaWidth/100, 50*gameAreaHeight/100, 5, write1);
			
		
			invalidate();
			
			
		}




	

  // method to save scores or running state of game
  //i.e ballX,ballY,score,currentLevel,manX,manY,life,time,etc.
	// also tested
	
/*	public void SaveInt(String key, int value){
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
		
	}*/




}
