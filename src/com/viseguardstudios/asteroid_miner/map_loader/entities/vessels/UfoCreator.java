package com.viseguardstudios.asteroid_miner.map_loader.entities.vessels;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.InventoryCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.VesselCreator;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.UFO;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.item.TeleportGateItem;

import java.util.ArrayList;

public class UfoCreator  extends VesselCreator {
    @Deprecated
    @Override
    public void create() {
        scene.AddEntity(new UFO(new Asteroid())); //creating UFO from thin air
    }

    @Deprecated
    public UfoCreator(Scene scene) {
        super(scene);
    }

    @Deprecated
    public void create(Asteroid a)
    {
        scene.AddEntity(new UFO(a));
    }

    public static UFO createUFO(ArrayList<String> rawLines, Asteroid home) throws Exception {
        String describer = rawLines.get(0);
        String name = "default";
        String param = FileOpener.getPropValue(describer,"name");
        if (param!=null){
            name =  param;
        }

        //Inventory létrehozása

        int[] ids = {-1,-1};
        try {
            ids = FileOpener.getChildLoc(rawLines,"ufoInventory",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ids[0]==-1||ids[1]==-1) throw new Exception("End of resource describer not found.");

        UFO ufo = new UFO(home,name);

        ArrayList<String> rawResources = new ArrayList<String>(rawLines.subList(ids[0],ids[1]+1));
        ArrayList<Item> resources = InventoryCreator.getResources(rawResources);

        for (Item res: resources) {
            ufo.getInventory().insertItem(res);
        }

        return ufo;


    }

}
