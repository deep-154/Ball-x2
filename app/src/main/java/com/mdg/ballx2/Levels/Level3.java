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

/**
 * Created by DEEPANKAR on 27-03-2015.
 */
public class Level3 extends BaseLevel {

    public Level3(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void initializeGame() {
        super.initializeGame();
        int initialBallX = (int) (11 * gameAreaWidth / 100);
        int initialBallY = (int) (50 * gameAreaHeight / 100);
        int displacement = (int) (gameAreaWidth / 10);
        float velocityX = (float) (0.002*gameAreaWidth);

        Ball b4 = new Ball(initialBallX + displacement, initialBallY,
                risingFactor,velocityX);
        balls.add(b4);
        radius.add(6 * BASE_RADIUS);


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
                mlistener.onStageChanged(4,numberOfLife);
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
