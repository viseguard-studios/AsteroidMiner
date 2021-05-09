package com.viseguardstudios.asteroid_miner.map_loader.entities;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.entities.vessels.RobotCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.vessels.SpaceShipCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.vessels.UfoCreator;
import com.viseguardstudios.asteroid_miner.map_loader.item.*;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;
import com.viseguardstudios.asteroid_miner.model.item.Item;

import java.util.ArrayList;

public abstract class VesselCreator extends MovableEntityCreator{
    @Deprecated
    public VesselCreator(Scene scene) {
        super(scene);
    }

    /**
     * Compiles a list of vessels that need to be added. Note: empty lines are ignored. Note: vessels are automatically placed on the asteroid.
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
    public static void createVessels(ArrayList<String> inputLines, GameManager manager, Asteroid home) throws Exception {
        String rawLine = null;
        for (int i = 0; i<inputLines.size(); i++) {
            rawLine = inputLines.get(i);
            if(rawLine.isEmpty() || rawLine.isBlank())
                continue;

            String currentType = FileOpener.getObjType(rawLine);
            int[] ids = {-1, -1};
            ArrayList<String> rawDescriber= null;
            if (currentType != null) {
                switch (currentType) {
                    default:
                        break;
                    case "robot":
                        try {
                            RobotCreator.createRobot(rawLine,manager, home);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ufo":
                        ids = new int[]{-1, -1};
                        try {
                        ids = FileOpener.getChildLoc(inputLines,"ufo",i);
                        } catch (Exception e) {
                        e.printStackTrace();
                         }
                        if(ids[0]==-1||ids[1]==-1) throw new Exception("End of UFO describer not found.");
                        rawDescriber= new ArrayList<String>(inputLines.subList(ids[0],ids[1]+1));
                        UfoCreator.createUFO(rawDescriber,manager, home);
                        break;
                    case "spaceship":
                        ids = new int[]{-1, -1};
                        try {
                            ids = FileOpener.getChildLoc(inputLines,"spaceship",i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(ids[0]==-1||ids[1]==-1) throw new Exception("End of SpaceShip describer not found.");
                        rawDescriber = new ArrayList<String>(inputLines.subList(ids[0],ids[1]+1));
                        SpaceShipCreator.createSpaceShip(rawDescriber,manager,home);
                        break;
                }
            }
        }
        return;
    }

}
