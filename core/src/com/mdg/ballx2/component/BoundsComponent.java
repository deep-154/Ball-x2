package com.mdg.ballx2.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author DEEPANKAR
 * @since 22-03-2016.
 */
public class BoundsComponent implements Component {

    public Rectangle bounds = new Rectangle();

    public BoundsComponent setBounds(Rectangle r){
        this.bounds = r;
        return this;
    }

    public BoundsComponent setBounds(float x, float y, float width, float height){
        this.bounds.set(x, y, width, height);
        return this;
    }

}
