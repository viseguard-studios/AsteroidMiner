package com.viseguardstudios.asteroid_miner.map_loader.entities.vessels;

import com.viseguardstudios.asteroid_miner.map_loader.entities.VesselCreator;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;

public class UfoCreator  extends VesselCreator {
    @Override
    public void create() {
        scene.AddEntity(new UFO(new Asteroid())); //creating UFO from thin air
    }

    public UfoCreator(Scene scene) {
        super(scene);
    }

    public void create(Asteroid a)
    {
        scene.AddEntity(new UFO(a));
    }

}
