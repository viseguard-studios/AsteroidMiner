package com.viseguardstudios.asteroid_miner.model.item;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.entities.building.TeleportGate;
import com.viseguardstudios.asteroid_miner.model.inventory.SSInventory;
import com.viseguardstudios.asteroid_miner.model.inventory.IInventory;
import com.viseguardstudios.asteroid_miner.util.Namer;

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

    @Override
    public String getName() {
        return "Teleport Gate";
    }

    /**
     * Teleportkapuk lerakásáért felel.
     */
    @Override
    public void activate(IInventory inv, SpaceShip ship){
        //eltávolítja magát a raktárból
        //inv.removeGate(this);
        inv.removeItem(this);
        //kapu létrehozása

        var ast = ship.getCurrentAsteroid();

        TeleportGate gate = new TeleportGate(ast, "", id);
        //hozzáadja az Id-ket tartalmazó listához a most létrehozott kaput
        //gate.addIdListItem(gate,id);
        //pár megkeresése
        //TeleportGate pair = gate.getIdPair(id, gate);
        //ha megtalálta a párt -> beállítja a kapuknál is
        /*
        if(pair != null){
            gate.setPair(pair);
            pair.setPair(gate);
        }
        */

        Engine.getInstance().getScene().addEntity(gate);

    }
}