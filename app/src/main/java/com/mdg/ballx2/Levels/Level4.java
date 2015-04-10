package com.mdg.ballx2.Levels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import com.mdg.ballx2.Ball;
import com.mdg.ballx2.MainActivity;
import com.mdg.ballx2.R;
import com.mdg.ballx2.SoundPoolManager;


public class Level4 extends BaseLevel {

    Bitmap wall;

    public Level4(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
    }

    @Override
    public void initializeGame() {
        super.initializeGame();
        int initialBallX = (int) (11 * gameAreaWidth / 100);
        int initialBallY = (int) (50 * gameAreaHeight / 100);
        int displacement = (int) (gameAreaWidth / 10);
        float velocityX = (float) (0.002*gameAreaWidth);

        Ball b = new Ball(initialBallX + displacement,
                (int) (initialBallY + gameAreaHeight / 10), risingFactor,velocityX);
        Ball b2 = new Ball(initialBallX + 7 * displacement,
                (int) (initialBallY + gameAreaHeight / 10), risingFactor,velocityX);
        balls.add(b);
        balls.add(b2);

        radius.add(4 * BASE_RADIUS);
        radius.add(6 * BASE_RADIUS);

        manX = 30*gameAreaWidth / 100;

    }

    @Override
    void updateManState() {
        super.updateManState();
        if(balls.size()>1&&(radius.get(0)==6*BASE_RADIUS||radius.get(1)==6*BASE_RADIUS)){
            if(manX>47*gameAreaWidth/100- gameAreaWidth / 20)manX=47*gameAreaWidth/100- gameAreaWidth / 20;
        }
    }

    @Override
    void renderOtherObjects(Canvas c) {
        super.renderOtherObjects(c);
        mainRect.set((int) (47 * gameAreaWidth / 100),
                (int) (27 * gameAreaHeight / 100),
                (int) (53 * gameAreaWidth / 100), (int) (90*gameAreaHeight/100));
        c.drawBitmap(wall, null, mainRect, null);
        if(balls.size()>1&&(radius.get(0)==6*BASE_RADIUS||radius.get(1)==6*BASE_RADIUS)){
            mainRect.set((int) (47 * gameAreaWidth / 100),
                    (int) (90 * gameAreaHeight / 100),
                    (int) (53 * gameAreaWidth / 100), (int) (gameAreaHeight));
            paint.reset();
            paint.setColor(Color.argb(200, 68, 68, 68));
            c.drawRect(mainRect, paint);
        }
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        // condition to pause the game is applied here

        if (!pauseGame&&!isHitTimerActive) {
            updateGame();
            detectCollison();
        }
        renderGame(c);

        if (numberOfLife > 0) {
            if (balls.isEmpty()) {
                gifts.clear();
                MainActivity.currentLevel++;
                mlistener.onStageChanged(5,numberOfLife);
                initializeGame();
                updatePowerUps(8);

            }
        }

        if (isHitTimerActive) {

            if(isHit){
                Timer = System.currentTimeMillis();
                isHit = false;
                if(SoundPoolManager.getInstance()!=null)
                    SoundPoolManager.getInstance().playSound(R.raw.death);
            }

            timePassed = timePassed + System.currentTimeMillis() - Timer;
            Timer = System.currentTimeMillis();
            if (timePassed > 1500) {
                isHitTimerActive = false;
                timePassed = 0;
                lifeLost();
            }

        }

    }

}
