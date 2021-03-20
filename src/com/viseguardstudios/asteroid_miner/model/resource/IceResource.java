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
    public boolean Satisfies(Resource r) {
        // TODO implement here
        return false;
    }

    /**
     * Napközelben a nyersanyag típusának megfelelő műveletet hajt végre.  A jég körönként 1 egységnyi sebességgel párolog.
     */
    public void NearSun() {
        // TODO implement here
    }

}