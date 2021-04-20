package com.viseguardstudios.asteroid_miner.model.item;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.inventory.IInventory;


/**
 * Raktárban tárolható elemtípusok gyűjtőosztálya.
 */
public abstract class Item {

    private IInventory inventory;

    /**
     * Alapértelmezetten üres metódusok:
     * activate: teleportkapu felüldefiniálja -> kapuk lerakásához szükséges
     * nearSun: napközelben különleges tulajdonságú nyersanyagok felüldefiniálják
     * turnEnd: vízjég párolgási tulajdonságát visszaállítja a kör végén
     */
    public void activate(IInventory inv){}
    public void nearSun(Asteroid a){};
    public void turnEnd(){};

    /**
     *Elemek összehasonlítására szolgál
     */
    public abstract boolean satisfies(Item item);

    public abstract String getName();
}