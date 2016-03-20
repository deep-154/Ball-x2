package com.mdg.ballx2.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * @author DEEPANKAR
 * @since 17-03-2016.
 */

public class TransformComponent implements Component {

    public Vector3 position = new Vector3();
    public float   rotation = 0f;
    public Vector2 scale    = new Vector2(1.0f,1.0f);
}
