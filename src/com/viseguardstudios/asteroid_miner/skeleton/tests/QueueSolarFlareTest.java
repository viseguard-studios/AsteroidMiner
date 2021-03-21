package com.viseguardstudios.asteroid_miner.skeleton.tests;

import com.viseguardstudios.asteroid_miner.model.*;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.Test;


import java.util.Scanner;

public class QueueSolarFlareTest extends Test {

    private GameManager gm;
    private Scene scene;



    @Override
    public void Setup(Scanner sc) {

        scene = new Scene();
        gm = new GameManager();

        scene.setManager(gm);
        gm.setManagedScene(scene);

        Logger.log("gm.QueueSolarStorm()");
        gm.QueueSolarStorm();
        Logger.returned();

    }

    @Override
    public void Run() {

        Logger.log("gm.TakeTurn()");
        gm.TakeTurn();
        Logger.returned();

    }
}
