package com.viseguardstudios.asteroid_miner.model.item;

import com.viseguardstudios.asteroid_miner.model.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.item.Item;

/**
 * Teleportkapu amikor még a telepesnél van.
 */
public class TeleportGateItem extends Item {

    /**
     * Default constructor
     */
    public TeleportGateItem() {
    }

    /**
     * Meghatározza, hogy az átadott com.viseguardstudios.asteroid_miner.model.item.Item használható-e a jelenlegi helyett.
     * @param i 
     * @return
     */
    public bool Satisfies(Item i) {
        // TODO implement here
        return null;
    }

    /**
     * A teleportkapuk lehelyezését végző függvény.
     * @param s
     */
    public void Activate(SpaceShip s) {
        // TODO implement here
    }

    /**
     * Meghatározza, hogy az átadott com.viseguardstudios.asteroid_miner.model.item.Item használható-e a jelenlegi helyett.
     * @param i 
     * @return
     */
    public abstract bool Satisfies(Item i);

}