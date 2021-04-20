package com.viseguardstudios.asteroid_miner.map_loader;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;

import java.awt.*;
import java.util.Collection;
import java.util.List;

/**
 * Mr. Connector feladata, hogy az aszteroidák közötti kapcsolat felépítésétét végezze
 */
public class MrConnector {

    /**
     * Aszteroida keresése név alapján. Null, ha nincs találat.
     * @param asteroids
     * @param name
     * @return
     */
    public static Asteroid asteroidByName(Collection<Asteroid> asteroids, String name){
        for (Asteroid ast: asteroids) {
            if(ast.getName().equals(name)){
                return ast;
            }
        }
        return null;
    }

    /**
     * Szomszédsági háló létrehozása. Teleportok nélül!
     * @param asteroids
     * @param describers
     * @return
     */
    public static void createNeighborhood(Collection<Asteroid> asteroids, List<String> describers){
        for (int i = 0; i<describers.size(); i++){
        String rawLine = describers.get(i);
        if(FileOpener.getObjType(rawLine).equals("neighbours")) {
                String ast1raw = FileOpener.getPropValue(rawLine, "ast1");
                String ast2raw = FileOpener.getPropValue(rawLine, "ast2");
                Asteroid ast1 = asteroidByName(asteroids,ast1raw);
                Asteroid ast2 = asteroidByName(asteroids,ast2raw);
                ast1.addNeighbour(ast2);
                ast2.addNeighbour(ast1);
            }
        }

    }

}
