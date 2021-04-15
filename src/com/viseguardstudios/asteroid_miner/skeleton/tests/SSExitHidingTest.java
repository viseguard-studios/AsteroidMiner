package com.viseguardstudios.asteroid_miner.skeleton.tests;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.STest;
import com.viseguardstudios.asteroid_miner.skeleton.Test;

import java.util.Scanner;

@STest
public class SSExitHidingTest extends Test {
    private Asteroid a;
    private SpaceShip ss;

    @Override
    public void Setup(Scanner sc) {
        a = new Asteroid();
        ss = new SpaceShip(a);

        System.out.println("Would you like this spaceship hiding right now? [Y/N]");
        var answer = sc.nextLine();
        ss.setHidden(answer.equals("Y"));
    }

    @Override
    public void Run() {
        Logger.functionCalled("ss.ExitHiding()");
        ss.ExitHiding();
        Logger.returned();
    }
}
