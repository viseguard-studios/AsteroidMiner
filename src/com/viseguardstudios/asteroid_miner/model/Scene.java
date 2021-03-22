package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.skeleton.Logger;

import java.util.*;

/**
 * Az entitások összességének összefogásáért felelős osztály.
 */
public class Scene {

    /**
     * Default constructor
     */
    public Scene() {
    }

    /**
     * A játékban szereplő összes entitás tárolója.
     */
    protected Set<Entity> entities;


    /**
     * Az aktuális játékmenet.
     */
    private GameManager manager;

    /**
     * Új entitás hozzáadása a játékmenethez.
     * @param e
     */
    public void AddEntity(Entity e) {
        if (!entities.contains(e)){
            entities.add(e);
        }
    }

    /**
     * Egy entitást kiveszük a fenntartott listákból.
     * @param v
     */
    public void removeEntity(Entity v) {
        if (entities.contains(v))  entities.remove(v);
    }

    /**
     * Az összes entitás RoundEnd() metódusát meghívja, befejezi az adott kört.
     */
    public void RoundEnded() {
        // TODO implement here
    }

    /**
     * A manager attribútum getter-e.
     * @return
     */
    public GameManager GetManager() {
        // TODO implement here
        return null;
    }

    /**
     * A napviharról szóló értesítést továbbítja az com.viseguardstudios.asteroid_miner.model.Entity felé
     */
    public void SolarFlare() {
        for (Entity e : entities){
            Logger.log("e.SolarFlare();");
            e.SolarFlare();
            Logger.returned();
        }
    }

    public void setManager(GameManager gm) {
        manager=gm;
    }
}