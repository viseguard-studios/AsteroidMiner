package com.viseguardstudios.asteroid_miner.map_loader.entities.vessels;

import com.viseguardstudios.asteroid_miner.map_loader.entities.VesselCreator;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;

public class SpaceShipCreator extends VesselCreator {

    @Deprecated
    @Override
    public void create() {
        scene.AddEntity(new SpaceShip(new Asteroid())); //creating SS from thin air
    }

    @Deprecated
    public SpaceShipCreator(Scene scene) {
        super(scene);
    }

    @Deprecated
    public void create(Asteroid a)
    {
        scene.AddEntity(new SpaceShip(a));
    }

    public static SpaceShip createSpaceShip(){}
}
