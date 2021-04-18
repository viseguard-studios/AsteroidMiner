package com.viseguardstudios.asteroid_miner.model.entities.Vessel;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.inventory.IInventory;

/**
 * Egy speciális jármű, a robot tevékenységeit, tulajdonságait tartalmazza.
 */
public class Robot extends Vessel {

    /**
     * Default constructor
     */
    public Robot(Asteroid a) {
        super(a);
    }

    /**
     * Constructor for crafting
     * @param p
     * @param currentAsteroid
     */
    public Robot(Player p, Asteroid currentAsteroid) {
        super(currentAsteroid);
        owner = p;
        p.addVessel(this);
    }


    /**
     * A robotok által az aszteroida magjában elfoglalt hely nagyságát adja vissza. Korlátlan mennyiségű robot elfér, tehát 0 az értéke.
     * @return
     */
    public int GetHidingSpaceRequirement() {
        return 0;
    }

    /**
     * Robotnak nincs raktára
     * @return
     */
    @Override
    public IInventory getInventory() {
        return null;
    }

    /**
     * Üres metódus, robot nem tud bányászni
     */
    @Override
    public void Mine(){}

    @Override
    public void AsteroidExploded(){

    }


    @Override
    public AsteroidPlaces getPlace() {
        return AsteroidPlaces.Vessel;
    }
}