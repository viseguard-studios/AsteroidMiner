package com.viseguardstudios.asteroid_miner.map_loader.resource;

import com.viseguardstudios.asteroid_miner.map_loader.ResourceCreator;
import com.viseguardstudios.asteroid_miner.model.item.Iron;

public class IronCreator extends ResourceCreator {

    /**
     * Creates Iron
     * @return
     */
    public static Iron createIron(){
        return new Iron();
    }
}
