package com.viseguardstudios.asteroid_miner.model.item.resource;

import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.item.resource.Resource;

/**
 * A szén reprezentálására szolgál.
 */
public class Coal extends Resource {

    /**
     * Meghatározza, hogy az átadott Item használható-e a jelenlegi helyett.
     */
    @Override
    public boolean satisfies(Item i) {
        if(i instanceof Coal)
            return true;
        else //nem egyezik az elemtípus
            return false;
    }

    @Override
    public String getName() {
        return "Coal";
    }

}