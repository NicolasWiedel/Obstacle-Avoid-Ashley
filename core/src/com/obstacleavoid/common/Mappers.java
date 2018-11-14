package com.obstacleavoid.common;

import com.badlogic.ashley.core.ComponentMapper;
import com.obstacleavoid.component.BoundsComponent;
import com.obstacleavoid.component.MovementComponent;
import com.obstacleavoid.component.ObstacleComponent;
import com.obstacleavoid.component.PositionComponent;

public class Mappers {

    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT =
            ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<ObstacleComponent> OBSTACLE =
            ComponentMapper.getFor(ObstacleComponent.class);

    private Mappers(){

    }
}
