package com.viseguardstudios.asteroid_miner.model.entities.Vessel;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.inventory.IInventory;
import com.viseguardstudios.asteroid_miner.model.inventory.InfiniteInventory;

public class UFO extends Vessel{
    InfiniteInventory inventory;

    /**
     * Default constructor
     *
    **/
    public UFO(Asteroid a) {
        super(a);
        inventory = new InfiniteInventory();
    }

    @Override
    public AsteroidPlaces getPlace() {
        return null;
    }

    @Override
    public int GetHidingSpaceRequirement() {
        return 1;
    }


    //Nem képes bányászni
    @Override
    public void Drill() {
        //Do nothing
    }

    @Override
    public IInventory getInventory() {
        return inventory;
    }
}
