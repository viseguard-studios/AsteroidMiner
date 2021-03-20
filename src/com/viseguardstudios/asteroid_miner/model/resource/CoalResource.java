package com.viseguardstudios.asteroid_miner.model.resource;

/**
 * Egy adott aszteroidában tárolt szén, adott számú egységgel rendelkező, bányászással kinyerhető nyersanyagkészletért felel.
 */
public class CoalResource extends Resource {

    /**
     * Default constructor
     */
    public CoalResource() {
    }

    /**
     * Meghatározza, hogy az átadott Resource használható-e a szén helyett.
     * @param r 
     * @return
     */
    public boolean Satisfies(Resource r) {
        // TODO implement here
        return false;
    }

    /**
     * Napközelben a nyersanyag típusának megfelelő műveletet hajt végre. Különleges képességekkel nem rendelkező nyersanyagok esetén nem hajt végre műveletet.
     */
    public void NearSun(){};

}