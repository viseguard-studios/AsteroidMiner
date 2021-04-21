package com.viseguardstudios.asteroid_miner.map_loader.entities.vessels;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.ItemCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.VesselCreator;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.UFO;
import com.viseguardstudios.asteroid_miner.model.item.Item;

import java.util.ArrayList;

public class UfoCreator  extends VesselCreator {
    @Deprecated
    @Override
    public void create() {
        scene.addEntity(new UFO(new Asteroid())); //creating UFO from thin air
    }

    @Deprecated
    public UfoCreator(Scene scene) {
        super(scene);
    }

    @Deprecated
    public void create(Asteroid a)
    {
        scene.addEntity(new UFO(a));
    }

    public static UFO createUFO(ArrayList<String> rawLines, GameManager manager, Asteroid home) throws Exception {
        String describer = rawLines.get(0);
        String name = "default";
        String playerName = null;
        String param = FileOpener.getPropValue(describer,"name");
        if (param!=null){
            name =  param;
        }
        //TODO
        param = FileOpener.getPropValue(describer,"ownerPlayer");
        if (param!=null){
            playerName =  param;
        }
        Player owner = manager.getPlayerByName(playerName);

        UFO ufo = new UFO(home, owner, name);

        //Inventory létrehozása

        int[] ids = {-1,-1};
        try {
            ids = FileOpener.getChildLoc(rawLines,"ufoInventory",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ids[0]!=-1||ids[1]!=-1) {// throw new Exception("End of resource describer not found.");

            ArrayList<String> rawResources = new ArrayList<String>(rawLines.subList(ids[0], ids[1] + 1));
            ArrayList<Item> resources = ItemCreator.getResources(rawResources);

            for (Item res : resources) {
                ufo.getInventory().insertItem(res);
            }
        }
        //adds ufo to scenes entities
        home.GetScene().addEntity(ufo);
        return ufo;


    }

}
