package com.mdg.ballx2.screens.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.mdg.ballx2.Ballx2;
import com.mdg.ballx2.screens.BaseScreen;

/**
 * @author DEEPANKAR
 * @since 20-03-2016.
 */

/**
 * It includes implementation of following :-
 *
 * 1. Spawning player
 * 2. Handling input, backKey pressing
 * 3. Rendering UI components (like dialogs, timer etc.)
 * 4. Saving settings on dispose(), handling pause/play of game
 */
public class BaseLevelScreen extends BaseScreen {

    private Application.ApplicationType appType;

    public float accelX = 0.0f;
    public Vector3 touch;

    public BaseLevelScreen(Ballx2 game) {
        super(game);
        appType = Gdx.app.getType();
        touch = new Vector3(0,0,0);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                mCamera.unproject(touch.set(x, y, 0));
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {

        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            accelX = Gdx.input.getAccelerometerY();
        }


    }


}
