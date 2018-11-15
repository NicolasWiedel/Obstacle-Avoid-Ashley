package com.obstacleavoid.system.collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Logger;
import com.obstacleavoid.common.Mappers;
import com.obstacleavoid.component.BoundsComponent;
import com.obstacleavoid.component.ObstacleComponent;
import com.obstacleavoid.component.PlayerComponent;
import com.obstacleavoid.component.SpecialObstacleComponent;

public class CollisionSystem extends EntitySystem {

    private static final Logger log = new Logger(CollisionSystem.class.getName(), Logger.DEBUG);

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class,
            BoundsComponent.class

    ).get();

    private static final Family OBSTACLE_FAMILY = Family.all(
            ObstacleComponent.class,
            BoundsComponent.class
    ).get();

    private static final Family SPECIAL_OBSTACLE_FAMILY = Family.all(
            SpecialObstacleComponent.class,
            BoundsComponent.class
    ).get();

    private final CollisionListener listener;

    public CollisionSystem(CollisionListener listener){
        this.listener = listener;
    }

    @Override
    public void update(float deltaTime) {
        // size is 1, we only have one player
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYER_FAMILY);

        ImmutableArray<Entity> obstacles = getEngine().getEntitiesFor(OBSTACLE_FAMILY);

        ImmutableArray<Entity> specialObstacles = getEngine().getEntitiesFor(SPECIAL_OBSTACLE_FAMILY);

        for(Entity playerEntity : players){
            for(Entity obstacleEntity : obstacles){
                ObstacleComponent obstacle = Mappers.OBSTACLE.get(obstacleEntity);

                if (obstacle.hit){
                    continue;
                }

                if(checkCollision(playerEntity, obstacleEntity)){
                    obstacle.hit = true;
                    log.debug("collision with obstacle!!");
                    listener.hitObstacle();
                }
            }
            for(Entity specialObstacleEntity : specialObstacles) {
                SpecialObstacleComponent specialObstacle = Mappers.SPECIAL_OBSTACLE.get(specialObstacleEntity);
                if (specialObstacle.hit) {
                    continue;
                }
                if (checkCollision(playerEntity, specialObstacleEntity)) {
                    specialObstacle.hit = true;
                    listener.hitSpecialObstacle();
                }
            }
        }
    }

    private boolean checkCollision(Entity player, Entity obstacle){
        BoundsComponent playerBounds = Mappers.BOUNDS.get(player);
        BoundsComponent obstacleBounds = Mappers.BOUNDS.get(obstacle);

        return Intersector.overlaps(playerBounds.bounds, obstacleBounds.bounds);
    }
}
