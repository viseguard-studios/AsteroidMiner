package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

import java.util.*;

/**
 * Egy aszteroidán vagy telepesnél található kinyert nyersanyagok (itemek) tárolója.
 * Ezt az osztályt használja az Aszteroida illetve a telepesek űrhajója a megszerzett nyersanyagok tárolására.
 */
public class Inventory {

    /**
     * Default constructor
     */
    public Inventory() {
    }

    /**
     * A tároló kapacitása.
     */
    private int size;



    /**
     * A tárolóban aktuálisan tárolt elemek.
     */
    private Set<Item> items;

    /**
     * Új elem hozzáadása a tárolóhoz, amennyiben van benne szabad hely (kapacitás).  Sikeres művelet esetén igaz, sikertelen művelet esetén hamis visszatérési értéke van.
     * @param item 
     * @return
     */
    public boolean InsertItem(Item item) {
        // TODO implement here

        Logger.log("Add the item to inventory");
        items.add(item);
        return true;
    }

    /**
     * Elem eltávolítása a tárolóból.
     * @param item
     */
    public void RemoveItem(Item item) {
        // TODO implement here
        Logger.log("Remove the item from inventory");
        try {
            items.remove(item);
        }
        catch (NullPointerException exp){}
    }

    /**
     * Ellenőrzi, hogy az adott elem elméletileg belehelyezhető-e a raktárba.
     * @return
     */
    public boolean TryInsertItem() {
        // TODO implement here

        Logger.log("Check if the inventory has enough place");
        return items.stream().count() <= size -1;
    }

    /**
     * Visszatér az inventory-ban találtahó item-ek listájával.
     * @return
     */
    public Set<Item> getItems() {
        // TODO implement here
        return null;
    }

}