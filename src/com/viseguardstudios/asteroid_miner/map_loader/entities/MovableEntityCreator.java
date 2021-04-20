package com.viseguardstudios.asteroid_miner.map_loader.entities;

import com.viseguardstudios.asteroid_miner.map_loader.EntityCreator;
import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.entities.buildings.StationCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.buildings.TeleGateCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.vessels.RobotCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.vessels.SpaceShipCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.vessels.UfoCreator;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.building.TeleportGate;

import java.util.ArrayList;

public abstract class MovableEntityCreator extends EntityCreator{
    @Deprecated
    public MovableEntityCreator(Scene scene) {
        super(scene);
    }


}
