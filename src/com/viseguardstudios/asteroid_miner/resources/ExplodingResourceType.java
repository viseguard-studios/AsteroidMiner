package com.viseguardstudios.asteroid_miner.resources;

import com.viseguardstudios.asteroid_miner.Asteroid;

public class ExplodingResourceType extends ResourceType {

    public ExplodingResourceType(String name) {
        super(name, "item:iron");
    }

    @Override
    public void NearSun(Asteroid asteroid, Resource resource) {
        super.NearSun(asteroid, resource);

        if(asteroid.IsCoreExposed()){
            asteroid.Explode();
        }

    }
}
