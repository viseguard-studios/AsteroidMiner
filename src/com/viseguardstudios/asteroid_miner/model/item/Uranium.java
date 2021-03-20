package com.viseguardstudios.asteroid_miner.model.item;

import com.viseguardstudios.asteroid_miner.model.item.Item;

/**
 * Az uránium reprezentálására szolgál az com.viseguardstudios.asteroid_miner.model.Inventory-ban.
 */
public class Uranium extends Item {

    /**
     * Default constructor
     */
    public Uranium() {
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