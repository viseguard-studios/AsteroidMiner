package com.viseguardstudios.asteroid_miner.model.item.resource;

import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.item.resource.*;

/**
 * A Vas reprezentálására szolgál.
 */
public class Iron extends Resource {

    public Iron(){
        type = "Iron";
    }

    /**
     * Meghatározza, hogy az átadott Item használható-e a jelenlegi helyett.
     */
    @Override
    public boolean satisfies(Item i) {
        if(i instanceof Iron)
            return true;
        else //nem egyezik az elemtípus
            return false;
    }

    @Override
    public String getName() {
        return "Iron";
    }

}