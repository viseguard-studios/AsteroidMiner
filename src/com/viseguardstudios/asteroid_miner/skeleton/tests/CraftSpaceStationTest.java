package com.viseguardstudios.asteroid_miner.skeleton.tests;


import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.building.SpaceStation;
import com.viseguardstudios.asteroid_miner.model.item.Coal;
import com.viseguardstudios.asteroid_miner.model.item.Ice;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.recipe.RobotRecipe;
import com.viseguardstudios.asteroid_miner.model.recipe.SpaceStationRecipe;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.STest;
import com.viseguardstudios.asteroid_miner.skeleton.Test;

import java.util.Scanner;

@STest
public class CraftSpaceStationTest extends Test {

    Asteroid a;
    SpaceShip ss;
    SpaceStationRecipe recipe;


    @Override
    public void Setup(Scanner sc) {

        /***
         * inicializálás
         */
        a = new Asteroid();
        a.GetInventory().setSize(10);
        ss = new SpaceShip(a);
        ss.getInventory().setSize(10);
        recipe = new SpaceStationRecipe();


        System.out.println("Does your SpaceShip have enough resources to build the station? (Y/N)");
        String answer = sc.nextLine();
        /**
         * ha a SpaceShip-ben van elég alapanyag, akkor pl. inicializálhatjuk a raktárát a recept hozzávalóival
         */
        if(answer.equals("Y")) {
            for(Item i: recipe.getInput())
            ss.getInventory().InsertItem(i);
        }
        /**
         * ha a SpaceShip-ben nem, de az Aszteroidában és a SpaceShip-ben együtt van elég anyag, akkor az Aszteroida
         * raktárát inicializálhatjuk például a recept hozzávalóival. Az esetek elkülönítése azért kell, mert a
         * készítés során több loop fut le, ha az aszteroida készletét is meg kell vizsgálnunk.
         */
        else {
            System.out.println("Does the SpaceShip + Asteroid have enough resources to build the station? (Y/N)");
            answer = sc.nextLine();
            if(answer.equals("Y")) {
                for (Item i : recipe.getInput())
                    a.GetInventory().InsertItem(i);
            }
            /**
             * Ha a kettőben együtt nincs elég item a hozzávalókhoz, 1-1 item-et adunk csak hozzá a raktárakhoz (így a
             * loop-ok lefutnak, de sikertelen lesz a craftolás).
             */
            else {
                a.GetInventory().InsertItem(new Coal());
                ss.getInventory().InsertItem(new Ice());
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
