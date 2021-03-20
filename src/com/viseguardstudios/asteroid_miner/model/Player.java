package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.model.resource.Resource;

/**
 * Egy adott játékos reprezentációja.
 */
public class Player {

    /**
     * Default constructor
     */
    public Player() {
    }

    /**
     * A játékos neve.
     */
    private String name;



    /**
     * Az itt szereplő nyersanyagfajta után kutatnak a játékos robotjai.
     */
    private Resource searching_for;

}