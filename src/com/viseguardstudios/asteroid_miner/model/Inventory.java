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
        Set<Item> item = new Set<Item>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Item> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Item item) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Item> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
    }

    /**
     * A tároló kapacitása.
     */
    private int size;



    /**
     * A tárolóban aktuálisan tárolt elemek.
     */
    private List<Item> items = new LinkedList<>();

    /**
     * Új elem hozzáadása a tárolóhoz, amennyiben van benne szabad hely (kapacitás).  Sikeres művelet esetén igaz, sikertelen művelet esetén hamis visszatérési értéke van.
     * @param item 
     * @return
     */
    public boolean InsertItem(Item item) {
        // TODO implement here

        Logger.lognl("Add the item to inventory");
        items.add(item);
        return true;
    }

    /**
     * Elem eltávolítása a tárolóból.
     * @param input
     */
    public int RemoveItem(Item input) {
        int amount = 0;
        for(Item item: items){
            Logger.functionCalled("item.Satisfies(input)");
            amount = item.Satisfies(input);
            Logger.returned();

            if(amount != 0){
                Logger.functionCalled("item.Reduce(amount)");
                item.Reduce(amount);
                Logger.returned();
            }
        }
        return amount;
    }

    /**
     * Ellenőrzi, hogy az adott elem elméletileg belehelyezhető-e a raktárba.
     * @return
     */
    public boolean TryInsertItem() {
        // TODO implement here

        Logger.lognl("Check if the inventory has enough place");
        return items.stream().count() <= size -1;
    }

    /**
     * Visszatér az inventory-ban találtahó item-ek listájával.
     * @return
     */
    public List<Item> getItems() { return items; }

    /***
     * A tárhely getter-e
     * @return size
     */
    public int getSize() { return size;}

    /***
     * A tárhely setter-e, tesztelésre
     * @param s
     */
    public void setSize(int s) {size = s;}
}
