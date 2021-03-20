package com.viseguardstudios.asteroid_miner.model.item;

import com.viseguardstudios.asteroid_miner.model.SpaceShip;

/**
 * A már kibányászott nyersanyagok hordozható elemekké válnak, ebben a formában tárolja őket az osztály. Egy típusú, adott mennyiségű azonos elemet, illetve hordozható épületet (pl. teleportkapu-pár) tárol.
 */
public abstract class Item {

    /**
     * Default constructor
     */
    public Item() {
    }

    /**
     * Az elem darabszámát tárolja.
     */
    private int amount;



    /**
     * Meghatározza, hogy az átadott com.viseguardstudios.asteroid_miner.model.item.Item használható-e a jelenlegi helyett.
     * @param i 
     * @return
     */
    public abstract boolean Satisfies(Item i);

    /**
     * A megadott számmal csökkenti az adott com.viseguardstudios.asteroid_miner.model.item.Item méretét (számosságát), amennyiben ez lehetséges.
     * @param a
     */
    public void Reduce(int a) {
        // TODO implement here
    }

    /**
     * Aktiválható tárgyaknál( pl.Teleport kapu) használt függvény. Alapból nem csinál semmit.
     * @param s
     */
    public void Activate(SpaceShip s) {
        // TODO implement here
    }

}