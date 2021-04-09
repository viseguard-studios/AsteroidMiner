package com.viseguardstudios.asteroid_miner.map_loader.resource;

import com.viseguardstudios.asteroid_miner.map_loader.ResourceCreator;
import com.viseguardstudios.asteroid_miner.model.item.Ice;

public class IceCreator extends ResourceCreator {


    /**
     * Creates Ice
     * @return
     */
    public static Ice createIce(){
        return new Ice();
    }
}
