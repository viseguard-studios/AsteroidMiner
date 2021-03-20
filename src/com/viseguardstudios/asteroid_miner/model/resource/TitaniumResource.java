package com.viseguardstudios.asteroid_miner.model.resource;

/**
 * Egy adott aszteroidában tárolt titán, adott számú egységgel rendelkező, bányászással kinyerhető nyersanyagkészletért felel.
 */
public class TitaniumResource extends Resource {

    /**
     * Default constructor
     */
    public TitaniumResource() {
    }

    /**
     * Meghatározza, hogy az átadott Resource használható-e a titán helyett.
     * @param r 
     * @return
     */
    public bool Satisfies(Resource r) {
        // TODO implement here
        return null;
    }

    /**
     * Napközelben a nyersanyag típusának megfelelő műveletet hajt végre. Különleges képességekkel nem rendelkező nyersanyagok esetén nem hajt végre műveletet.
     */
    public abstract void NearSun();

}