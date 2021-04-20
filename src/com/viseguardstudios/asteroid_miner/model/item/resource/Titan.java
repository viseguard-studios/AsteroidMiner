package com.viseguardstudios.asteroid_miner.model.item.resource;

import com.viseguardstudios.asteroid_miner.model.item.Item;

/**
 * A titán reprezentálására szolgál.
 */
public class Titan extends Resource {

    public Titan(){
        type = "Titan";
    }

    /**
     * Meghatározza, hogy az átadott Item használható-e a jelenlegi helyett.
     */
    @Override
    public boolean satisfies(Item i) {
        if(i instanceof Titan)
            return true;
        else //nem egyezik az elemtípus
            return false;
    }

    @Override
    public String getName() {
        return "Titan";
    }

}