package com.viseguardstudios.asteroid_miner.model.item.resource;

import com.viseguardstudios.asteroid_miner.model.item.Item;

/**
 * Egy adott aszteroidában tárolt egy fajta (egy konkrét típusú), adott számú egységgel rendelkező, bányászással kinyerhető nyersanyagkészletért felel.
 */
public abstract class Resource extends Item {

    /**
     * Default constructor
     */
    public Resource() {
    }

    /**
     * Egy egység nyersanyag kinyerése bányászattal, amennyiben ez lehetséges (rendelkezésre áll elég nyersanyag). Ekkor egy kibányászott megfelelő elemmel tér vissza.
     * @return
     */
    public Item Mine() {
        // TODO implement here
        return null;
    }

    /**
     * Napközelben a nyersanyag típusának megfelelő műveletet hajt végre. Különleges képességekkel nem rendelkező nyersanyagok esetén nem hajt végre műveletet.
     */
    public void NearSun(){

    };

}