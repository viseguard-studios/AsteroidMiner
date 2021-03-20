package com.viseguardstudios.asteroid_miner.model.item;

import com.viseguardstudios.asteroid_miner.model.item.Item;

/**
 * A titán reprezentálására szolgál az com.viseguardstudios.asteroid_miner.model.Inventory-ban
 */
public class Titan extends Item {

    /**
     * Default constructor
     */
    public Titan() {
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
     * Meghatározza, hogy az átadott com.viseguardstudios.asteroid_miner.model.item.Item használható-e a jelenlegi helyett.
     * @param i 
     * @return
     */
    public abstract bool Satisfies(Item i);

}