package com.viseguardstudios.asteroid_miner.model.entities.building;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Scene;

/**
 * A játékosok által megépítendő űrállomás épület típust jelöli. Speciális tulajdonsága, hogy amikor megépül akkor a játék befejeződik.
 */
public class SpaceStation extends Building {


    /**
     * Meghívódik ha az adott aszteroidán egy űrállomás épült. Ekkor a játék befejeződik.
     * @param a 
     */
    public SpaceStation (Asteroid a) {
        name = "default";
        /***
         * Elhelyezzük a megfelelő aszteroidán
         */
        a.AddBuilding(this);
        /****
         * Jelezzük a game manager-nek, hogy a játékot megnyertük, felépült az állomás
         */
        Scene scene = a.GetScene();
        GameManager manager = scene.GetManager();
        manager.endGame();
    }

    /**
     * Üres metódusok, miután felépült egy űrállomás, a játéknak vége,
     * ezért ezek a metódusok sosem hívódnak meg, nincs következménye.
     */

    @Override
    public void SolarFlare() {}

    @Override
    public void explode() {}

    @Override
    public void roundEnd(boolean closeToSun){
        super.roundEnd(closeToSun);
    }


    @Override
    public AsteroidPlaces getPlace() {
        return AsteroidPlaces.Inside;
    }
}