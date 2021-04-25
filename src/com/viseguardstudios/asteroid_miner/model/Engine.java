package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.commands.CommandExecutor;
import com.viseguardstudios.asteroid_miner.util.INotifyPropertyChanged;
import com.viseguardstudios.asteroid_miner.util.StateChangedListener;
import com.viseguardstudios.asteroid_miner.view.MainWindow;

import java.util.*;

/**
 * A program indításáért, egy játékfolyam bezárásáért felelős osztály.
 */
public class Engine implements INotifyPropertyChanged {

    static Engine instance;
    public static Scanner in = new Scanner(System.in);

    public enum State{
        MainMenu,
        InGame,
        Won,
        Lost
    }
    State gameState = State.MainMenu;

    Scene scene;

    boolean running;

    private int playerCount;

    CommandExecutor cmdexec;

    /**
     * Default constructor
     *
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

        MainWindow m = new MainWindow();

        running = true;

        while (running) {
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

        setScene(new Scene());
        scene.setManager(gm);

        gm.setManagedScene(scene);

        for (int i = 0; i < playerCount; i++) {
            var p = new Player();

            gm.addPlayer(p);
        }
        //gm.AddPlayer();

        gm.initGame(seed);

        setGameState(State.InGame);
        gm.startGame();

    }

    /**
     * Új játék fájlból
     **/
    public void StartGame(int seed, Scene scene) {
        System.out.println("Starting game");
        this.scene = scene;
        scene.getManager().initGame(seed);
        scene.getManager().startGame();
    }

    /**
     * Játékmenetek befejezése.
     */
    public void EndGame() {
        System.out.println("Game ended");
    }


    public CommandExecutor getCmdExec() {
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

    public GameManager getGameManager() {
        return scene.getManager();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }


    public State getGameState() {
        return gameState;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
        notifyListeners();
    }


    List<StateChangedListener> listeners = new ArrayList<>();

    @Override
    public void addListener(StateChangedListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListener(StateChangedListener l) {
        listeners.remove(l);
    }

    public void notifyListeners(){
        listeners.forEach(l -> l.stateChanged());
    }
}