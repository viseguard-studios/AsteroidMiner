package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.model.resource.Resource;

import java.util.*;

/**
 * A játék menetének irányításáért felelős osztály.
 */
public class GameManager {

    /**
     * Default constructor
     */
    public GameManager() {
    }

    /**
     * The player who is taking the turn currently
     */
    private void currentPlayer;

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
    private bool gameEnded;

    /**
     * 
     */
    private bool CreateStormOn;

    /**
     * A játék telepeseinek tárolója.
     */
    private SpaceShip settlers;

    /**
     * A jelenleg aktív játékos, aki éppen tudja mozgatni a telepesit.
     */
    private Player currentPlayer;

    /**
     * Az adott játékhoz tartozó játéktér.
     */
    private Scene scene;

    /**
     * A játékmenet inicializálásáért felel.
     */
    public void InitGame() {
        // TODO implement here
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
        // TODO implement here
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
        // TODO implement here
    }

    /**
     * Visszaadja, hogy jelenleg napviharban van-e az aszteroidamező.
     * @return
     */
    public bool IsSolarStormActive() {
        // TODO implement here
        return null;
    }

    /**
     * @param int
     */
    public void ChangeAFDistance(void int) {
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
     * 
     */
    public void QueueSolarStorm() {
        // TODO implement here
    }

}