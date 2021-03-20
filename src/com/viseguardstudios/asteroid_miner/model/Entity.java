package com.viseguardstudios.asteroid_miner.model;

/**
 * Egy adott entitás ( vizuális megjelenítéssel rendelkező játékelem) osztálya.
 */
public abstract class Entity {

    /**
     * Default constructor
     */
    public Entity() {
    }

    /**
     * A játéktér tárolója.
     */
    protected Scene scene;

    /**
     * Akkor hívódik meg, ha az adott körben már minden játékos lépett. A robotok ezt használják például a mozgásra.
     * @param closeToSun
     */
    public abstract void RoundEnd(bool closeToSun);

    /**
     * A játéktér getter-e.
     * @return
     */
    public Scene GetScene() {
        // TODO implement here
        return null;
    }

    /**
     * Napviharról értesíti az egységet.
     */
    public abstract void SolarFlare();

}