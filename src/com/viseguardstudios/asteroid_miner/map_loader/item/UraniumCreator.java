package com.viseguardstudios.asteroid_miner.map_loader.item;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.ItemCreator;

import com.viseguardstudios.asteroid_miner.model.item.resource.Uranium;

public class UraniumCreator extends ItemCreator {
    /**
     * Uránium létrehozása az őt tartalmazó sorból
     * @param inputLine
     * @return null, ha sikertelen, egyébként az urán
     */
    public static Uranium createUranium(String inputLine){
        String param = FileOpener.getPropValue(inputLine,"counter");

            int counter = 0;
            if(param != null){
                counter = Integer.parseInt(param);
            }

        return new Uranium(counter);
    }
}
