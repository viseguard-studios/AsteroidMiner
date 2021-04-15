package com.viseguardstudios.asteroid_miner.model.entities.building;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

import java.util.*;

/**
 * A játékosok által megépítendő űrállomás épület típust jelöli. Speciális tulajdonsága, hogy amikor megépül akkor a játék befejeződik.
 */
public class SpaceStation extends Building {

    /**
     * Default constructor
     */
    public SpaceStation() {
    }

    @Override
    public void RoundEnd(boolean closeToSun) {

    }

    @Override
    public void SolarFlare() {

    }


    /**
     * Meghívódik ha az adott aszteroidán egy űrállomás épült. Ekkor a játék befejeződik.
     * @param a 
     */
    public SpaceStation (Asteroid a) {
        /***
         * Elhelyezzük a megfelelő aszteroidán
         */
        Logger.functionCalled("a.AddBuilding(this)");
        a.AddBuilding(this);
        Logger.returned();
        /****
         * Jelezzük a game manager-nek, hogy a játékot megnyertük, felépült az állomás
         */
        Logger.functionCalled("a.GetScene()");
        Scene scene = a.GetScene();
        Logger.returned();
        Logger.functionCalled("scene.GetManager()");
        GameManager manager = scene.GetManager();
        Logger.returned();
        Logger.functionCalled("manager.EndGame()");
        manager.EndGame();
        Logger.returned();
    }

    /**
     * Az űrállomás felrobbanásakor hívódik meg.  Ez gyakorlatban sosem fog megtörténni, hiszen amint felépül az űrállomás, a játékot megnyerik.
     * @param a
     */
    public void Explode(Asteroid a) {
        // TODO implement here
    }

    /**
     * Az ebből az épületből elérhető extra aszteroidákat adja vissza.
     * @return
     */
    public Set<Asteroid> GetRoutes() {
        // TODO implement here
        return null;
    }

}