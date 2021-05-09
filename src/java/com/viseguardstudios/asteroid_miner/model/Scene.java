package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.model.entities.Entity;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.util.Vector2;

import java.util.*;

/**
 * Az entitások összességének összefogásáért felelős osztály.
 */
public class Scene {

    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * A játékban szereplő összes entitás tárolója.
     */
    protected List<Entity> entities = new ArrayList<>();

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
     *
     * @param e
     */
    public void addEntity(Entity e) {
        if (!entities.contains(e)) {
            entities.add(e);
            e.setScene(this);
        }
    }

    /**
     * Egy entitást kiveszük a fenntartott listákból.
     *
     * @param v
     */
    public void removeEntity(Entity v) {
        if (entities.contains(v)) entities.remove(v);
    }

    /**
     * Az összes entitás RoundEnd() metódusát meghívja, befejezi az adott kört.
     */
    public void roundEnded() {
        if (!entities.isEmpty()) {
            for (int i = 0; i < entities.size(); i++) {
                Entity e = entities.get(i);

                boolean close = false;
                if (manager.getSunDistance() < 10) {
                    close = true;
                }

                e.roundEnd(close);

            }
        }
    }

    /**
     * A manager attribútum getter-e.
     *
     * @return
     */
    public GameManager getManager() {
        return manager;
    }

    /**
     * A napviharról szóló értesítést továbbítja az com.viseguardstudios.asteroid_miner.model.entities.Entity felé
     *
     * @param pos
     * @param radius
     */
    public void solarFlare(Vector2 pos, int radius) {
        if (!entities.isEmpty()) {
            for (int i = 0; i < entities.size(); i++) {
                Entity e = entities.get(i);
                //Logger.log("e.SolarFlare();");
                e.SolarFlare();
                //Logger.returned();
            }
        }
    }

    public void setManager(GameManager gm) {
        manager = gm;
    }

    public void entityPosChanged(Entity e)
    {

    }
}