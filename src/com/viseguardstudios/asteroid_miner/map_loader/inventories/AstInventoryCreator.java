package com.viseguardstudios.asteroid_miner.map_loader.inventories;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.InventoryCreator;
import com.viseguardstudios.asteroid_miner.model.inventory.AsteroidInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.item.resource.Resource;

import java.util.ArrayList;

public class AstInventoryCreator extends InventoryCreator {

    /**
     * Létrehoz egy aszteroida tárhelyet.
     * @param inputLines
     * ******
     * {asteroidInventory}
     * ...
     * {/asteroidInventory}
     * ******
     * @return
     */
    public static AsteroidInventory createAstInventory(ArrayList<String> inputLines){
        AsteroidInventory inventory = new AsteroidInventory(); //TODO kapacitás bevezetése!
        ArrayList<Item> items = InventoryCreator.getResources(inputLines); //itt siman at lehet adni modisitas nelkül
        for (Item item: items) {
            inventory.insertItem(item);
        }
        return inventory;
    }
}
