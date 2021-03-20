package com.viseguardstudios.asteroid_miner.skeleton;

import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.building.TeleportGate;
import com.viseguardstudios.asteroid_miner.skeleton.tests.SSDrillTest;
import com.viseguardstudios.asteroid_miner.skeleton.tests.SSMoveTest;

import java.util.Scanner;

public class SkeletonEntry {

    static Registry<Test> testRegistry = new Registry<>("");


    public static void main(String[] args) {

        RegisterTests();

        while (true){

            Scanner sc= new Scanner(System.in);
            var line = sc.nextLine();

            var tokens = line.split(" ");

            var test = testRegistry.Get(tokens[0]);

            if(test == null)
                continue;

            Logger.setEnabled(false);
            test.Setup(sc);

            Logger.setEnabled(true);
            test.Run();

        }

    }

    public static void RegisterTests(){
        testRegistry.Register("SSMove", new SSMoveTest());
        testRegistry.Register("SSDrill", new SSDrillTest());
    }


}
