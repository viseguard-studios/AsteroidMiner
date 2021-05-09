package com.viseguardstudios.asteroid_miner.model.inventory;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.item.Item;

import java.util.ArrayList;

/**
 * Egy UFO végtelen tárhelyű raktáráért felelős osztály.
 */

public class InfiniteInventory implements IInventory{
    /***
     * az itemek tárolója
     */
    private ArrayList<Item> items;

    /**
     * Konstruktor: új üres lista
     */
    public InfiniteInventory(){
        items = new ArrayList<Item>();
    }

    /**
     * Új elem hozzáadása a listához, mindig van rá kapacitás
     */
    @Override
    public boolean insertItem(Item item) {
        items.add(item);
        item.setInventory(this);
        return true;
    }

    /***
     *Elem eltávolítása, ha megtalálható a listában
     */

    @Override
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    /**
     * Mindig van kapacitás új elemet hozzáadni
     */

    @Override
    public boolean tryInsertItem(Item item) {
        return true;
    }

    /**
     * Itemek listájának getter-e
     */
    @Override
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Minden item nearSun metódusát meghívja
     */
    @Override
    public void nearSun(Asteroid a) {
        for(Item i : items){
            i.nearSun(null);
        }
    }

    /**
     * Raktár robbanása -> items tároló kiürítése
     */
    @Override
    public void explode() {
        ArrayList<Item> all = new ArrayList<Item>(items);
        for(Item i: all){
            items.remove(i);
        }
    }
}
