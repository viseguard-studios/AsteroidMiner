package com.viseguardstudios.asteroid_miner.map_loader;

import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Entity;
import com.viseguardstudios.asteroid_miner.util.Vector2;

import java.util.ArrayList;
import java.util.List;

public class SceneCreator {
    /** TODO This is incomplete!
     * Create a new Scene by passing the command lines
     * @return
     */
    public static Scene SceneFromCommandLine(ArrayList<String> inputLines){
        Scene scene = new Scene();
        //must create entities here

        return scene;
    }

    public static Scene createSceneFromFile(ArrayList<String> inputLines){
        Scene scene = new Scene();
        scene.setManager(GMCreator.createManager(inputLines,scene));

        return scene;
    }
}
