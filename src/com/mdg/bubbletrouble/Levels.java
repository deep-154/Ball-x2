package com.mdg.bubbletrouble;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Levels extends Fragment {

	float finalX, bottomX = -1;
	static float arrowY, arrowX, currentX;
	boolean shoot = false;
	int a = 0;
	Bitmap []background = new Bitmap[3] ;
	Bitmap man, man_left, man_right,arrow;

	private ShapeDrawable ballDrawable;

	private Rect sourceRect; // the rectangle to be drawn from the animation
								// bitmap
	private int frameNr = 4; // number of frames in animation
	private int currentFrame; // the current frame
	private long frameTicker; // the time of the last frame update
	private int framePeriod; // milliseconds between each frame (1000/fps)
	private int spriteWidth; // the width of the sprite to calculate the cut out
								// rectangle
	private int spriteHeight;
	static float H, W;
	private stageOne s;
	ArrayList<Ball> balls = new ArrayList<Ball>();
	final int BASE_RADIUS = 15;
	int NumberOfBalls;
	ArrayList<Integer> radius = new ArrayList<Integer>();
	int ballX = 100;
	int ballY = 150;
	float ballVelocityX = (float) 2.2, ballVelocityY;
	double risingFactor = 0.1;
    int currentLevel = 1;
    
    
    
	void initializingNumberOfBalls(int level) {

		switch (level) {
		case 1:
			NumberOfBalls = 1;
			Ball b1 = new Ball(100,150,risingFactor);
			balls.add(b1);
			radius.add(2 * BASE_RADIUS);
			// radius.add(4*BASE_RADIUS);

			break;
		case 2:
			NumberOfBalls = 1;
			Ball b2 = new Ball(100,150,risingFactor);
			Ball b3 = new Ball(600,150,risingFactor);
			balls.add(b2);
			balls.add(b3);
			radius.add(8 * BASE_RADIUS);
			radius.add(4 * BASE_RADIUS);
			break;
		case 3:
			break;
		}
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return new myView(getActivity());

	}

	public void getCoordinate(float position, boolean checkShoot) {
		// TODO Auto-generated method stub
		bottomX = position;
		shoot = checkShoot;
	}

	public interface stageOne {
		public void OnStageOneChanged(int changeStage, int restartStage);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			s = (stageOne) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnTouch");
		}
	}

	class myView extends View {

		int Y;
		int delay = 0;

		public myView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			background[0] = BitmapFactory.decodeResource(getResources(),
					R.drawable.level_one);
			background[1] = BitmapFactory.decodeResource(getResources(),
					R.drawable.level_two);
			background[2] = BitmapFactory.decodeResource(getResources(),
					R.drawable.level_three);
			man = BitmapFactory.decodeResource(getResources(), R.drawable.man);
			man_left = BitmapFactory.decodeResource(getResources(),
					R.drawable.man_left);
			man_right = BitmapFactory.decodeResource(getResources(),
					R.drawable.man_right);
			arrow = BitmapFactory.decodeResource(getResources(),
					R.drawable.arrow);
			currentFrame = 0;
			sourceRect = new Rect(0, 0, man_right.getWidth() / 4,
					man_right.getHeight());
			framePeriod = 150;
			frameTicker = 0l;

			initializingNumberOfBalls(currentLevel);
		}

		void walking() {
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
		}

		@SuppressLint("DrawAllocation")
		@Override
		protected void onDraw(Canvas c) {
			// TODO Auto-generated method stub
			super.onDraw(c);

			Rect dest = new Rect(0, 0, getWidth(), getHeight());
			Paint paint = new Paint();
			paint.setFilterBitmap(true);
			c.drawBitmap(background[currentLevel-1], null, dest, paint);
			// ---------------------------------------------

			H = getHeight();
			W = getWidth();
			if (delay > 100) {

				// shooting the ball

				if (a == 0) {
					arrowY = c.getHeight();
					arrowX = currentX + man.getWidth() / 2 - arrow.getWidth()
							/ 2;
				}
				if (shoot == true) {
					a = 1;
				}
				if (a == 1) {
					arrowY = arrowY - 8;

					Rect Rec2 = new Rect((int) arrowX, (int) arrowY,
							(int) (arrowX + arrow.getWidth()), c.getHeight());
					c.drawBitmap(arrow, null, Rec2, null);
					if (arrowY < 0) {
						arrowY = H;
						a = 0;
					}
				}
				// --------------------------------------------
				// walking the man with help of slider
				walking();

				spriteHeight = man.getHeight();
				spriteWidth = man_right.getWidth() / 4;
				Y = c.getHeight() - man.getHeight();
				int mid = c.getWidth() / 2 - man.getWidth() / 2;
				finalX = (float) ((bottomX) * (2.229883));

				Rect destRect = new Rect((int) currentX, Y,
						(int) (currentX + spriteWidth), Y + spriteHeight);

				if (finalX < 0) {

					currentX = mid;
					c.drawBitmap(man, null, destRect, null);
				}
				if (finalX > currentX) {

					c.drawBitmap(man_right, sourceRect, destRect, null);
					currentX = currentX + 3;
					if (currentX == finalX || currentX > finalX) {
						currentX = finalX;
					}
				} else if (finalX < currentX && finalX > 0) {

					c.drawBitmap(man_left, sourceRect, destRect, null);
					currentX = currentX - 3;
					if (currentX == finalX || currentX < finalX) {
						currentX = finalX;
					}
				} else if (finalX == currentX) {
					c.drawBitmap(man, null, destRect, null);
				}
				// ----------------------------------------------------------------------------------
				// Drawing balls

				ballDrawable = new ShapeDrawable(new OvalShape());
				ballDrawable.getPaint().setColor(Color.GREEN);

				for (int i = 0; i < balls.size(); i++) {
					if (radius.get(i) > 4 * BASE_RADIUS) {
						ballVelocityY = (float) (6 + (radius.get(i) / (1.5 * BASE_RADIUS)));
					} else {
						ballVelocityY = 6 + (radius.get(i) / BASE_RADIUS);
					}

					balls.get(i).movingBall(radius.get(i), ballVelocityY, risingFactor);
					ballDrawable.setBounds((int) balls.get(i).ballX,
							(int) balls.get(i).ballY, (int) balls.get(i).ballX
									+ radius.get(i), (int) balls.get(i).ballY
									+ radius.get(i));
					ballDrawable.draw(c);

					if (balls.get(i).ballHit == 1) {
						radius.set(i, radius.get(i) / 2);
						arrowY = H;
						a = 0;

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
							
							// Log.d("rFGame",""+risingFactor);

						}
					}

					if(balls.isEmpty()){
						currentLevel++;
						initializingNumberOfBalls(currentLevel);
					}
				}

				// c.drawBitmap(bcheck, 100, 100, null);

			}

			if (delay < 102) {
				Paint write = new Paint();
				write.setColor(Color.rgb(253, 238, 0));
				write.setTextSize(50);
				write.setStrokeWidth(30);
				c.drawText("Get Ready", 4 * W / 10, 6 * H / 10, write);
				delay++;
			}
			invalidate();
		}
	}

}
