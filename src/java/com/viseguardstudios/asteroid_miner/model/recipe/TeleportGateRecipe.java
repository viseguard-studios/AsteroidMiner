package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.entities.building.TeleportGate;
import com.viseguardstudios.asteroid_miner.model.inventory.AsteroidInventory;
import com.viseguardstudios.asteroid_miner.model.inventory.SSInventory;
import com.viseguardstudios.asteroid_miner.model.item.*;
import com.viseguardstudios.asteroid_miner.model.item.resource.Ice;
import com.viseguardstudios.asteroid_miner.model.item.resource.Iron;
import com.viseguardstudios.asteroid_miner.model.item.resource.Uranium;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Teleportkapu-párok elkészítéséhez szükséges speciális recept típus.
 */
public class TeleportGateRecipe extends Recipe {

    /**
     * Default constructor
     */
    public TeleportGateRecipe() {
        input.add(new Iron());
        input.add(new Iron());
        input.add(new Ice());
        input.add(new Uranium());
    }

    /**
     * A sikeres készítés után ilyen típusú eredményhez ("elemhez") lehet hozzájutni.
     */
    private TeleportGate result = new TeleportGate(-1);

    /**
     * Létrehozza a kívánt terméket a receptből, figyelembe véve a telepes tárhelyét.
     * @param ss
     */

    @Override
    public boolean canCraft(SpaceShip ss) {
        //ha a 2 teleportkapunak van elég hely a raktárban
        if(ss.getInventory().getTgCapacity() >= (ss.getInventory().getGates().size()+2))
            return super.canCraft(ss);
        else
            return false;
    }



    protected void makeResult(SpaceShip ss){

        /***
         * Új teleportkapu-pár létrehozása, konstruktor meghívása
         */
        int id = result.generateId();
        TeleportGateItem gate1 = new TeleportGateItem(id);
        TeleportGateItem gate2 = new TeleportGateItem(id);
        /***
         * A telepes raktárába belehelyezzük a kapukat
         */
        ss.getInventory().insertGate(gate1);
        ss.getInventory().insertGate(gate2);
    }



}