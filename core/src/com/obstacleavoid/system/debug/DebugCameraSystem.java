package com.obstacleavoid.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.obstacleavoid.util.debug.DebugCameraController;

public class DebugCameraSystem extends EntitySystem {

    // == constants ==
    private final DebugCameraController DEBUG_CAMERA_CONTROLLER = new DebugCameraController();

    // == attributes ==
    private final OrthographicCamera camera;

    // == contructor ==
    public DebugCameraSystem(OrthographicCamera camera, float startX, float startY){
        this.camera = camera;
        DEBUG_CAMERA_CONTROLLER.setStartPosition(startX, startY);
    }

    // == update ==
    @Override
    public void update(float deltaTime) {
        DEBUG_CAMERA_CONTROLLER.handleDebugInput(deltaTime);
        DEBUG_CAMERA_CONTROLLER.applyTo(camera);

    }
}
