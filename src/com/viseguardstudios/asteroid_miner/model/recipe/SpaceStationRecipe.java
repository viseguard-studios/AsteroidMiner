package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Inventory;

/**
 * Aszteroidákra elhelyezhető épületek receptjeiért felelős osztály.
 */
public class SpaceStationRecipe extends Recipe {

    /**
     * Default constructor
     */
    public SpaceStationRecipe() {
    }

    /**
     * Létrehozza a kívánt terméket a receptből.
     * @param inv 
     * @param a
     */
    protected void MakeResult(Inventory inv, Asteroid a){};

}