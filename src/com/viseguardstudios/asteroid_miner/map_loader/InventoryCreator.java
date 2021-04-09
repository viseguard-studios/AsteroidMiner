package com.viseguardstudios.asteroid_miner.map_loader;

import com.viseguardstudios.asteroid_miner.map_loader.resource.CoalCreator;
import com.viseguardstudios.asteroid_miner.model.item.Coal;
import com.viseguardstudios.asteroid_miner.model.resource.Resource;

import java.util.ArrayList;
import java.util.Locale;

public abstract class InventoryCreator {

    /** Compiles a list of resources that need to be added. Note: empty lines are ignored
     * @param inputLines Format:
     *      * ****************
     *      *  "{ufoInventory}",
     *      *  "   {res1}",
     *      *  "",
     *      *  "   {res2}",
     *      *  "{/...Inventory}"
     *      *
     *      * ****************
     * @return
     */
    protected ArrayList<Resource> getResourcesFromList(ArrayList<String> inputLines){
        ArrayList<Resource> found = new ArrayList<>();

        for (int currentLine = 0; currentLine<inputLines.size(); currentLine++){
            String temp = inputLines.get(currentLine);
            temp = FileOpener.cutComments(temp);
            try {
                ArrayList<String> args = FileOpener.getNameAndPropsFromLine(temp);
                temp = args.get(0).toLowerCase(Locale.ROOT); //toLower for security

                switch (temp){  //TODO correct way of working?
                    case "coal":
                        found.add(new Coal()); //TODO change resources to implement current model!!!
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return found;
    }

}
