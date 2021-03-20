package com.viseguardstudios.asteroid_miner.model;

/**
 * Egy speciális jármű, a robot tevékenységeit, tulajdonságait tartalmazza.
 */
public class Robot extends Vessel {

    /**
     * Default constructor
     */
    public Robot() {
    }

    /**
     * Az osztály konstruktora, beállítja az őt kezelő játékost illetve aszteroidát.
     * @param p 
     * @param a 
     * @return
     */
    public Robot Robot(Player p, Asteroid a) {
        // TODO implement here
        return null;
    }

    /**
     * A robotok által az aszteroida magjában elfoglalt hely nagyságát adja vissza. Korlátlan mennyiségű robot elfér, tehát 0 az értéke.
     * @return
     */
    public int GetHidingSpaceRequirement() {
        // TODO implement here
        return 0;
    }

    /**
     * Absztrakt metódus, leszármazottól függően más értéket ad vissza. Meghatározza, hogy ha az adott jármű el szeretne bújni egy aszteroidában, mennyi helyre van hozzá szüksége.
     * @return
     */
    public abstract int GetHidingSpaceRequirement();

    /**
     * A jelenlegi aszeroida ezen keresztül szól a telep/robot-nak, hogy felrobbant.
     */
    public abstract void AsteroidExploded();

}