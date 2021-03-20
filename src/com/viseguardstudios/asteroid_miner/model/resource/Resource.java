package com.viseguardstudios.asteroid_miner.model.resource;

import com.viseguardstudios.asteroid_miner.model.item.Item;

/**
 * Egy adott aszteroidában tárolt egy fajta (egy konkrét típusú), adott számú egységgel rendelkező, bányászással kinyerhető nyersanyagkészletért felel.
 */
public abstract class Resource {

    /**
     * Default constructor
     */
    public Resource() {
    }

    /**
     * A játék adott pillanatában ennyi egységnyi nyersanyag bányászható még ki az aszteroidából.
     */
    private int amount;



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

    /**
     * Meghatározza, hogy az átadott com.viseguardstudios.asteroid_miner.model.item.Item használható-e a jelenlegi helyett.
     * @param r 
     * @return
     */
    public abstract boolean Satisfies(Resource r);

}