package com.viseguardstudios.asteroid_miner.map_loader.resource;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.ResourceCreator;
import com.viseguardstudios.asteroid_miner.model.item.Uranium;

import java.util.ArrayList;

public class UraniumCreator extends ResourceCreator {
    /**
     * Creates Uranium from a command line
     * @param inputLine
     * @return
     */
    public static Uranium createUranium(String inputLine){
        String temp = FileOpener.cutComments(inputLine); // " {uranium counter="y"} "
        try {
            ArrayList<String> args = FileOpener.getNameAndPropsFromLine(temp); // "uranium" ,"counter="y""
            String rawCounter = FileOpener.getPropNameAndValue(args.get(1))[1]; // "y"
            int counter = Integer.parseInt(rawCounter); // y
            return new Uranium(counter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
