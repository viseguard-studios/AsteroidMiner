package com.viseguardstudios.asteroid_miner.skeleton.tests;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.STest;
import com.viseguardstudios.asteroid_miner.skeleton.Test;
import java.util.Scanner;

@STest
public class SSDrillTest extends Test {

    private Asteroid a;
    private SpaceShip ss;

    @Override
    public void Setup(Scanner sc) {
        a = new Asteroid();
        ss = new SpaceShip(a);

        System.out.println("Enter a asteroid crust size(always enter a integer and bigger than 0!): ");
        var answer = sc.nextLine();
        a.setCrustSize((Integer.parseInt(answer)));

        System.out.println("Would you like the asteroid exploded? [Y/N]");
        answer = sc.nextLine();
        a.setExploded(answer.equals("Y"));

    }

    @Override
    public void Run() {
        Logger.functionCalled("ss.Drill()");
        ss.Drill();
        Logger.returned();
    }
}
