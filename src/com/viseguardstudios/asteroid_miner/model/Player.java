package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.model.resource.Resource;

import java.util.ArrayList;

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
     * A játékos által birtokolt űrjárművek listája.
     */
    private ArrayList<Vessel> ownedVessels = new ArrayList<>();

    /**
     * A játékos neve.
     */
    private String name;



    /**
     * Az itt szereplő nyersanyagfajta után kutatnak a játékos robotjai.
     */
    private Resource searching_for;

    /**
     * Hozzáadunk egy űrjárművet a játékoshoz
     * @param v
     */
    public void addVessel(Vessel v) {
        if (!ownedVessels.contains(v))  ownedVessels.add(v);
    }

    /**
     * Elveszünk egy űrjárművet a játékostól
     * @param v
     */
    public void removeVessel(Vessel v) {
        if (ownedVessels.contains(v))  ownedVessels.remove(v);
    }
}