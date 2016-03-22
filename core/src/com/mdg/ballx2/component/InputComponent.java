package com.mdg.ballx2.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * @author DEEPANKAR
 * @since 22-03-2016.
 */
public class InputComponent implements Component{

    public float accelX;
    public Vector2 touchRawCoord;
    public Vector2 touchWorldCoord;
}
