package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Inventory;
import com.viseguardstudios.asteroid_miner.model.building.TeleportGate;

/**
 * Teleportkapu-párok elkészítéséhez szükséges speciális recept típus.
 */
public class TeleportGateRecipe extends Recipe {

    /**
     * Default constructor
     */
    public TeleportGateRecipe() {
    }

    /**
     * A sikeres készítés után ilyen típusú eredményhez ("elemhez") lehet hozzájutni.
     */
    private TeleportGate result;

    /**
     * Az elkészült dolog mennyisége (egyszerre hány egység készül el belőle)
     */
    private int amount;

    /**
     * Létrehozza a kívánt terméket a receptből.
     * @param inv 
     * @param a
     */
    protected abstract void MakeResult(Inventory inv, Asteroid a);

}