package com.viseguardstudios.asteroid_miner.model.inventory;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.item.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

/**
 * Interfész, ami a különböző raktártípusoktól elvárt megvalósítandó metódusokat gyűjti
 * össze.
 */

public interface IInventory {
    /**
     * Új elem hozzáadása a tárolóhoz, amennyiben van benne szabad hely (kapacitás).
     * Sikeres művelet esetén igaz, sikertelen művelet esetén hamis visszatérési értéke van.
     * @param item
     * @return sikeres-e
     */
    public boolean insertItem(Item item);

    /**
     * Elem eltávolítása a tárolóból, amennyiben ez lehetséges
     * @param item
     * @return sikeres-e
     */
    public boolean removeItem(Item item);

    /**
     * Ellenőrzi, hogy az adott elem elméletileg belehelyezhető-e a raktárba.
     * @param item
     * @return lehetséges-e
     */
    public boolean tryInsertItem(Item item);

    /**
     * Visszatér az inventory-ban található item-ek listájával.
     * @return lista
     */
    public ArrayList<Item> getItems();

    /**
     * Napközelben hívódik meg, minden elem NearSun metódusát meghívja.
     * @param a - aszteroida
     */
    public void nearSun(Asteroid a);

    /**
     * Felrobban a raktár, el kell távolítani az összes elemet (removeitem metódussal végigiterálva az items listán).
     */
    public void explode();
}
