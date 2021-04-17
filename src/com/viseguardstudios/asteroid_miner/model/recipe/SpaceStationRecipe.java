package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.entities.building.SpaceStation;
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
            //input.add(new Coal(3));
            //input.add(new Ice(3));
            //input.add(new Uranium(3));
            //input.add(new Titan(3));
            //input.add(new Iron(3));
    }

    /**
     * Létrehozza a kívánt terméket a receptből.
     * @param ss
     */
    protected void MakeResult(SpaceShip ss){
        /***
         * Új űrállomás létrehozása, konstruktor meghívása
         */
        Logger.lognl("Creating a space station, constructor: ");
        Logger.functionCalled("SpaceStation(ss.getCurrentAsteroid())");
        SpaceStation station = new SpaceStation(ss.getCurrentAsteroid());
        Logger.returned();
        Logger.lognl(("Congratulations! You successfully built a space station!"));
    };

}