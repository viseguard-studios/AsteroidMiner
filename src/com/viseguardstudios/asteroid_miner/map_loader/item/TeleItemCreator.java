package com.viseguardstudios.asteroid_miner.map_loader.item;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.ItemCreator;
import com.viseguardstudios.asteroid_miner.model.item.TeleportGateItem;

public class TeleItemCreator extends ItemCreator {

    public static TeleportGateItem createTeleItem(String rawLine){
    String name = "default"; //TODO kell-e név a teleportkapunak?
    int ID =-1;
    //nev
    String param = FileOpener.getPropValue(rawLine,"name");
    if (param!=null){
        name =  param;
    }
    param = FileOpener.getPropValue(rawLine,"teleId");
    if(param!=null){
        ID = Integer.parseInt(param);
    }
    return new TeleportGateItem(ID);
    }
}
