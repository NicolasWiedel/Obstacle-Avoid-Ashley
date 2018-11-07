package com.obstacleavoid.screen.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.obstacleavoid.ObstacleAvoidGame;
import com.obstacleavoid.screen.menu.MenuScreen;

public class GameScreen implements Screen {

    private final ObstacleAvoidGame game;
    private AssetManager assetManager;

    public GameScreen(ObstacleAvoidGame game){
        this.game = game;
        assetManager = game.getAssetManager();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {

    }
}
