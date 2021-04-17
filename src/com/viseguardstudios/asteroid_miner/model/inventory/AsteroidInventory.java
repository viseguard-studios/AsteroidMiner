package com.viseguardstudios.asteroid_miner.model.inventory;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Aszteroida raktáráért felelős
 */
public class AsteroidInventory implements IInventory{

    /***
     * az itemek tárolója
     */
    private ArrayList<Item> items;

    /**
     * a maximális tárolóképesség
     */
    private int capacity;

    /**
     * Konstruktor: új üres lista, kapacitás beállítása
     */
    public AsteroidInventory(){
        items = new ArrayList<Item>();
        capacity = 15;
    }

    /**
     * Új elem hozzáadása a listához
     */
    @Override
    public boolean insertItem(Item item) {
        if(capacity > items.size()) {
            items.add(item);
            return true;
        } else
            return false;
    }

    /***
     *Elem eltávolítása, ha megtalálható a listában
     */

    @Override
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    /**
     * Kapacitás ellenőrzése új item hozzáadása előtt
     */

    @Override
    public boolean tryInsertItem(Item item) {
        if (capacity == items.size())
            return false;
        else
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
            i.nearSun(a);
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

    /**
     * Egy kör végén visszaállítja az itemek párolgási tulajdonságát -> listán végigiterál
     */
    public void roundEnd(){
        for(Item i: items){
            i.turnEnd();
        }
    }
}
