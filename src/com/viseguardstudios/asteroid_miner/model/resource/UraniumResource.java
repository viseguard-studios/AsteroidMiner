package com.viseguardstudios.asteroid_miner.model.resource;

/**
 * Egy adott aszteroidában tárolt urán, adott számú egységgel rendelkező, bányászással kinyerhető nyersanyagkészletért felel.
 */
public class UraniumResource extends Resource {

    /**
     * Default constructor
     */
    public UraniumResource() {
    }

    /**
     * Meghatározza, hogy az átadott Resource használható-e uránium helyett.
     * @param r 
     * @return
     */
    public boolean Satisfies(Resource r) {
        // TODO implement here
        return false;
    }

    /**
     * Napközelben a nyersanyag típusának megfelelő műveletet hajt végre.  Az uránium, ha megfúrt aszteroidában van, akkor felrobban.
     */
    public void NearSun() {
        // TODO implement here
    }


}