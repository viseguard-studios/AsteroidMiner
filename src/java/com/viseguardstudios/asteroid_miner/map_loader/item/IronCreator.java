package com.viseguardstudios.asteroid_miner.map_loader.item;

import com.viseguardstudios.asteroid_miner.map_loader.ItemCreator;
import com.viseguardstudios.asteroid_miner.model.item.resource.Iron;

public class IronCreator extends ItemCreator {

    /**
     * Creates Iron
     * @return
     */
    public static Iron createIron(){
        return new Iron();
    }
}
