package com.viseguardstudios.asteroid_miner.model.entities.Vessel;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.inventory.IInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.recipe.Recipe;
import com.viseguardstudios.asteroid_miner.util.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Egy speciális jármű, a robot tevékenységeit, tulajdonságait tartalmazza.
 */
public class Robot extends Vessel {

    public static final Sprite sprite = new Sprite("assets\\graphics\\sprites\\robot.png", 10);

    static List<String> actions = List.of("move","hide","drill");

    /**
     * Default constructor
     */
    public Robot(Asteroid a) {
        super(a);
    }

    @Override
    public boolean placeItem(Item i) {
        return false;
    }

    /**
     * Constructor for crafting
     * @param p
     * @param currentAsteroid
     */
    public Robot(Player p, Asteroid currentAsteroid, String name) {
        super(currentAsteroid);
        this.name = name;
        owner = p;
        p.addVessel(this);
    }


    /**
     * A robotok által az aszteroida magjában elfoglalt hely nagyságát adja vissza. Korlátlan mennyiségű robot elfér, tehát 0 az értéke.
     * @return
     */
    public int GetHidingSpaceRequirement() {
        return 0;
    }

    /**
     * Cannot craft
     * @param recipe
     * @return
     */
    @Override
    public boolean Craft(Recipe recipe) {
        return false;
    }

    /**
     * Robotnak nincs raktára
     * @return
     */
    @Override
    public IInventory getInventory() {
        return null;
    }

    /**
     * Üres metódus, robot nem tud bányászni
     */
    @Override
    public Item mine(){
        return null;
    }

    @Override
    public void AsteroidExploded(){
        //Todo miért üres???
    }


    @Override
    public AsteroidPlaces getPlace() {
        return AsteroidPlaces.Vessel;
    }

    @Override
    public List<String> getActions() {

        return actions;
    }

    @Override
    public void doAction(String[] args) {
        if(owner.equals(Engine.getInstance().getGameManager().getCurrentPlayer()))
            super.doAction(args);
        if(args[0].equals("drill")){
            this.drill();
            scene.getManager().notifyListeners();
        }
    }



    @Override
    public Sprite getSprite() {
        return sprite;
    }
}