package com.viseguardstudios.asteroid_miner.skeleton;

import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.building.TeleportGate;

import java.util.Scanner;

public class SkeletonEntry {


    public static void main(String[] args) {



        while (true){

            Scanner sc= new Scanner(System.in);
            var line = sc.nextLine();

            var tokens = line.split(" ");

            switch (tokens[0]){
                case "move":
                    System.out.print("Use TeleportGate? (Y/N):");
                    var answer = sc.nextLine();
                    if(answer.equals("Y")){
                        Asteroid a1 = new Asteroid();
                        Asteroid a2 = new Asteroid();
                        TeleportGate tg1 = new TeleportGate();
                        TeleportGate tg2 = new TeleportGate();


                    }
                    break;
                case "drill":

                    break;
            }

        }

    }

}
