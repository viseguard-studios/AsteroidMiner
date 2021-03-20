package com.viseguardstudios.asteroid_miner.model.item;

import com.viseguardstudios.asteroid_miner.model.item.Item;

/**
 * A jég reprezentálására szolgál az com.viseguardstudios.asteroid_miner.model.Inventory-ban.
 */
public class Ice extends Item {

    /**
     * Default constructor
     */
    public Ice() {
    }

    /**
     * Meghatározza, hogy az átadott com.viseguardstudios.asteroid_miner.model.item.Item használható-e a jelenlegi helyett.
     * @param i 
     * @return
     */
    public boolean Satisfies(Item i) {
        // TODO implement here
        return false;
    }

}