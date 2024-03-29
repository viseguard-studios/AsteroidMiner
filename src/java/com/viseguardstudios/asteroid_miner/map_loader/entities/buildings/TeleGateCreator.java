package com.viseguardstudios.asteroid_miner.map_loader.entities.buildings;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.entities.BuildingCreator;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.building.TeleportGate;
import com.viseguardstudios.asteroid_miner.model.item.TeleportGateItem;

public class TeleGateCreator extends BuildingCreator {

    @Deprecated
    public TeleGateCreator(Scene scene) {
        super(scene);
    }

    @Deprecated
    @Override
    public void create() {
        scene.addEntity(new TeleportGate(1));
    }

    /**
     * Teleportkapu létrehozása
     * @param rawLine
     * @return
     */
    public static TeleportGate createTeleportGate(String rawLine, Asteroid home){
        String name = "default";
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
        TeleportGate tg = new TeleportGate(home,name,ID);
        home.GetScene().addEntity(tg);
        home.GetScene().getManager().notifyListeners();
        return tg;

    }
}
