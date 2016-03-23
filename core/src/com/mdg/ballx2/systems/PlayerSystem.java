package com.mdg.ballx2.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mdg.ballx2.component.PlayerComponent;
import com.mdg.ballx2.component.TransformComponent;
import com.mdg.ballx2.component.VelocityComponent;

/**
 * @author DEEPANKAR
 * @since 23-03-2016.
 */
public class PlayerSystem extends IteratingSystem {

    private static final Family mFamily = Family.all(
            PlayerComponent.class,
            TransformComponent.class,
            VelocityComponent.class).get();

    public PlayerSystem() {
        super(mFamily);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
