package com.mdg.ballx2.component;

import com.badlogic.ashley.core.Component;

/**
 * @author DEEPANKAR
 * @since 22-03-2016.
 */
public class GiftComponent implements Component {

    public float width = 0f;
    public float height = 0f;
    public int timeToDestroy; // in seconds

    public GiftComponent(float width, float height){
        this.width = width;
        this.height = height;
    }
}
