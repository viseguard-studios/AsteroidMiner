package com.viseguardstudios.asteroid_miner.model.entities;

import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.util.Vector2;

/**
 * Egy adott entitás ( vizuális megjelenítéssel rendelkező játékelem) osztálya.
 */
public abstract class Entity {

    protected String name;

    /**
     * Név lekérdezése
     * @return
     */
    public String getName(){
        return new String(name);
    }

    /**
     * Név átállítása
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Default constructor
     */
    public Entity() {
    }

    /**
     * A játéktér tárolója.
     */
    protected Scene scene;


    public Vector2 pos;

    /**
     * Akkor hívódik meg, ha az adott körben már minden játékos lépett. A robotok ezt használják például a mozgásra.
     * @param closeToSun
     */
    public abstract void RoundEnd(boolean closeToSun);

    /**
     * A játéktér getter-e.
     * @return
     */
    public Scene GetScene() {
        // TODO implement here
        return scene;
    }

    /**
     * Napviharról értesíti az egységet.
     */
    public abstract void SolarFlare();

    public void setScene(Scene s) {
        this.scene = s;
    }
}