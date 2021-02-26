package com.viseguardstudios.asteroid_miner.resources;

import com.viseguardstudios.asteroid_miner.Asteroid;
import com.viseguardstudios.asteroid_miner.Item;
import com.viseguardstudios.asteroid_miner.ItemType;
import com.viseguardstudios.asteroid_miner.registry.GameRegistries;

public class ResourceType {

    String name;
    ItemType result;

    public ResourceType(String name, String item_id) {
        this.name = name;
        this.result = GameRegistries.ITEM_TYPES.Get(item_id);
    }

    public ItemType GetMineResult(){
        return result;
    }

    public void NearSun(Asteroid asteroid, Resource resource) {

    }
}
