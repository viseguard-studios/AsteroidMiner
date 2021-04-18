package com.viseguardstudios.asteroid_miner.model.entities.building;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.MovableEntity;

import java.util.*;

/**
 * Az épülettípusok közös ősosztálya.
 */
public abstract class Building extends MovableEntity {

    /**
     * Felüldefiniálandó metódus, az épületből elérhető extra aszteroidákat adja vissza
     */
    public Asteroid getRoutes() {
        return null;
    }

}