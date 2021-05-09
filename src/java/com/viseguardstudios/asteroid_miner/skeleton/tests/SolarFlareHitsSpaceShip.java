package com.viseguardstudios.asteroid_miner.skeleton.tests;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.Test;

import java.util.Scanner;

public class SolarFlareHitsSpaceShip extends Test {

    private SpaceShip s;
    private Asteroid a;

    @Override
    public void Setup(Scanner sc) {

        a = new Asteroid();
        s = new SpaceShip(a);

        System.out.print("Hide SpaceShip? (Y/N):");
        String answer = sc.nextLine().toLowerCase();

        if (answer.equals("y")){
            Logger.functionCalled("r.Hide();");
            s.Hide();
            Logger.returned();
        }

    }

    @Override
    public void Run() {

        Logger.functionCalled("r.SolarFlare()");
        s.SolarFlare();
        Logger.returned();

    }
}
