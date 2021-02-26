package com.viseguardstudios.asteroid_miner.registry;

import com.viseguardstudios.asteroid_miner.ItemType;
import com.viseguardstudios.asteroid_miner.resources.ExplodingResourceType;
import com.viseguardstudios.asteroid_miner.resources.ResourceType;

public class GameRegistries {

    public static final Registry<ResourceType> RESOURCE_TYPES = new Registry<>();
    public static final Registry<ItemType> ITEM_TYPES = new Registry<>();


    public void Register(){
        RESOURCE_TYPES.Register("iron",new ResourceType("Iron", "item:iron"));


        RESOURCE_TYPES.Register("coal",new ResourceType("Coal", "item:iron"));
        RESOURCE_TYPES.Register("uran",new ExplodingResourceType("Uranium"));
    }



}
