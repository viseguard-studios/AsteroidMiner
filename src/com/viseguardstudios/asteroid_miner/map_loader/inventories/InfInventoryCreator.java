package com.viseguardstudios.asteroid_miner.map_loader.inventories;

import com.viseguardstudios.asteroid_miner.map_loader.InventoryCreator;
import com.viseguardstudios.asteroid_miner.model.inventory.AsteroidInventory;
import com.viseguardstudios.asteroid_miner.model.inventory.InfiniteInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;

import java.util.ArrayList;

public class InfInventoryCreator extends InventoryCreator {

    /**
     * Létrehoz egy aszteroida tárhelyet.
     * @param inputLines
     * ******
     * {ufoInventory}
     * ...
     * {/ufoInventory}
     * ******
     * @return
     */
    public static InfiniteInventory createAstInventory(ArrayList<String> inputLines){
        InfiniteInventory inventory = new InfiniteInventory(); //TODO kell foglalkozni, ha van item?
        ArrayList<Item> items = InventoryCreator.getResources(inputLines); //itt siman at lehet adni modisitas nelkül
        for (Item item: items) {
            inventory.insertItem(item);
        }
        return inventory;
    }
}
