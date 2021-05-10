package com.viseguardstudios.asteroid_miner.model.inventory;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.item.TeleportGateItem;

import java.util.ArrayList;

/**
 * Telepes raktáráért felelős
 */

public class SSInventory implements IInventory{
    /***
     * az itemek tárolója
     */
    private ArrayList<Item> items;
    /**
     * teleportkapuk listája
     */
    private ArrayList<TeleportGateItem> tgs;

    /**
     * a maximális tárolóképesség
     */
    private int capacity;
    /**
     * teleportkapu tárolási kapacitás
     */
    private int tgCapacity;



    /**
     * Konstruktor: új üres lista, kapacitás beállítása
     */
    public SSInventory(){
        items = new ArrayList<Item>();
        tgs = new ArrayList<TeleportGateItem>();
        capacity = 15;
        tgCapacity = 3;
    }

    /**
     * Új elem, illetve teleportkapu hozzáadása a listához
     */
    @Override
    public boolean insertItem(Item item) {
        if(capacity > items.size()) {
            items.add(item);
            item.setInventory(this);
            return true;
        } else
            return false;
    }

    public boolean insertGate(TeleportGateItem gate){
        if(tgCapacity > tgs.size()) {
            tgs.add(gate);
            return true;
        } else
            return false;
    }
    /***
     *Elem, illetve teleportkapu eltávolítása, ha megtalálható a listában
     */

    @Override
    public boolean removeItem(Item item) {
        for (int i = 0; i < items.size(); i++) {
            var it = items.get(i);
            if(it.satisfies(item)){
                items.remove(it);
                return true;
            }
        }

        for (int i = 0; i < tgs.size(); i++) {
            var it = tgs.get(i);
            if(it.satisfies(item)){
                tgs.remove(it);
                return true;
            }
        }

        return false;
    }

    public boolean removeGate(TeleportGateItem gate){
        return items.remove(gate);
    }

    /**
     * Kapacitás ellenőrzése új item, illetve teleportkapu hozzáadása előtt
     */

    @Override
    public boolean tryInsertItem(Item item) {
        if (capacity == items.size())
            return false;
        else
            return true;
    }

    public boolean tryInsertGate(TeleportGateItem gate){
        if(tgCapacity==items.size())
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

    public ArrayList<TeleportGateItem> getGates() {return tgs;}
    public int getTgCapacity(){return tgCapacity;}

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
