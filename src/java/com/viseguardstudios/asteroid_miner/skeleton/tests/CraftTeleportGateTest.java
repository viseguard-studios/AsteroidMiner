package com.viseguardstudios.asteroid_miner.skeleton.tests;


import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.item.resource.Coal;
import com.viseguardstudios.asteroid_miner.model.item.resource.Ice;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.recipe.TeleportGateRecipe;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.STest;
import com.viseguardstudios.asteroid_miner.skeleton.Test;

import java.util.Scanner;

@STest
public class CraftTeleportGateTest extends Test {

    Asteroid a;
    SpaceShip ss;
    TeleportGateRecipe recipe;


    @Override
    public void Setup(Scanner sc) {

        /***
         * inicializálás
         */
        a = new Asteroid();
        //a.getInventory().setSize(10);
        ss = new SpaceShip(a);
        //ss.getInventory().setSize(10);
        recipe = new TeleportGateRecipe();


        System.out.println("Does your SpaceShip have enough resources to build the teleport gate pair? (Y/N)");
        String answer = sc.nextLine();
        /**
         * ha a SpaceShip-ben van elég alapanyag, akkor pl. inicializálhatjuk a raktárát a recept hozzávalóival
         */
        if(answer.equals("Y")) {
            for(Item i: recipe.getInput())
            ss.getInventory().insertItem(i);
        }
        /**
         * ha a SpaceShip-ben nem, de az Aszteroidában és a SpaceShip-ben együtt van elég anyag, akkor az Aszteroida
         * raktárát inicializálhatjuk például a recept hozzávalóival. Az esetek elkülönítése azért kell, mert a
         * készítés során több loop fut le, ha az aszteroida készletét is meg kell vizsgálnunk.
         */
        else {
            System.out.println("Does the SpaceShip + Asteroid have enough resources to build the teleport gate pair? (Y/N)");
            answer = sc.nextLine();
            if(answer.equals("Y")) {
                for (Item i : recipe.getInput())
                    a.getInventory().insertItem(i);
            }
            /**
             * Ha a kettőben együtt nincs elég item a hozzávalókhoz, 1-1 item-et adunk csak hozzá a raktárakhoz (így a
             * loop-ok lefutnak, de sikertelen lesz a craftolás).
             */
            else {
                System.out.println("Does the SpaceShip have enough space to store the teleport gate pair? (Y/N)");
                answer = sc.nextLine();
                if(answer.equals("N")) {
                    //ss.getInventory().setSize(1);
                }
                a.getInventory().insertItem(new Coal());
                ss.getInventory().insertItem(new Ice());
            }
        }

    }

    @Override
    public void Run() {
        Logger.functionCalled("ss.Craft(recipe)");
        ss.Craft(recipe);
        Logger.returned();
    }
}
