package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.*;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Robot;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.item.resource.Coal;
import com.viseguardstudios.asteroid_miner.model.item.resource.Iron;
import com.viseguardstudios.asteroid_miner.model.item.resource.Uranium;
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
    protected void makeResult(SpaceShip ss){
        Player p = ss.getOwner();
        //TODO generate new name
        // Mire gondolsz??
        Robot r = new Robot(p,ss.getCurrentAsteroid(),"r");
    }

}