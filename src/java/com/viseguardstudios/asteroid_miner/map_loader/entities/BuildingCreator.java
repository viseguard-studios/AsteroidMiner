package com.viseguardstudios.asteroid_miner.map_loader.entities;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.entities.buildings.StationCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.buildings.TeleGateCreator;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;

import java.util.ArrayList;

public abstract class BuildingCreator extends MovableEntityCreator{
    public BuildingCreator(Scene scene) {
        super(scene);
    }

    /**
     * Compiles a list of movables that need to be added. Note: empty lines are ignored. Note: movables are automatically placed on the asteroid.
     *
     * @param inputLines Format:
     * ****************
     *  "{...Inventory\resorces}",
     *  "   {res1}",
     *  "",
     *  "   {res2}",
     *  "{/...Inventory\resorces}"
     * ****************
     * @return
     */
    public static void createBuildings(ArrayList<String> inputLines, Asteroid home) throws Exception {
        String rawLine = null;
        for (int i = 0; i<inputLines.size(); i++) {
            rawLine = inputLines.get(i);
            if(rawLine.isEmpty() || rawLine.isBlank())
                continue;

            String currentType = FileOpener.getObjType(rawLine);
            ArrayList<String> rawDescriber= null;
            if (currentType != null) {
                switch (currentType) {
                    default:
                        break;
                    case "telegate":
                        try {
                            TeleGateCreator.createTeleportGate(rawLine, home);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "spacestation":
                        try {
                            StationCreator.createSpaceStation(home);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        }
        return;
    }
}
