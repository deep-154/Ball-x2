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

    private Family mFamily;

    public PlayerSystem() {
        super(Family.all(PlayerComponent.class, TransformComponent.class,
                VelocityComponent.class).get());
        mFamily = getFamily();
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}