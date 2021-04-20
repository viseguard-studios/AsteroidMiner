package com.viseguardstudios.asteroid_miner.map_loader.entities.vessels;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.GMCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.VesselCreator;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Robot;

public class RobotCreator extends VesselCreator {
    @Deprecated
    public RobotCreator(Scene scene) {
        super(scene);
    }

    @Deprecated
    @Override
    public void create() {
        scene.addEntity(new Robot(new Asteroid())); //creating Robot from thin air
    }

    @Deprecated
    public void create(Asteroid a) {
        scene.addEntity(new Robot(a));
    }

    /**
     * Létrehoz egy új robotot
     * @param rawLine
     * @return
     * @throws Exception
     */
    public static Robot createRobot(String rawLine, GameManager manager ,Asteroid home) throws Exception {
        String name = "default";
        String playerName = null;
        boolean isHidden = false; //alapértelmezetten nem bújtunk el
        String param = FileOpener.getPropValue(rawLine,"name");
        if (param!=null){
            name =  param;
        }
        param = FileOpener.getPropValue(rawLine,"ownerPlayer");
        if (param!=null){
            playerName =  param;
        }
        else {
            throw new Exception("Robot without assigned player found!");
        }
        param = FileOpener.getPropValue(rawLine,"ishidden");
        if (param!=null){
            isHidden = FileOpener.getBoolValue(param);
        }

        Player owner = manager.getPlayerByName(playerName);
        Robot robot = new Robot(owner,home,name);
        if(isHidden)
            robot.Hide();

        //register into Scene
        manager.getManagedScene().addEntity(robot);
        return robot;

    }
}
