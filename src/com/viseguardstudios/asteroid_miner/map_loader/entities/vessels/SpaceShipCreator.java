package com.viseguardstudios.asteroid_miner.map_loader.entities.vessels;

import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.ItemCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.VesselCreator;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.item.TeleportGateItem;

import java.util.ArrayList;

public class SpaceShipCreator extends VesselCreator {

    @Deprecated
    @Override
    public void create() {
        scene.AddEntity(new SpaceShip(new Asteroid())); //creating SS from thin air
    }

    @Deprecated
    public SpaceShipCreator(Scene scene) {
        super(scene);
    }

    @Deprecated
    public void create(Asteroid a)
    {
        scene.AddEntity(new SpaceShip(a));
    }

    public static SpaceShip createSpaceShip(ArrayList<String> rawLines, Asteroid home) throws Exception {
        String describer = rawLines.get(0);
        String name = "default";
        String playerName = null;
        boolean isHidden = false; //alapértelmezetten nem bújtunk el
        String param = FileOpener.getPropValue(describer,"name");
        if (param!=null){
            name =  param;
        }
        param = FileOpener.getPropValue(describer,"player");
        if (param!=null){
            playerName =  param;
        }
        else {
            throw new Exception("SpaceShip without assigned player found!");
        }
        param = FileOpener.getPropValue(describer,"ishidden");
        if (param!=null){
            isHidden = FileOpener.getBoolValue(param);
        }

        Player owner = FileOpener.getPlayerByName(playerName); //TODO HOGY KÉRDEZZÜK LE A PLAYERT?
        SpaceShip spaceShip = new SpaceShip(home,owner,name);
        if(isHidden)
            spaceShip.Hide();

        //Inventory létrehozása

        int[] ids = {-1,-1};
        try {
            ids = FileOpener.getChildLoc(rawLines,"resources",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ids[0]==-1||ids[1]==-1) throw new Exception("End of resource describer not found.");

        ArrayList<String> rawResources = new ArrayList<String>(rawLines.subList(ids[0],ids[1]+1));
        ArrayList<Item> resources = ItemCreator.getResources(rawResources);

        for (Item res: resources) {
            spaceShip.getInventory().insertItem(res);
        }

        // Teleportkapuk betöltése
        ids = new int[]{-1, -1};
        try {
            ids = FileOpener.getChildLoc(rawLines,"telegateitem",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ids[0]==-1||ids[1]==-1) throw new Exception("End of telegates describer not found.");

        ArrayList<String> rawTeles = new ArrayList<String>(rawLines.subList(ids[0],ids[1]+1));
        ArrayList<TeleportGateItem> teles = ItemCreator.getTeleportGates(rawTeles);

        for (TeleportGateItem tele:teles) {
            spaceShip.getInventory().insertGate(tele);
        }

        return spaceShip;


    }
}
