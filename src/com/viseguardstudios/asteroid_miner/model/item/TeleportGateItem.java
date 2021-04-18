package com.viseguardstudios.asteroid_miner.model.item;

import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.entities.building.TeleportGate;
import com.viseguardstudios.asteroid_miner.model.inventory.SSInventory;

/**
 * A még le nem rakott teleportkapukért felelős osztály, amiket a telepes a raktárában tárol.
 */
public class TeleportGateItem extends Item {

    private int id; //azonosító

    /**
     * Konstruktor id beállítással
     */
    public TeleportGateItem(int i){
        id = i;
    }


    /**
     * Meghatározza, hogy az átadott Item használható-e a jelenlegi helyett.
     */
    @Override
    public boolean satisfies(Item item) {
        if(item instanceof TeleportGateItem)
            return true;
        else
            return false;
    }

    /**
     * Teleportkapuk lerakásáért felel.
     */

    public void activate(SSInventory inv){
        //eltávolítja magát a raktárból
        inv.removeGate(this);
        //kapu létrehozása
        TeleportGate gate = new TeleportGate(id);
        //pár megkeresése
        TeleportGate pair = gate.getIdPair(id);
        //ha megtalálta a párt -> beállítja a kapuknál is
        if(pair != null){
            gate.setPair(pair);
            pair.setPair(gate);
        }
        //hozzáadja az Id-ket tartalmazó listához a most létrehozott kaput
        gate.addIdListItem(gate);
    }
}