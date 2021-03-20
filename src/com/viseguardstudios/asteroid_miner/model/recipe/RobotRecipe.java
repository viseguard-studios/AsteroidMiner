package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Inventory;

/**
 * Robotok legyártásáért felelős osztály.
 */
public class RobotRecipe extends Recipe {

    /**
     * Default constructor
     */
    public RobotRecipe() {
    }

    /**
     * Létrehozza a kívánt terméket a receptből.
     * @param inv 
     * @param a
     */
    protected abstract void MakeResult(Inventory inv, Asteroid a);

}