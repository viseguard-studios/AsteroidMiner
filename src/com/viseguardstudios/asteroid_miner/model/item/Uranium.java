package com.viseguardstudios.asteroid_miner.model.item;

import com.viseguardstudios.asteroid_miner.model.item.Item;

/**
 * Az uránium reprezentálására szolgál az com.viseguardstudios.asteroid_miner.model.Inventory-ban.
 */
public class Uranium extends Item {

    /**
     * Default constructor
     */
    public Uranium() {
    }

    /**
     * Meghatározza, hogy az átadott item használható-e a jelenlegi helyett, és ha igen, milyen mennyiségben.
     * Ha nem használható, 0-val tér vissza.
     * @param i
     * @return amount
     */
    @Override
    public int Satisfies(Item i) {
        if(i instanceof Uranium){
            if(i.getAmount()<=this.amount) //megvan az összes szükséges darab
                return i.getAmount();
            else
                return this.amount; //kevesebb darab van a szükségesnél
        }
        else //nem egyezik az elemtípus
            return 0;
    }

}