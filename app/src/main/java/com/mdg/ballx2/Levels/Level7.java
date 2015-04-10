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

public class Level7 extends BaseLevel {

    boolean gameEnd = false;

    public Level7(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void initializeGame() {
        super.initializeGame();
        int initialBallX = (int) (11 * gameAreaWidth / 100);
        int initialBallY = (int) (50 * gameAreaHeight / 100);
        int displacement = (int) (65*gameAreaWidth/1000);
        float velocityX = (float) (0.002*gameAreaWidth);

        for(int i =0;i<3;i++){
            Ball b = new Ball((int) (5*gameAreaWidth/200+ i*displacement),
                    (int) (initialBallY+gameAreaHeight / 12), risingFactor,0);
            balls.add(b);
            radius.add(4*BASE_RADIUS);
            Ball c = new Ball((int) (gameAreaWidth-4*BASE_RADIUS -5*gameAreaWidth/200- i*displacement),
                    (int) (initialBallY+gameAreaHeight / 12), risingFactor,0);
            balls.add(c);
            radius.add(4*BASE_RADIUS);
        }

        Ball b = new Ball((int) (initialBallX +22*gameAreaWidth/100),
                (int) (initialBallY-gameAreaHeight / 20), risingFactor,velocityX);
        balls.add(b);
        radius.add(2*BASE_RADIUS);
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

        if (numberOfLife > 0 &&gameEnd == false) {
            if (balls.isEmpty()) {
               gameEnd =true;
                gifts.clear();
                message = "YOU WON";
                isHit =true;
                isHitTimerActive = true;
            }
        }



        if (isHitTimerActive) {

            if(isHit){
                Timer = System.currentTimeMillis();
                isHit = false;
                if(!gameEnd) {
                    if (SoundPoolManager.getInstance() != null)
                        SoundPoolManager.getInstance().playSound(R.raw.death);
                }

            }

            timePassed = timePassed + System.currentTimeMillis() - Timer;
            Timer = System.currentTimeMillis();
            if (timePassed > 1500) {
                isHitTimerActive = false;
                timePassed = 0;
                if(!gameEnd){
                    lifeLost();
                }else{
                    gameOver();
                }

            }

        }
    }

}
