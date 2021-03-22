package com.viseguardstudios.asteroid_miner.skeleton.tests;

import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.Test;

import java.util.Scanner;

public class MoveAsteroidField extends Test {

    private GameManager gm;
    int newDistance;

    @Override
    public void Setup(Scanner sc) {

        gm = new GameManager();

        Logger.functionCalled("gm.ChangeAFDistance");
        gm.ChangeAFDistance(0);
        Logger.returned();

        System.out.print("What should the new distance be? Please enter a new number.");
        newDistance = Integer.parseInt(sc.nextLine());

    }

    @Override
    public void Run() {

        Logger.functionCalled("gm.ChangeAFDistance");
        gm.ChangeAFDistance(newDistance);
        Logger.returned();

    }
}
