package com.viseguardstudios.asteroid_miner.map_loader.item;

import com.viseguardstudios.asteroid_miner.map_loader.ItemCreator;
import com.viseguardstudios.asteroid_miner.model.item.resource.Coal;

public class CoalCreator extends ItemCreator {

    /**
     * Creates Coal
     * @return
     */
    public static Coal createCoal(){
        return new Coal();
    }
}
