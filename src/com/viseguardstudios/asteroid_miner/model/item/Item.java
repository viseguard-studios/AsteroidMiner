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
        amount = 1;
    }

    /**
     * Az elem darabszámát tárolja.
     */
    protected int amount;



    /**
     * Meghatározza, hogy az átadott item használható-e a jelenlegi helyett, és ha igen, milyen mennyiségben.
     * Ha nem használható, 0-val tér vissza.
     * @param i 
     * @return
     */
    public abstract int Satisfies(Item i);

    /**
     * A megadott számmal csökkenti az adott com.viseguardstudios.asteroid_miner.model.item.Item méretét (számosságát), amennyiben ez lehetséges.
     * @param a
     */
    public void Reduce(int a) {
        amount-=a;
    }

    /**
     * Aktiválható tárgyaknál( pl.Teleport kapu) használt függvény. Alapból nem csinál semmit.
     * @param s
     */
    public void Activate(SpaceShip s) {
        // TODO implement here
    }

    /**
     * A rendelkezésre álló mennyiség getter-e.
     * @return amount
     */

    public int getAmount(){return amount;};

}