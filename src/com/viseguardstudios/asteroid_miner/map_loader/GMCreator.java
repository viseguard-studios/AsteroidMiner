package com.viseguardstudios.asteroid_miner.map_loader;

import com.viseguardstudios.asteroid_miner.map_loader.entities.AsteroidCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.buildings.TeleGateCreator;
import com.viseguardstudios.asteroid_miner.map_loader.entities.vessels.SpaceShipCreator;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Entity;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.entities.building.TeleportGate;
import com.viseguardstudios.asteroid_miner.model.item.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GMCreator {


    public static void createManager(ArrayList<String> inputLines, Scene scene){
        //Playerek keresése
        Set<Player> players = new HashSet<>();

        String currentLine = null;
        String currentType = null;
        for (int i = 0; i < inputLines.size(); i++){
            currentLine = inputLines.get(i);
            if(currentLine.isEmpty() || currentLine.isBlank())
                continue;

            currentType = FileOpener.getObjType(currentLine);
            if(currentType.equals("player")){
                try {
                    players.add(PlayerCreator.PlayerFromCommandLine(currentLine));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //tulajdonságok
        int[] ids = {-1,-1};
        try {
            ids = FileOpener.getChildLoc(inputLines,"astList",0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> rawAsteroidList = new ArrayList<String>(inputLines.subList(ids[0],ids[1]+1));

        String describer = rawAsteroidList.get(0);
        int sunDist = -1;
        boolean gameEnded = false;
        boolean stormQueued = false;

        String param = FileOpener.getPropValue(describer,"sunDist");
        if (param!=null){
            sunDist =  Integer.parseInt(param);
        }
        param = FileOpener.getPropValue(describer,"gameEnded");
        if (param!=null){
            try {
                gameEnded =  FileOpener.getBoolValue(param);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        param = FileOpener.getPropValue(describer,"stormQueued");
        if (param!=null){
            try {
                stormQueued =  FileOpener.getBoolValue(param);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        GameManager manager = new GameManager(sunDist,gameEnded,stormQueued);
        scene.setManager(manager);
        manager.setManagedScene(scene);

        for (Player p: players) {
            manager.addPlayer(p);
        }

        //aszteroidák

        List<Asteroid> asteroidsWithoutConnections = new ArrayList<>();
        //TODO move to FileOpener getAllChild(inputlines, "childname") IF DOES NOT WORK
        for (int i = 0; i<inputLines.size();i++){
            ids = new int[]{-1,-1};
            try {
                ids = FileOpener.getChildLoc(inputLines, "asteroid", i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(ids[0] ==-1 || ids[1] ==-1) {
                continue;
            }
            ArrayList<String> currentRawAsteroid = new ArrayList<String>(inputLines.subList(ids[0],ids[1]+1));
            asteroidsWithoutConnections.add(AsteroidCreator.createAsteroid(currentRawAsteroid,scene));
            i = ids[1]+1;
        }



        //Todo kapcsolatok felállítása, Kész?
        try {
            ids = FileOpener.getChildLoc(inputLines,"astRelations",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> rawRelations = new ArrayList<String>(inputLines.subList(ids[0],ids[1]+1));
        MrConnector.createNeighborhood(asteroidsWithoutConnections,rawRelations);
        //Asteroidák átadása a Managernek

        for (Asteroid a:asteroidsWithoutConnections
             ) {
            manager.AddAsteroid(a);
        }



        //Todo Teleportkapuk összekapcsolása Lehet hogy működik

        for (Entity e: scene.getEntities()
             ) {
            if(e.getClass().equals(TeleportGate.class))
            {
                TeleportGate tg = ((TeleportGate) e);
                TeleportGate pair = tg.getIdPair(tg.getId(), tg);
                if(pair != null){
                    tg.setPair(pair);
                    pair.setPair(tg);
                }
            }

        }

        //Todo Seed beállítása

        
        //Telepesek beregisztrálása a GameManagerbe DONE (keresd a SpaceShipCreator-ben)


        return;
    }



}
