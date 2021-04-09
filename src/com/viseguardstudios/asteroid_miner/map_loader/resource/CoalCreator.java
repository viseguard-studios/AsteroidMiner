package com.viseguardstudios.asteroid_miner.map_loader.resource;

import com.viseguardstudios.asteroid_miner.map_loader.ResourceCreator;
import com.viseguardstudios.asteroid_miner.model.item.Coal;
import com.viseguardstudios.asteroid_miner.model.resource.Resource;

public class CoalCreator extends ResourceCreator {

    /**
     * Creates Coal
     * @return
     */
    public static Coal createCoal(){
        return new Coal();
    }
}
