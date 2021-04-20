package com.viseguardstudios.asteroid_miner.map_loader;

import com.viseguardstudios.asteroid_miner.map_loader.item.*;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.item.TeleportGateItem;

import java.util.ArrayList;

public abstract class ItemCreator {
    /**
     * Compiles a list of resources that need to be added. Note: empty lines are ignored
     * Note: TeleGateItems ARE NOT INCLUDED!
     *
     * @param inputLines Format:
     * ****************
     *  "{...Inventory\resorces}",
     *  "   {res1}",
     *  "",
     *  "   {res2}",
     *  "{/...Inventory\resorces}"
     * ****************
     * @return
     */
    public static ArrayList<Item> getResources(ArrayList<String> inputLines) {
        ArrayList<Item> found = new ArrayList<>();

        for (String rawLine : inputLines) {
            if(rawLine.isBlank() || rawLine.isEmpty())
                continue;

            String currentType = FileOpener.getObjType(rawLine);
            if (currentType != null) {
                switch (currentType) {
                    default:
                        break;
                    case "uranium":  //fontos, ez érzékeny a nagy- és kisbetűkre!
                    case "uran":
                        found.add(UraniumCreator.createUranium(rawLine));
                        break;
                    case "iron":
                        found.add(IronCreator.createIron());
                        break;
                    case "coal":
                        found.add(CoalCreator.createCoal());
                        break;
                    case "ice":
                        found.add(IceCreator.createIce());
                        break;
                    case "titanium":
                    case "titan":
                        found.add(TitaniumCreator.createTitanium());
                }
            }
        }
        return found;
    }

    /**
     * Compiles a list of the teleports that need to be added. Note: empty lines are ignored
     * Note: EVERY OTHER ITEM IS IGNORED!
     *
     * @param inputLines Format:
        * ****************
        *  "{...Inventory\resorces}",
        *  "   {telegateItem ...}",
        *  "",
        *  "   {telegateItem ...}",
        *  "{/...Inventory\resorces}"
        *
        * ****************
     * @return
     */
    public static ArrayList<TeleportGateItem> getTeleportGates(ArrayList<String> inputLines){
        ArrayList<TeleportGateItem> teles = new ArrayList<>();

        for (String rawLine : inputLines){
            if (FileOpener.getObjType(rawLine).equals("telegateItem")){
            teles.add(TeleItemCreator.createTeleItem(rawLine));
            }
        }
        return teles;
    }
}
