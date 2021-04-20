package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.commands.CommandExecutor;

import java.util.*;

/**
 * A program indításáért, egy játékfolyam bezárásáért felelős osztály.
 */
public class Engine {



    static Engine instance;

    Scene scene;

    boolean running;

    private int playerCount;

    CommandExecutor cmdexec;

    /**
     * Default constructor
     * @param args
     */
    public Engine(String[] args) {
        instance = this;
        System.out.println("Created Engine");
        cmdexec = new CommandExecutor();

    }


    /**
     * A program indítása.
     */
    public void StartApplication() {
        System.out.println("Application started");

        Scanner in = new Scanner(System.in);

        running = true;

        while (running){
            var line = in.nextLine();
            cmdexec.execute(line);
        }
    }

    /**
     * Egy új játék kezdése.
     */
    public void StartGame(int seed) {
        System.out.println("Starting game");
        var gm = new GameManager();

        scene = new Scene();
        scene.setManager(gm);

        gm.setManagedScene(scene);

        for (int i = 0; i < playerCount; i++) {
            var p = new Player();

            gm.addPlayer(p);
        }
        //gm.AddPlayer();

        gm.initGame(seed);

        gm.startGame();
    }

    /**
     * Játékmenetek befejezése.
     */
    public void EndGame() {
        // TODO implement here
    }


    public CommandExecutor getCmdexec() {
        return cmdexec;
    }

    public static Engine getInstance() {
        return instance;
    }

    public void setPlayerCount(int player_count) {
        this.playerCount = player_count;
    }

    public Scene getScene() {
        return scene;
    }

    public GameManager getGameManager(){
        return scene.GetManager();
    }
}