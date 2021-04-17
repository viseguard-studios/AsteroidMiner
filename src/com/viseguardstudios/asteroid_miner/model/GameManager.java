package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;
import com.viseguardstudios.asteroid_miner.model.item.resource.Resource;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

import java.util.*;

/**
 * A játék menetének irányításáért felelős osztály.
 */
public class GameManager {

    Random rnd;
    boolean deterministic;

    /**
     * The player who is taking the turn currently
     */
    private Player currentPlayer;

    /**
     * Az aktuálisan irányítható jármű (akit a soron lévő játékos jelenleg irányít).
     */
    private Vessel selectedVessel;

    /**
     * A Naptól való aktuális távolság.
     */
    private int sunDistance;

    /**
     * A játék játékosainak listája.
     */
    private Set<Player> allPlayers;

    /**
     * A játékban szereplő aszteroidák listája.
     */
    private Set<Asteroid> asteroids;

    /**
     * Jelzi, hogy a játék befejeződött-e már.
     */
    private boolean gameEnded;

    /**
     * 
     */
    private boolean CreateStormOn;

    /**
     * A játék telepeseinek tárolója.
     */
    private SpaceShip settlers;

    /**
     * Az adott játékhoz tartozó játéktér.
     */
    private Scene scene;

    /**
     * Default constructor
     */
    public GameManager() {
    }

    /**
     * A játékmenet inicializálásáért felel.
     * @param seed
     */
    public void InitGame(int seed) {
        // TODO implement here

        if(seed == 0){
            System.out.println("Deterministic game");
            rnd = new Random(0);
            deterministic = true;
        }
        else {
            rnd = new Random(seed);
            deterministic = false;
        }

        //Generate stuff
        GenerateScene();

    }

    public void setManagedScene(Scene sc) {
        scene = sc;
    }

    /**
     * Új játékos hozzáadása.
     * @param p
     */
    public void AddPlayer(Player p) {
        // TODO implement here
    }

    /**
     * Új játék indításáért felel.
     */
    public void StartGame() {
        // TODO implement here
    }

    /**
     * Egy játékos aktuális köre - ekkor van lehetősége irányítani a járműveit egyesével.
     */
    public void TakeTurn() {
        Logger.log("scene.SolarFlare()");
        scene.SolarFlare();
        Logger.returned();
    }

    /**
     * Aktuális játék befejezése.
     */
    public void EndGame() {
        // TODO implement here
    }

    /**
     * Játék inicializálás során a játéktér elemeinek inicializálása.
     */
    private void GenerateScene() {
        // TODO implement here
        GenerateAsteroids();
    }

    /**
     * Új nyersanyag generálása, inicializálás folyamata során használjuk fel.
     * @return
     */
    private Resource GenerateNewResource() {
        // TODO implement here
        return null;
    }

    /**
     * Az aszteroidamező inicializálása, játék inicializálás során hozzuk létre.
     */
    private void GenerateAsteroids() {
        for (int i = 0; i < 100; i++) {
            //Create pos

            var a = new Asteroid();

            scene.AddEntity(a);
        }

        //Connect the asteroids that are close together.
    }

    /**
     * Visszaadja, hogy jelenleg napviharban van-e az aszteroidamező.
     * @return
     */
    public boolean IsSolarStormActive() {
        return CreateStormOn;
    }

    /**
     * @param d
     */
    public void ChangeAFDistance(int d) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int GetAFDistance() {
        // TODO implement here
        return 0;
    }

    /**
     * Beállítja, hogy a kör végén legyen napkitörés.
     */
    public void QueueSolarStorm() {
        CreateStormOn = true;
    }

    /**
     * Játékos hozzáadása a listához.
     * @param p
     */
    public void addPlayer(Player p) {
        allPlayers.add(p);
    }

    public void addSettler(SpaceShip s) {
        //todo
    }
}