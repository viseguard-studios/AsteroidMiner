package com.viseguardstudios.asteroid_miner.model.resource;

/**
 * Egy adott aszteroidában tárolt jég, adott számú egységgel rendelkező, bányászással kinyerhető nyersanyagkészletért felel.
 */
public class IceResource extends Resource {

    /**
     * Default constructor
     */
    public IceResource() {
    }

    /**
     * Meghatározza, hogy az átadott Resource használható-e a jég helyett.
     * @param r 
     * @return
     */
    public bool Satisfies(Resource r) {
        // TODO implement here
        return null;
    }

    /**
     * Napközelben a nyersanyag típusának megfelelő műveletet hajt végre.  A jég körönként 1 egységnyi sebességgel párolog.
     */
    public void NearSun() {
        // TODO implement here
    }

    /**
     * Napközelben a nyersanyag típusának megfelelő műveletet hajt végre. Különleges képességekkel nem rendelkező nyersanyagok esetén nem hajt végre műveletet.
     */
    public abstract void NearSun();

}