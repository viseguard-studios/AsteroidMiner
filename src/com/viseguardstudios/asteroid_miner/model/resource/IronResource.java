package com.viseguardstudios.asteroid_miner.model.resource;

/**
 * Egy adott aszteroidában tárolt vas, adott számú egységgel rendelkező, bányászással kinyerhető nyersanyagkészletért felel.
 */
public class IronResource extends Resource {

    /**
     * Default constructor
     */
    public IronResource() {
    }

    /**
     * Meghatározza, hogy az átadott Resource használható-e a vas helyett.
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
    public void NearSun() {};

}