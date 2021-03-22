package com.viseguardstudios.asteroid_miner.model.item;

import com.viseguardstudios.asteroid_miner.model.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.recipe.TeleportGateRecipe;

/**
 * Teleportkapu amikor még a telepesnél van.
 */
public class TeleportGateItem extends Item {

    /**
     * Default constructor
     */
    public TeleportGateItem() {
    }

    /**
     * Meghatározza, hogy az átadott item használható-e a jelenlegi helyett, és ha igen, milyen mennyiségben.
     * Ha nem használható, 0-val tér vissza.
     * @param i
     * @return amount
     */
    @Override
    public int Satisfies(Item i) {
        if(i instanceof TeleportGateItem){
            if(i.getAmount()<=this.amount) //megvan az összes szükséges darab
                return i.getAmount();
            else
                return this.amount; //kevesebb darab van a szükségesnél
        }
        else //nem egyezik az elemtípus
            return 0;
    }

    /**
     * A teleportkapuk lehelyezését végző függvény.
     * @param s
     */
    public void Activate(SpaceShip s) {
        // TODO implement here
    }
}