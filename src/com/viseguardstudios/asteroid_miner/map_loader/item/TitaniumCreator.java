package com.viseguardstudios.asteroid_miner.map_loader.item;

import com.viseguardstudios.asteroid_miner.map_loader.ItemCreator;
import com.viseguardstudios.asteroid_miner.model.item.resource.Titan;

public class TitaniumCreator extends ItemCreator {

    /**
     * Creates Titanium resource
     * @return
     */
    public static Titan createTitanium(){
        return new Titan();
    }
}
