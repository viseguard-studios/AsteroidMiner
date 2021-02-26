package com.viseguardstudios.asteroid_miner.resources;

import com.viseguardstudios.asteroid_miner.Asteroid;
import com.viseguardstudios.asteroid_miner.Item;

public class Resource {

    ResourceType type;
    int amount;

    public Resource(ResourceType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public Item Mine(){
        return null;
    }

    public void NearSun(Asteroid asteroid) {
        type.NearSun(asteroid, this);
    }
}
