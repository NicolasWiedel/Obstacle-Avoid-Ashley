package com.obstacleavoid.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.obstacleavoid.component.BoundsComponent;
import com.obstacleavoid.component.CleanUpComponent;
import com.obstacleavoid.component.MovementComponent;
import com.obstacleavoid.component.ObstacleComponent;
import com.obstacleavoid.component.PlayerComponent;
import com.obstacleavoid.component.PositionComponent;
import com.obstacleavoid.component.WorldWrapComponent;
import com.obstacleavoid.config.GameConfig;
import com.obstacleavoid.system.WorldWrapSystem;

public class EntityFactory {

    private final PooledEngine engine;

    public EntityFactory(PooledEngine engine){
        this.engine = engine;
    }

    public void addPlayer(){
        float x = GameConfig.WORLD_WIDTH / 2;
        float y = GameConfig.PLAYER_SIZE;

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;

        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.bounds.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS);

        MovementComponent movement = engine.createComponent(MovementComponent.class);

        PlayerComponent player = engine.createComponent(PlayerComponent.class);

        WorldWrapComponent worldWrap = engine.createComponent(WorldWrapComponent.class);

        Entity entity = engine.createEntity();
        entity.add(bounds);
        entity.add(movement);
        entity.add(player);
        entity.add(position);
        entity.add(worldWrap);

        engine.addEntity(entity);
    }

    public void addObstacle(float x, float y){
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.bounds.set(x, y, GameConfig.OBSTACLE_BOUNDS_RADIUS);

        MovementComponent movement = engine.createComponent(MovementComponent.class);
        movement.ySpeed = -GameManager.INSTANCE.getDifficultyLevel().getObstacleSpeed();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;

        CleanUpComponent cleanUp = engine.createComponent(CleanUpComponent.class);

        ObstacleComponent obstacle = engine.createComponent(ObstacleComponent.class);

        Entity entity = engine.createEntity();

        entity.add(bounds);
        entity.add(movement);
        entity.add(position);
        entity.add(cleanUp);
        entity.add(obstacle);

        engine.addEntity(entity);
    }
}
