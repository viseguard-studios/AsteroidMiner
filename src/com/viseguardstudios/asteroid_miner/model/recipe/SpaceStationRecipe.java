package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.entities.building.SpaceStation;
import com.viseguardstudios.asteroid_miner.model.item.Ice;
import com.viseguardstudios.asteroid_miner.model.item.resource.*;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

/**
 * Aszteroidákra elhelyezhető épületek receptjeiért felelős osztály.
 */
public class SpaceStationRecipe extends Recipe {

    /**
     * Default constructor
     */
    public SpaceStationRecipe() {
        for(int i = 0; i<3; i++) {
            input.add(new Coal());
            input.add(new Ice());
            input.add(new Uranium());
            input.add(new Titan());
            input.add(new Iron());
        }
    }

    /**
     * Létrehozza a kívánt terméket a receptből.
     * @param ss
     */
    protected void makeResult(SpaceShip ss){
        /***
         * Új űrállomás létrehozása, konstruktor meghívása
         */
        SpaceStation station = new SpaceStation(ss.getCurrentAsteroid());
    }

}