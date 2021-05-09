package com.viseguardstudios.asteroid_miner.map_loader.entities.buildings;

import com.viseguardstudios.asteroid_miner.map_loader.entities.BuildingCreator;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.building.SpaceStation;
import com.viseguardstudios.asteroid_miner.model.entities.building.TeleportGate;
import com.viseguardstudios.asteroid_miner.model.item.resource.Coal;

public class StationCreator extends BuildingCreator {
    @Deprecated
    public StationCreator(Scene scene) {
        super(scene);
    }
    @Deprecated
    @Override
    public void create() {
        scene.addEntity(new SpaceStation(null)); }

    public static SpaceStation createSpaceStation(Asteroid home){
                SpaceStation station = new SpaceStation(home);
                home.GetScene().addEntity(station);
                return station;
    }
}
