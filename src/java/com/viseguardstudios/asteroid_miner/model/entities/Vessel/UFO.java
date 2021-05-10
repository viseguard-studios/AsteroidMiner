package com.viseguardstudios.asteroid_miner.model.entities.Vessel;

import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.inventory.IInventory;
import com.viseguardstudios.asteroid_miner.model.inventory.InfiniteInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.recipe.Recipe;
import com.viseguardstudios.asteroid_miner.util.Sprite;

import java.util.List;

public class UFO extends Vessel{

    public static final Sprite sprite = new Sprite("assets/graphics/sprites/ufo.png", 10);
    InfiniteInventory inventory;
    //static List<String> actions = List.of("move","mine");
    /**
     * Default constructor
     *
    **/
    public UFO(Asteroid a) {
        super(a);
        inventory = new InfiniteInventory();
    }

    @Override
    public boolean placeItem(Item i) {
        return false;
    }

    /**
     * Konstruktor névvel.
     *
     **/
    public UFO(Asteroid a, Player owner, String name) {
        super(a);
        this.name = name;
        owner.addVessel(this);
        this.owner = owner;
        inventory = new InfiniteInventory();
    }

    @Override
    public AsteroidPlaces getPlace() {
        return AsteroidPlaces.Vessel;
    }

    @Override
    public int GetHidingSpaceRequirement() {
        return 2;
    }


    @Override
    public void roundEnd(boolean closeToSun) {
        super.roundEnd(closeToSun);

        mine();

        if(!this.turnUsed){
            var asteroids = currentAsteroid.getReachableAsteroids();
            int rnd = scene.getManager().getRnd().nextInt(asteroids.size());

            move(asteroids.get(rnd));
        }
    }

    //Nem képes bányászni
    @Override
    public boolean drill() {
        //Do nothing
        return false;
    }

    @Override
    public void setOwner(Player p) {
        //Do nothing
    }

    @Override
    public void SolarFlare() {
        //DO not die
    }

    @Override
    public List<String> getActions() {
        return null;
    }

    @Override
    public void doAction(String[] args) {
        //No user actions
    }

    /**
     * Can not craft
     * @param recipe
     * @return
     */
    @Override
    public boolean Craft(Recipe recipe) {
        return false;
    }

    @Override
    public boolean Hide() {
        //DO not hide
        return false;
    }

    @Override
    public Sprite getSprite(){
        return sprite;
    }

    @Override
    public IInventory getInventory() {
        return inventory;
    }
}
