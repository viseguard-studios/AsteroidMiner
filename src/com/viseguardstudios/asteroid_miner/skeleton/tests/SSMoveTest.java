package com.viseguardstudios.asteroid_miner.skeleton.tests;

import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.building.TeleportGate;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.STest;
import com.viseguardstudios.asteroid_miner.skeleton.Test;

import java.util.Scanner;

@STest
public class SSMoveTest extends Test {


    private Asteroid a1;
    private Asteroid a2;

    private SpaceShip ss;

    @Override
    public void Setup(Scanner sc) {
        System.out.print("Use TeleportGate? (Y/N):");
        var answer = sc.nextLine();

        a1 = new Asteroid();
        a2 = new Asteroid();


        ss = new SpaceShip(a1);


        if(answer.equals("Y")){
            TeleportGate tg1 = new TeleportGate();
            a1.AddBuilding(tg1);

            TeleportGate tg2 = new TeleportGate();
            a2.AddBuilding(tg2);

            tg1.SetPair(tg2);
            tg2.SetPair(tg1);
        }
        else {
            a1.AddNeigbour(a2);
        }
    }

    @Override
    public void Run() {

        Logger.functionCalled("ss.Move(a2)");
        ss.Move(a2);
        Logger.returned();
    }
}