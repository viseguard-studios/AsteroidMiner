package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Entity;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.UFO;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;
import com.viseguardstudios.asteroid_miner.model.item.resource.*;
import com.viseguardstudios.asteroid_miner.util.*;

import java.util.*;

/**
 * A játék menetének irányításáért felelős osztály.
 */
public class GameManager implements INotifyPropertyChanged {

    public static final int MAP_SIZE= 1000;
    public static final int CLOSE_SUN_DISTANCE= 250;

    boolean debug = false;


    Random rnd;
    boolean deterministic;

    /**
     * Jelzi, hogy a játék befejeződött-e már.
     */
    private boolean gameEnded;

    private int turnCount =0;

    /**
     * Az aktuálisan irányítható jármű (akit a soron lévő játékos jelenleg irányít).
     */
    private Entity selectedEntity;


    /**
     * A játék játékosainak listája.
     */
    private List<Player> allPlayers = new ArrayList<>();

    /**
     * The player who is taking the turn currently
     */
    private Player currentPlayer;
    private int playerID;


    /**
     * A játékban szereplő aszteroidák listája.
     */
    private List<Asteroid> asteroids = new ArrayList<>();



    /**
     * Kör végén lesz-e napkitörés
     */
    private boolean CreateStormOn = false;

    public Vector2 getSolarStormCenter() {
        return solarStormCenter;
    }

    /**
     * Napkitörés középpontja
     */
    private Vector2 solarStormCenter;

    public int getSolarStromRadius() {
        return solarStromRadius;
    }

    /**
     * Napkitörés sugara
     */
    private int solarStromRadius;


    /**
     * A Naptól való aktuális távolság.
     */
    private int sunDistance = 300;




    /**
     * A játék telepeseinek tárolója.
     */
    private List<SpaceShip> settlers = new LinkedList<>();

    /**
     * Az adott játékhoz tartozó játéktér.
     */
    private Scene scene;


    /**
     * Default constructor
     */
    public GameManager() {
    }


    public GameManager(int sunDist, boolean gameEnded, boolean stormQueued) {
        this.sunDistance = sunDist;
        this.gameEnded = gameEnded;
        this.CreateStormOn = stormQueued;
    }

    /**
     * Visszaadja az első ilyen nevű playert. NE LEGYEN TÖBB EGYFORMA NEVŰ!
     * @param name
     * @return
     */
    public Player getPlayerByName(String name){

        for (Player p : allPlayers) {
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    /**
     * A játékmenet inicializálásáért felel.
     * @param seed a játék random generátorjának kezdő értéke
     */
    public void initGame(int seed) {
        if(seed == 0){
            System.out.println("Deterministic game");
            rnd = new Random(0);
            deterministic = true;
        }
        else {
            rnd = new Random(seed);
            deterministic = false;
            generateScene();
        }

        //Generate stuff
    }

    public void setManagedScene(Scene sc) {
        scene = sc;
    }

    public Scene getManagedScene() {
        return scene;
    }

    /**

     * Új játékos hozzáadása.
     * @param p
     */
    public void AddPlayer(Player p) {
        if(!allPlayers.contains(p)){
            allPlayers.add(p);
        }
    }

    /**
     * Aszteroida hozzáadása
     * @param asteroid
     */
    public void AddAsteroid(Asteroid asteroid){
        asteroids.add(asteroid);
    }

    /**
     * Új játék indításáért felel.
     */
    public void startGame() {
        currentPlayer = allPlayers.get(0);
        playerID = 0;

        notifyListeners();
    }

    double dir = 0;

    /**
     * Amikor az összes játékos lépett.
     */
    public void takeTurn() {
        if(this.CreateStormOn){
            scene.solarFlare(solarStormCenter, solarStromRadius);
            CreateStormOn = false;
        }
        if(rnd.nextInt(100) < 30){
            CreateStormOn = true;
            solarStormCenter = new Vector2(rnd.nextInt(MAP_SIZE),rnd.nextInt(MAP_SIZE));
            solarStromRadius = rnd.nextInt(200)+80;
        }

        dir = Math.cos(turnCount/4f);
        sunDistance += rnd.nextInt(30)*dir;


        scene.roundEnded();
        turnCount++;
        notifyListeners();
    }

    /**
     * Amikor egy játékos befejezi a körét
     */
    public void nextPlayer(){
        playerID++;
        if(playerID >= allPlayers.size()) {
            takeTurn();
            playerID = 0;
        }

        currentPlayer = allPlayers.get(playerID);

        notifyListeners();
    }

    /**
     * Aktuális játék befejezése.
     * @param b
     */
    public void endGame(boolean b) {
        System.out.println("Current game is over");
        if(b)
            Engine.getInstance().setGameState(Engine.State.Won);
        else
            Engine.getInstance().setGameState(Engine.State.Lost);
    }


    /**
     * Játék inicializálás során a játéktér elemeinek inicializálása.
     */
    private void generateScene() {
        GenerateAsteroids();

        for (var pl : allPlayers) {

            for (int i = 0; i < 3; i++) {

                var ai = rnd.nextInt(asteroids.size());

                var ss = new SpaceShip(asteroids.get(ai));
                ss.setName("SS_"+ Namer.getNextID(ss.getClass()));
                if(debug) {
                    ss.getInventory().insertItem(new Iron());
                    ss.getInventory().insertItem(new Iron());
                    ss.getInventory().insertItem(new Iron());
                    ss.getInventory().insertItem(new Coal());
                    ss.getInventory().insertItem(new Ice());
                    ss.getInventory().insertItem(new Uranium());
                    ss.getInventory().insertItem(new Titan());
                }
                pl.addVessel(ss);
                ss.setOwner(pl);
                settlers.add(ss);
                scene.addEntity(ss);
            }
        }

        for (int i = 0; i < 5; i++) {

            var ai = rnd.nextInt(asteroids.size());

            var ss = new UFO(asteroids.get(ai));
            ss.setName("Klingon");
            scene.addEntity(ss);
        }
    }

    /**
     * Új nyersanyag generálása, inicializálás folyamata során használjuk fel.
     * @return
     */
    private Resource GenerateNewResource() {

        RandomCollection<Resource> resources = new RandomCollection<>(rnd);

        resources.add(1, new Coal());
        resources.add(2, new Iron());
        resources.add(2, new Ice());

        return resources.next();

        //return null;
    }

    /**
     * Az aszteroidamező inicializálása, játék inicializálás során hozzuk létre.
     */
    private void GenerateAsteroids() {
        for (int i = 0; i < 500; i++) {

            var res = GenerateNewResource();
            var count = rnd.nextInt(5)+1;

            //Create pos
            var pos = new Vector2(rnd.nextInt(MAP_SIZE), rnd.nextInt(MAP_SIZE));

            var a = new Asteroid(res,count);
            a.setPos(pos);
            //a.setResource(res);
            a.setCrustSize(rnd.nextInt(3)+1);

            a.setName("A_"+i);
            scene.addEntity(a);
            asteroids.add(a);
        }

        for (int i = 0; i < 50; i++) {
            for (var ast : asteroids) {
                var pos = ast.getPos();
                var delta = new Vector2(0,0);

                for (var o : asteroids) {
                    var other = o.getPos();

                    var d = pos.distance(other);

                    if(ast.getPhysicalNeighbours().contains(o) && d < 70){
                        var norm = other.subtract(pos).divide(d);

                        delta = delta.add(norm.multiply(1/2f));
                    }
                    else if(d < 50){
                        var norm = pos.subtract(other).divide(d);

                        delta = Vector2.add(delta,norm.multiply(50-d));
                    }
                }
                pos = pos.add(delta);

                ast.setPos(pos);
            }
        }


        for (var ast : asteroids) {
            var pos = ast.getPos();

            while (ast.getPhysicalNeighbours().size() < 3){
                Asteroid closest = null;
                double dist_closest = 100000000;

                for (var p : asteroids) {
                    var dist = ast.getPos().distance(p.getPos());
                    if(dist < dist_closest && ast != p && !ast.getPhysicalNeighbours().contains(p)){
                        dist_closest = dist;
                        closest =  p;
                    }
                }

                ast.addNeighbour(closest);
                closest.addNeighbour(ast);
            }



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

    /**A mező naptól való távolságának állítása
     * @param d
     */
    public void ChangeAFDistance(int d) {
        sunDistance = d;
    }

    /**
     * @return
     */
    public int GetAFDistance() {
        return sunDistance;
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
        settlers.add(s);
        scene.addEntity(s);
    }

    public void removeSettler(SpaceShip spaceShip) {
        settlers.remove(spaceShip);
        scene.removeEntity(spaceShip);

        if(settlers.size() <= 0){
            endGame(false);
        }
    }

    public List<SpaceShip> getSettlers(){
        return settlers;
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public List<Asteroid> getAsteroids() { return asteroids; }

    public Player getCurrentPlayer() { return currentPlayer; }

    public Entity getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(Entity selectedEntity) {
        this.selectedEntity = selectedEntity;
        notifyListeners();
    }

    public Random getRnd() {
        return rnd;
    }

    /**
     * Távolság lekérdezése
     * @return
     */
    public int getSunDistance() {return  sunDistance;}

    public boolean isColseToSun() {return  sunDistance < CLOSE_SUN_DISTANCE;}

    /**
     * Napkitörés középpontjának állítása
     * @param center
     */
    public void setSolarStormCenter(Vector2 center){
        solarStormCenter = center;
    }

    /**
     * Napkitörés sugarának állítása
     * @param radius
     */
    public void setSolarStromRadius(int radius){
        solarStromRadius = radius;
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