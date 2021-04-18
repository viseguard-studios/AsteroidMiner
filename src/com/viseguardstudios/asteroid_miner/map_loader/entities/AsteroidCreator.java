package com.viseguardstudios.asteroid_miner.map_loader.entities;

import com.viseguardstudios.asteroid_miner.map_loader.EntityCreator;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;

public class AsteroidCreator extends EntityCreator {

    public AsteroidCreator(Scene scene) {
        super(scene);
    }
    @Override
    public void create() {
        scene.AddEntity(new Asteroid());
    }
}
