package com.viseguardstudios.asteroid_miner.model.item.resource;

import com.viseguardstudios.asteroid_miner.model.item.Item;

/**
 * A titán reprezentálására szolgál az com.viseguardstudios.asteroid_miner.model.Inventory-ban
 */
public class Titan extends Resource {

    /**
     * Default constructor
     */
    public Titan() {
    }

    /**
     * Meghatározza, hogy az átadott item használható-e a jelenlegi helyett, és ha igen, milyen mennyiségben.
     * Ha nem használható, 0-val tér vissza.
     * @param i
     * @return amount
     */
    @Override
    public int Satisfies(Item i) {
        //TODO
        return -1;
    }

}