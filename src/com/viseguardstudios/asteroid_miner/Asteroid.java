package com.viseguardstudios.asteroid_miner;

import com.viseguardstudios.asteroid_miner.resources.Resource;
import com.viseguardstudios.asteroid_miner.resources.ResourceType;
import com.viseguardstudios.virtual_engine.Entity;

public class Asteroid extends Entity {

    int depth;

    Resource resource;

    Inventory inv;

    public Asteroid(int size, ResourceType type) {
        resource = new Resource(type,size);
        inv = new Inventory(size);
    }

    public void Explode(){

    }

    public void Mine(){

    }

    @Override
    public void Update() {
        //if near sun
        resource.NearSun(this);
    }

    public boolean IsCoreExposed(){
        return depth <= 0;
    }
}
