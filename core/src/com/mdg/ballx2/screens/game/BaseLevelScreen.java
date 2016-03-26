package com.mdg.ballx2.screens.game;

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

    float accelX = 0.0f;

    public BaseLevelScreen(Ballx2 game) {
        super(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

//        Application.ApplicationType appType = Gdx.app.getType();
//
//        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
//            accelX = Gdx.input.getAccelerometerX();
//        } else {
//            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) accelX = 5f;
//            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) accelX = -5f;
//        }

        //engine.getSystem(BobSystem.class).setAccelX(accelX);

    }


}
