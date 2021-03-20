package com.viseguardstudios.asteroid_miner.model.building;

import com.viseguardstudios.asteroid_miner.model.Asteroid;

import java.util.*;

/**
 * A játékosok által megépítendő űrállomás épület típust jelöli. Speciális tulajdonsága, hogy amikor megépül akkor a játék befejeződik.
 */
public class SpaceStation extends Building {

    /**
     * Default constructor
     */
    public SpaceStation() {
    }

    /**
     * Meghívódik ha az adott aszteroidán egy űrállomás épült. Ekkor a játék befejeződik.
     * @param a 
     * @return
     */
    public SpaceStation SpaceStation(Asteroid a) {
        // TODO implement here
        return null;
    }

    /**
     * Az űrállomás felrobbanásakor hívódik meg.  Ez gyakorlatban sosem fog megtörténni, hiszen amint felépül az űrállomás, a játékot megnyerik.
     * @param a
     */
    public void Explode(Asteroid a) {
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

}