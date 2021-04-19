package com.viseguardstudios.asteroid_miner.map_loader;

import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.Scene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GMCreator {

    public static GameManager createManager(ArrayList<String> inputLines, Scene scene){
        //Playerek keresése
        Set<Player> players = new HashSet<>();

        String currentLine = null;
        String currentType = null;
        for (int i = 0; i>inputLines.size(); i++){
            currentLine = inputLines.get(i);
            currentType = FileOpener.getObjType(currentLine);
            if(currentType.equals("player")){
                try {
                    players.add(PlayerCreator.PlayerFromCommandLine(currentLine));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        String describer = inputLines.get(0);
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

        for (Player p: players) {
            manager.AddPlayer(p);
        }
        // TODO itt hagytam abba, miért van 2 AddPlayer

        return manager;
    }
}
