package com.mdg.ballx2;

/**
 * @author DEEPANKAR
 * @since 20-03-2016.
 */

import com.badlogic.ashley.core.PooledEngine;

/**
 * This class deals with:-
 * 1. Managing gameEngine (create and provide to needed classes)
 * 2. Managing the levels status and respective highScores along with settings
 * 3. Managing game screens if needed
 */
public class GameManager {

    private static GameManager mGameManager;
    private PooledEngine mGameEngine;

    public boolean isGameRunning;
    public int currentLevel;

    public static GameManager getGameManager(){
        if(mGameManager== null) mGameManager = new GameManager();
        return mGameManager;
    }

    private GameManager(){
        mGameEngine = new PooledEngine(25,80,100,250);
    }

    public PooledEngine getGameEngine(){
        return mGameEngine;
    }


}
