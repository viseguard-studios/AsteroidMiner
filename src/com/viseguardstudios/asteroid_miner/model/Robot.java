package com.viseguardstudios.asteroid_miner.model;

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
     * A jelenlegi aszeroida ezen keresztül szól a telep/robot-nak, hogy felrobbant.
     */
    public void AsteroidExploded(){

    }



}