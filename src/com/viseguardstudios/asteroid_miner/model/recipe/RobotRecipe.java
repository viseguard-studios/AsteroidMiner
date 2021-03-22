package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.*;
import com.viseguardstudios.asteroid_miner.model.item.Coal;
import com.viseguardstudios.asteroid_miner.model.item.Iron;
import com.viseguardstudios.asteroid_miner.model.item.Uranium;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

/**
 * Robotok legyártásáért felelős osztály.
 */
public class RobotRecipe extends Recipe {

    /**
     * Default constructor
     */
    public RobotRecipe() {
        input.add(new Iron());
        input.add(new Coal());
        input.add(new Uranium());
    }

    /**
     * Létrehozza a kívánt terméket a receptből.
     * @param ss
     */
    protected void MakeResult(SpaceShip ss){
        /***
         * tulajdonos lekérdezése
         */
        Logger.functionCalled("ss.getOwner()");
        Player p = ss.getOwner();
        Logger.returned();

        /***
         * új robot létrehozása, konstruktor meghívása
         */
        Logger.lognl("Creating a robot, constructor: ");
        Logger.functionCalled("Robot(p,ss.getCurrentAsteroid())");
        Robot r = new Robot(p,ss.getCurrentAsteroid());
        Logger.returned();
    };

}