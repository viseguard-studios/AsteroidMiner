package com.viseguardstudios.asteroid_miner.map_loader.inventories;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.InventoryCreator;

import com.viseguardstudios.asteroid_miner.model.inventory.SSInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.item.TeleportGateItem;
import com.viseguardstudios.asteroid_miner.model.item.resource.Resource;

import java.util.ArrayList;

public class SpaceShipInventoryCreator extends InventoryCreator {

    @Deprecated
    public static SSInventory createSSInventory(ArrayList<String> inputLines) throws Exception {

        // Nyersanyagok betöltése
        SSInventory inventory = new SSInventory();
        int[] ids = {-1,-1};
        try {
             ids = FileOpener.getChildLoc(inputLines,"resources",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ids[0]==-1||ids[1]==-1) throw new Exception("End of resource describer not found.");

        ArrayList<String> rawResorces = new ArrayList<String>(inputLines.subList(ids[0],ids[1]+1));
        ArrayList<Item> resources = getResources(rawResorces);

        for (Item res: resources) {
            inventory.insertItem(res);
        }

        // Teleportkapuk betöltése
        ids = new int[]{-1, -1};
        try {
            ids = FileOpener.getChildLoc(inputLines,"telegateitem",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ids[0]==-1||ids[1]==-1) throw new Exception("End of telegates describer not found.");

        ArrayList<String> rawTeles = new ArrayList<String>(inputLines.subList(ids[0],ids[1]+1));
        ArrayList<TeleportGateItem> teles = getTeleportGates(rawTeles);

        for (TeleportGateItem tele:teles) {
            inventory.insertGate(tele);
        }

        return inventory;
    }
}
