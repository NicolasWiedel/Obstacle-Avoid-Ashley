package com.obstacleavoid.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.common.Mappers;
import com.obstacleavoid.component.BoundsComponent;
import com.obstacleavoid.component.MovementComponent;
import com.obstacleavoid.component.PlayerComponent;
import com.obstacleavoid.config.GameConfig;

public class PlayerSystem extends IteratingSystem {

    private static final Logger log = new Logger(PlayerSystem.class.getName(), Logger.DEBUG);

    private static final Family FAMILY = Family.all(
            PlayerComponent.class,
            MovementComponent.class,
            BoundsComponent.class
    ).get();

    public PlayerSystem(){
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponent movement = Mappers.MOVEMENT.get(entity);

        movement.xSpeed = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            movement.xSpeed = GameConfig.MAX_PLAYER_X_SPEED;
        }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            movement.xSpeed = -GameConfig.MAX_PLAYER_X_SPEED;
        }

        BoundsComponent bounds = Mappers.BOUNDS.get(entity);

        log.debug("processEntity xSpeed = " + movement.xSpeed);
        log.debug("bounds = " + bounds.bounds);
    }
}
