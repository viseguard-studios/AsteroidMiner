package com.viseguardstudios.asteroid_miner.model;

import java.util.*;

/**
 * A program indításáért, egy játékfolyam bezárásáért felelős osztály.
 */
public class Engine {

    Scene scene;

    /**
     * Default constructor
     */
    public Engine() {
        System.out.println("Created Engine");
    }


    /**
     * A program indítása.
     */
    public void StartApplication() {
        System.out.println("Application started");

        //TODO Handle commands
    }

    /**
     * Egy új játék kezdése.
     */
    public void StartGame() {
        var gm = new GameManager();

        scene = new Scene();
        scene.setManager(gm);

        gm.setManagedScene(scene);

        //gm.AddPlayer();

        gm.InitGame();

        gm.StartGame();
    }

    /**
     * Játékmenetek befejezése.
     */
    public void EndGame() {
        // TODO implement here
    }

}