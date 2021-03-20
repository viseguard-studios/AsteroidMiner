package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Inventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;

import java.util.*;

/**
 * A telepesek által elkészíthető receptekért felel. Egy recept adott számú kibányászott nyersanyag (elem) felhasználásával elkészített dolgok pontos hozzávalóit, és az elkészült eredmény típusát tárolja.
 */
public abstract class Recipe {

    /**
     * Default constructor
     */
    public Recipe() {
    }

    /**
     * Az aktuális recepthez szükséges hozzávalók tárolása.
     */
    private Set<Item> input;

    /**
     * Meghatározza, hogy egy adott raktárban és aszteroidán lévő kibányászott nyersanyagkészlet elegendő-e az adott recept elkészítéséhez.
     * @param inv 
     * @param a 
     * @return
     */
    public bool CanCraft(Inventory inv, Asteroid a) {
        // TODO implement here
        return null;
    }

    /**
     * Akkor hívódik meg ha ténylegesen le akarjuk gyártani ezt a receptet.
     * @param inv 
     * @param a
     */
    public void Craft(Inventory inv, Asteroid a) {
        // TODO implement here
    }

    /**
     * Létrehozza a kívánt terméket a receptből.
     * @param inv 
     * @param a
     */
    protected abstract void MakeResult(Inventory inv, Asteroid a);

}