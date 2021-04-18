package com.viseguardstudios.asteroid_miner.map_loader.entities.vessels;

import com.viseguardstudios.asteroid_miner.map_loader.entities.VesselCreator;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Robot;

public class RobotCreator extends VesselCreator {
    public RobotCreator(Scene scene) {
        super(scene);
    }

    @Override
    public void create() {
        scene.AddEntity(new Robot(new Asteroid())); //creating Robot from thin air
    }

    public void create(Asteroid a) {
        scene.AddEntity(new Robot(a));
    }
}
