package com.viseguardstudios.asteroid_miner.map_loader.entities;

import com.viseguardstudios.asteroid_miner.map_loader.EntityCreator;
import com.viseguardstudios.asteroid_miner.map_loader.FileOpener;
import com.viseguardstudios.asteroid_miner.map_loader.ItemCreator;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.util.Vector2;

import java.util.ArrayList;

public class AsteroidCreator extends EntityCreator {

    @Deprecated
    public AsteroidCreator(Scene scene) {
        super(scene);
    }
    @Deprecated
    @Override
    public void create() {
        scene.AddEntity(new Asteroid());
    }

    public static Asteroid createAsteroid(ArrayList<String> rawLines, Scene scene){
        String describer = rawLines.get(0);
        String name = "default";
        boolean exploded = false;
        Vector2 pos = new Vector2(0,0);
        int coreSize = -1;
        int maxHidingSpace = -1;
        int crustSize = -1;
        boolean revealed = false;
        boolean visted = false;

        // tulajdonságok
        String param = FileOpener.getPropValue(describer,"name");
        if (param!=null){
            name =  param;
        }
        param = FileOpener.getPropValue(describer,"position");
        if (param!=null){
            String[] rawPos=  param.split(",");
            pos = new Vector2(Integer.parseInt(rawPos[0]),Integer.parseInt(rawPos[1]));
        }
        param = FileOpener.getPropValue(describer,"exploded");
        if(param!=null){
            try {
                exploded = FileOpener.getBoolValue(param);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        param = FileOpener.getPropValue(describer,"coresize");
        if(param!=null) {
            coreSize = Integer.parseInt(param);
        }
        param = FileOpener.getPropValue(describer,"maxhidingspace");
        if(param!=null) {
            maxHidingSpace = Integer.parseInt(param);
        }
        param = FileOpener.getPropValue(describer,"crustsize");
        if(param!=null) {
            crustSize = Integer.parseInt(param);
        }
        param = FileOpener.getPropValue(describer,"revealed");
        if(param!=null) {
            try {
                revealed = FileOpener.getBoolValue(param);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        param = FileOpener.getPropValue(describer,"visited");
        if(param!=null) {
            try {
                visted = FileOpener.getBoolValue(param);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Asteroid asteroid = new Asteroid(scene, name, pos,maxHidingSpace,coreSize,crustSize,exploded,revealed,visted);

        // GYERMEKEK
        // inventory
        int[] ids = {-1,-1};

        try {
            ids = FileOpener.getChildLoc(rawLines,"asteroidInventory",0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> child = new ArrayList<String>(rawLines.subList(ids[0],ids[1]+1));
        ArrayList<Item> items = ItemCreator.getResources(child); //itt siman at lehet adni modisitas nelkül
        for (Item item: items) {
            asteroid.getInventory().insertItem(item);
        }

        //Vesselek
        ids = new int[]{-1,-1};
        try {
            ids = FileOpener.getChildLoc(rawLines,"vesselsOnAsteroid",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        child = new ArrayList<String>(rawLines.subList(ids[0],ids[1]+1));
        try {
            VesselCreator.createVessels(child,asteroid);// Létrehozza és az aszteroidára pakolja
        } catch (Exception e) {
            e.printStackTrace();
        }
        // belső mozgó entitások
        ids = new int[]{-1,-1};
        try {
            ids = FileOpener.getChildLoc(rawLines,"insideAsteroid",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        child = new ArrayList<String>(rawLines.subList(ids[0],ids[1]+1));
        try {
            BuildingCreator.createBuildings(child,asteroid);// Létrehozza és az aszteroidára pakolja
        } catch (Exception e) {
            e.printStackTrace();
        }

        // külső mozgó entitások
        ids = new int[]{-1,-1};
        try {
            ids = FileOpener.getChildLoc(rawLines,"orbitAsteroid",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        child = new ArrayList<String>(rawLines.subList(ids[0],ids[1]+1));
        try {
            BuildingCreator.createBuildings(child,asteroid);// Létrehozza és az aszteroidára pakolja
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asteroid;

    }


}
