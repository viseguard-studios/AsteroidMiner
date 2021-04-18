package com.viseguardstudios.asteroid_miner.map_loader.entities.buildings;

import com.viseguardstudios.asteroid_miner.map_loader.entities.BuildingCreator;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.building.TeleportGate;

public class TeleGateCreator extends BuildingCreator {


    public TeleGateCreator(Scene scene) {
        super(scene);
    }

    @Override
    public void create() {
        scene.AddEntity(new TeleportGate(1)); //TODO: NEW id
    }
}
