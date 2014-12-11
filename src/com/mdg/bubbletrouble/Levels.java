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

	
	static float manX = -100, manY = -100;
	static float arrowY = -1000, arrowX = -100;
	float manVelocity, accelerometerSensorX = -1;
	boolean shoot = false, manMove = true, otherShoot = false;
	int isShooting = 0;
	Bitmap man, man_left, man_right, arrow, pause, life;
	private ShapeDrawable ballDrawable;
	Bitmap background, directionArrowLeft, directionArrowRight;
	private Rect sourceRect; // the rectangle to be drawn from the animation
								// bitmap
	private int frameNr = 4; // number of frames in animation
	private int currentFrame; // the current frame
	private long frameTicker; // the time of the last frame update
	private int framePeriod; // milliseconds between each frame (1000/fps)
	private int spriteWidth; // the width of the sprite to calculate the cut out
								// rectangle
	static float gameAreaHeight, gameAreaWidth;
	int currentLevel = 1;
	int[] touchX = new int[2];
	int[] touchY = new int[2];
	boolean pauseGame = false;
	int maxTime = 0;
	int time = 0;
	int numberOfLife = 5;
	String message = null;
	int score = 0;
	// Initializing list to store balls and their radius
	ArrayList<Ball> balls = new ArrayList<Ball>();
	ArrayList<Integer> radius = new ArrayList<Integer>();
	static int BASE_RADIUS;
	float ballVelocityX = (float) 2;
	double risingFactor = 0.1;
	int counter = 0;
	Activity act;
    
   
    public Levels(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		act = activity;
		background = BitmapFactory.decodeResource(getResources(),
				R.drawable.layout4);
		man = BitmapFactory.decodeResource(getResources(), R.drawable.man);
		man_left = BitmapFactory.decodeResource(getResources(),
				R.drawable.man_left);
		man_right = BitmapFactory.decodeResource(getResources(),
				R.drawable.man_right);
		arrow = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);
		pause = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
		life = BitmapFactory.decodeResource(getResources(), R.drawable.life);
		directionArrowLeft = BitmapFactory.decodeResource(getResources(),
				R.drawable.arrow_left);
		directionArrowRight = BitmapFactory.decodeResource(getResources(),
				R.drawable.arrow_right);
		sourceRect = new Rect(0, 0, man_right.getWidth()/4, man.getHeight());
		framePeriod = 150;
		frameTicker = 0l;
	}

    @Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		if (oldw == 0)
			initializeGame();
	}
    
 	@SuppressLint("ClickableViewAccessibility") @Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
 		touchX[0] = (int) event.getX(0);
		touchY[0] = (int) event.getY(0);

		int action = event.getAction() & MotionEvent.ACTION_MASK;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			shoot = true;
			manMove = true;
			touchX[1] = (int) event.getX(0);
			touchY[1] = (int) event.getY(0);
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			otherShoot = true;
			break;
		case MotionEvent.ACTION_UP:
			manMove = false;
			break;

		}
	}
	

    public void initializeGame(){
    	initializeNumberOfBalls(currentLevel);
    	initializeGamePanelArena(currentLevel);
    	initializeTime(currentLevel);
    }
    
		void initializeNumberOfBalls(int level) {
			
		manX = 11 * gameAreaWidth / 100;
		int initialBallX = (int) (11 * gameAreaWidth / 100);
		int initialBallY = (int) (50 * gameAreaHeight / 100);
		
		switch (level) {
		case 1:
			Ball b1 = new Ball(initialBallX + 100,
					(int) (initialBallY + gameAreaHeight / 10), risingFactor);
			balls.add(b1);
			radius.add(2 * BASE_RADIUS);
			break;

		case 2:
			Ball b2 = new Ball(initialBallX + 100,
					(int) (initialBallY + gameAreaHeight / 10), risingFactor);
			Ball b3 = new Ball(initialBallX + 400,
					(int) (initialBallY + gameAreaHeight / 10), risingFactor);
			balls.add(b2);
			balls.add(b3);
			radius.add(4 * BASE_RADIUS);
			radius.add(2 * BASE_RADIUS);
			break;

		case 3:
			Ball b4 = new Ball(initialBallX + 100, initialBallY, risingFactor);
			balls.add(b4);
			radius.add(8 * BASE_RADIUS);
			break;
		case 4:
			Ball b5 = new Ball(initialBallX + 100,
					(int) (initialBallY + gameAreaHeight / 10), risingFactor);
			Ball b6 = new Ball(initialBallX + 400,
					(int) (initialBallY + gameAreaHeight / 10), risingFactor);
			Ball b7 = new Ball(initialBallX + 700, initialBallY, risingFactor);
			balls.add(b5);
			balls.add(b6);
			balls.add(b7);
			radius.add(2 * BASE_RADIUS);
			radius.add(4 * BASE_RADIUS);
			radius.add(8 * BASE_RADIUS);
			break;
		}
		
		
	}
        void initializeGamePanelArena(int level){
        	
        	gameAreaHeight =855*getHeight()/1000;
        	gameAreaWidth = 90*getWidth()/100; 
        	BASE_RADIUS = (int) (gameAreaHeight / 48);
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
		spriteWidth = man_right.getWidth() / 4;
		sourceRect.left = currentFrame * spriteWidth;
		sourceRect.right = sourceRect.left + spriteWidth;

		manY = (int) (9 * gameAreaHeight / 10);
		//Accelerometer method
		if (MainActivity.selectMethod == 1) {
			accelerometerSensorX = AccelerometerData.sensorX;
			manVelocity = 2 * accelerometerSensorX;

			if (Math.abs(manVelocity) < 1) {
				manVelocity = 0;
			} else {
				manVelocity = (manVelocity / Math.abs(manVelocity)) * (35 / 10);
			}
		} else if (MainActivity.selectMethod == 2) { //Manual Method

			Rect destArrowLeft = new Rect(5 * getWidth() / 100,
					88 * getHeight() / 100, 13 * getWidth() / 100,
					97 * getHeight() / 100);
			Rect destArrowRight = new Rect(15 * getWidth() / 100,
					88 * getHeight() / 100, 23 * getWidth() / 100,
					97 * getHeight() / 100);
			if (manMove == true) {
				if (destArrowLeft.contains(touchX[0], touchY[0])) {
					manVelocity = (float) -3.5;
					shoot = false;
				} else if (destArrowRight.contains(touchX[0], touchY[0])) {
					manVelocity = (float) 3.5;
					shoot = false;
				}
			} else
				manVelocity = 0;
		}
		//updating manVelocity
		manX = manX + manVelocity;
		if (manX < 11 * gameAreaWidth / 100) {
			manX = 11 * gameAreaWidth / 100;
		}
		if (manX > gameAreaWidth - gameAreaWidth / 20) {
			manX = gameAreaWidth - gameAreaWidth / 20;
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
		displayingLivesAndScores(c);//writing no. of lives and scores
		renderingNavigationArrows(c);
	}
	void renderingTimer(Canvas c){
    	if (time < 0) {
			message = "Time Up";
			settingDelay();
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
	
	void displayingLivesAndScores(Canvas c){
    	//displaying levelNumber
    	Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/chewy.ttf");
		Paint p = new Paint();
		p.setTypeface(tf);
		p.setTextSize(5 * gameAreaHeight / 56);
		c.drawText("Level # " + currentLevel, 48 * gameAreaWidth / 100,
				95 * getHeight() / 100, p);
		//displaying no. of Lives
    	Rect y = new Rect((int) (45 * gameAreaWidth / 100),
				(int) (12 * gameAreaHeight / 100),
				(int) (45 * gameAreaWidth / 100 + gameAreaHeight / 10),
				(int) (10 * gameAreaHeight / 100 + gameAreaHeight / 9));
		c.drawBitmap(life, null, y, null);
		Paint text = new Paint();
		text.setColor(Color.BLACK);
		text.setTextSize((float) (1.1 * gameAreaHeight / 9));
		text.setTypeface(tf);
		c.drawText("" + numberOfLife, 53 * gameAreaWidth / 100,
				21 * gameAreaHeight / 100, text);

		// ------------------------------------------------------------------------------
		// display scores
		c.drawText("" + score, 6 * gameAreaWidth / 100,
				21 * gameAreaHeight / 100, text);
    }
	 void renderingNavigationArrows(Canvas c){
	    	if (MainActivity.selectMethod == 2) { // whem Manual method is selected
				Rect destArrowLeft = new Rect(5 * getWidth() / 100,
						88 * getHeight() / 100, 13 * getWidth() / 100,
						97 * getHeight() / 100);
				c.drawBitmap(directionArrowLeft, null, destArrowLeft, null);
				Rect destArrowRight = new Rect(15 * getWidth() / 100,
						88 * getHeight() / 100, 23 * getWidth() / 100,
						97 * getHeight() / 100);
				c.drawBitmap(directionArrowRight, null, destArrowRight, null);
			}
	    }
	
	public void detectCollison(){
		collisonBallArrow();
		collisonBallMan();
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
	void collisonBallMan() {
		for (int i = 0; i < balls.size(); i++) {
			if (balls.get(i).manballCollison) {
				// life will be lost
				message = "You Got Hit";
				settingDelay();
			}

		}
	}
	
	private void settingDelay() {
		// TODO Auto-generated method stub
		counter++;
		if (counter < 60) {
			pauseGame = true;
		} else {
			pauseGame = false;
			lifeLost();
			counter = 0;
		}
	}

	public void lifeLost() {
		numberOfLife--;
		balls.clear();
		radius.clear();
		gifts.clear();
		isShooting = 0;
		if (numberOfLife > 0) {
			initializeNumberOfBalls(currentLevel);
			initializeTime(currentLevel);
		} else {
			//gameOver Dialog will be displayed
			gameOver();
		}
	}
	
	public void gameOver() {
		pauseGame = true;
		final Dialog popUp = new Dialog(act);
		popUp.setTitle("PLAY AGAIN");
		popUp.setContentView(R.layout.game_over);
		Button yes = (Button) popUp.findViewById(R.id.button1);
		Button no = (Button) popUp.findViewById(R.id.button2);
		TextView dis = (TextView) popUp.findViewById(R.id.textView2);
		dis.setText("" + score);
		yes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getContext(), MainActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TOP);
				getContext().startActivity(i);
				popUp.dismiss();
			}
		});
		no.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		popUp.show();

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
				
				if (numberOfLife > 0 && currentLevel < 5) {
					if (balls.isEmpty()) {
						currentLevel++;
						initializeNumberOfBalls(currentLevel);
						initializeTime(currentLevel);
					}
				}
				if (counter > 1 && counter < 60) {
					Paint text = new Paint();
					text.setColor(Color.BLACK);
					text.setTextSize((float) (1.1 * gameAreaHeight / 9));
					Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
							"fonts/chewy.ttf");
					text.setTypeface(tf);
					c.drawText(message, 40 * gameAreaWidth / 100,
							60 * gameAreaHeight / 100, text);
				}
			
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
