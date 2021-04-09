package com.viseguardstudios.asteroid_miner.map_loader.resource;

import com.viseguardstudios.asteroid_miner.map_loader.ResourceCreator;
import com.viseguardstudios.asteroid_miner.model.item.Titan;

public class TitaniumCreator extends ResourceCreator {

    /**
     * Creates Titanium resource
     * @return
     */
    public static Titan createTitanium(){
        return new Titan();
    }
}
