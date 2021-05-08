package com.viseguardstudios.asteroid_miner.model.entities.Vessel;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.inventory.SSInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.recipe.Recipe;
import com.viseguardstudios.asteroid_miner.util.Sprite;

import java.util.List;

/**
 * A telepesekért felelős osztály.
 */
public class SpaceShip extends Vessel {

    private static final Sprite s = new Sprite("assets/graphics/sprites/ship.png", 10);

    static List<String> actions = List.of("move","hide","drill","mine","activate","create","stash");


    /**
     * Default constructor
     */
    public SpaceShip(Asteroid a) {
        super(a);
        inventory = new SSInventory();
    }

    public SpaceShip(Asteroid a, Player owner, String name) {
        super(a);
        inventory = new SSInventory();
        this.owner = owner;
        owner.addVessel(this);
        this.name = name;
    }

    /**
     * Elem belehelyezése az aktuális aszteroidába, amin tartózkodik. Sikeres behelyezés után igazzal tér vissza, a saját raktárból eltávolítja az elemet. Sikertelen művelet után hamissal tér vissza.
     * @param i
     * @return
     */
    @Override
    public boolean placeItem(Item i) {

        if(currentAsteroid.placeItem(i)){
            inventory.removeItem(i);
            return true;
        }
        return false;
    }

    /**
     * A telepes által folyamatosan hordozott raktár.
     */
    private SSInventory inventory;

    /**
     * A telepesek által az aszteroida magjában elfoglalt hely nagyságát adja vissza.
     */
    public int GetHidingSpaceRequirement() {
        return 1;
    }

    /**
     * A telepes az aktuálisan rendelkezésére álló elemekből egy "receptet" készít.  A felhasznált elemek elhasználódnak, törlődnek a raktárból.
     */
    public boolean Craft(Recipe recipe) {
        if(!turnUsed){
            boolean cancraft = recipe.canCraft(this);
            if (cancraft){
                recipe.craft(this);
                turnUsed=true;
                return true;
            }
        }
        return false;
    }

    /**
     * A megadott tárgyon hív egy Activate()-et
     * @param i
     */
    public void ActivateItem(Item i) {
        i.activate(inventory, this);
    }

    @Override
    public void explode() {
        super.explode();
        inventory.explode();
        scene.getManager().removeSettler(this);
    }

    @Override
    public List<String> getActions() {
        return actions;
    }

    @Override
    public void doAction(String name) {
        //TODO :Implement
    }

    /**
     * A raktár getter-e
     * @return inventory
     */
    public SSInventory getInventory() {
        return inventory;
    }

    /**
     * A tulajdonos getter-e
     * @return owner
     */
    public Player getOwner() {return owner; }



    @Override
    public AsteroidPlaces getPlace() {
        return AsteroidPlaces.Vessel;
    }

    @Override
    public String printStatus() {
        String status = super.printStatus() + "\n";
        status = status.concat("Turn used: " + (turnUsed ? "true":"false")+ "\n");

        status = status.concat(currentAsteroid.getName()+"\n");

        status = status.concat("Resources:\n");
        for (var item : inventory.getItems()) {
          status =  status.concat("- "+ item.getName()+"\n");
        }

        status = status.concat("Teleport Gates:\n");
        for (var item : inventory.getGates()) {
           status = status.concat("- "+ item.getName()+"\n");
        }

        return status;
    }

    @Override
    public Sprite getSprite() {
        return s;
    }
}