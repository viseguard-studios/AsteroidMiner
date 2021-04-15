package com.viseguardstudios.asteroid_miner.model.building;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;

import java.util.*;

/**
 * Az épülettípusok közös ősosztálya.
 */
public abstract class Building {

    /**
     * Default constructor
     */
    public Building() {
    }

    /**
     * Az épület lerakási helye (aszteroida).
     */
    private Asteroid asteroid;

    /**
     * Azt jelöli,hogy az épület hol helyezkedik el az aszteroidán.
     */
    private BuildingPlace position;

    /**
     * Akkor hívódik meg, ha valamilyen okból az épület megsemmisül.
     */
    public void Explode() {
        // TODO implement here
    }

    /**
     * Az ebből az épületből elérhető extra aszteroidákat adja vissza.
     * @return
     */
    public Set<Asteroid> GetRoutes() {
        // TODO implement here
        return null;
    }

    /**
     * Az osztály konstruktora, beállítja az aktuális tartózkodási helyet.
     * @param a 
     * @return
     */
    public Building Building(Asteroid a) {
        // TODO implement here
        return null;
    }

}