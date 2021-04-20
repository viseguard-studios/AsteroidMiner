package com.viseguardstudios.asteroid_miner.model.entities.Vessel;

import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.inventory.IInventory;
import com.viseguardstudios.asteroid_miner.model.inventory.InfiniteInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;

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
    public boolean placeItem(Item i) {
        return false;
    }

    /**
     * Konstruktor névvel.
     *
     **/
    public UFO(Asteroid a, String name) {
        super(a);
        this.name = name;
        this.owner = null;
        inventory = new InfiniteInventory();
    }

    @Override
    public AsteroidPlaces getPlace() {
        return AsteroidPlaces.Vessel;
    }

    @Override
    public int GetHidingSpaceRequirement() {
        return 2;
    } 


    //Nem képes bányászni
    @Override
    public boolean Drill() {
        //Do nothing
        return false;
    }

    @Override
    public void setOwner(Player p) {
        //Do nothing
    }

    @Override
    public void SolarFlare() {
        //DO not die
    }

    @Override
    public boolean Hide() {
        //DO not hide
        return false;
    }

    @Override
    public IInventory getInventory() {
        return inventory;
    }
}
