package com.viseguardstudios.asteroid_miner.skeleton.tests;

import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Robot;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.Test;

import java.util.Scanner;

public class SolarFlareHitsRobot extends Test {

    private Robot r;
    private Asteroid a;

    @Override
    public void Setup(Scanner sc) {

        a = new Asteroid();
        r = new Robot(a);

        System.out.print("Hide robot? (Y/N):");
        String answer = sc.nextLine().toLowerCase();

        if (answer == "y"){
            Logger.functionCalled("r.Hide();");
            r.Hide();
            Logger.returned();
        }

    }

    @Override
    public void Run() {

        Logger.functionCalled("r.SolarFlare()");
        r.SolarFlare();
        Logger.returned();

    }
}
