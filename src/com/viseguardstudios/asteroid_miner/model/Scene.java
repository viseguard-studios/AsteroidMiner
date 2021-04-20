package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.model.entities.Entity;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

import java.util.*;

/**
 * Az entitások összességének összefogásáért felelős osztály.
 */
public class Scene {

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * A játékban szereplő összes entitás tárolója.
     */
    protected ArrayList<Entity> entities  = new ArrayList<>();

    /**
     * Default constructor
     */
    public Scene() {
    }


    /**
     * Az aktuális játékmenet.
     */
    private GameManager manager;

    /**
     * Új entitás hozzáadása a játékmenethez.
     * @param e
     */
    public void addEntity(Entity e) {
        if (!entities.contains(e)){
            entities.add(e);
            e.setScene(this);
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
        return manager;
    }

    /**
     * A napviharról szóló értesítést továbbítja az com.viseguardstudios.asteroid_miner.model.entities.Entity felé
     */
    public void SolarFlare() {
        if (!entities.isEmpty()){
            for (Entity e : entities){
                Logger.log("e.SolarFlare();");
                e.SolarFlare();
                Logger.returned();
            }
        }
    }

    public void setManager(GameManager gm) {
        manager=gm;
    }
}