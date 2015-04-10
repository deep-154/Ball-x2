package com.mdg.ballx2.Levels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import com.mdg.ballx2.Ball;
import com.mdg.ballx2.MainActivity;
import com.mdg.ballx2.R;
import com.mdg.ballx2.SoundPoolManager;


public class Level1 extends BaseLevel {

    public Level1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void initializeGame() {
        super.initializeGame();
        int initialBallX = (int) (11 * gameAreaWidth / 100);
        int initialBallY = (int) (50 * gameAreaHeight / 100);
        int displacement = (int) (gameAreaWidth / 10);
        float velocityX = (float) (0.002*gameAreaWidth);
        Ball b1 = new Ball(initialBallX + displacement,
                (int) (initialBallY + gameAreaHeight / 10), risingFactor,velocityX);
        balls.add(b1);
        radius.add(2 * BASE_RADIUS);


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
                mlistener.onStageChanged(2,numberOfLife);
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
