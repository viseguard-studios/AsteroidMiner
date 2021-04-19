package com.viseguardstudios.asteroid_miner.model.entities.Vessel;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.entities.building.TeleportGate;
import com.viseguardstudios.asteroid_miner.model.inventory.SSInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.recipe.Recipe;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

/**
 * A telepesekért felelős osztály.
 */
public class SpaceShip extends Vessel {

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
        this.name = name;
    }

    /**
     * A telepes által folyamatosan hordozott raktár.
     */
    private SSInventory inventory;


    /**
     * A telepesek által az aszteroida magjában elfoglalt hely nagyságát adja vissza.
     * @return
     */
    public int GetHidingSpaceRequirement() {
        return 1;
    }

    /**
     * A telepes az aktuálisan rendelkezésére álló elemekből egy "receptet" készít.  A felhasznált elemek elhasználódnak, törlődnek a raktárból.
     * @param recipe
     */
    public void Craft(Recipe recipe) {
        boolean cancraft = recipe.canCraft(this);
        if (cancraft)
            recipe.craft(this);
    }

    /**
     * Elem belehelyezése az aktuális aszteroidába, amin tartózkodik. Sikeres behelyezés után igazzal tér vissza, a saját raktárból eltávolítja az elemet. Sikertelen művelet után hamissal tér vissza.
     * @param i 
     * @return
     */
    public boolean PlaceItem(Item i) {
        var success = currentAsteroid.PlaceItem(i);
        if(success){
            inventory.removeItem(i);
            return true;
        }
        return false;
    }

    /**
     * A megadott tárgyon hív egy Activate()-et
     * @param i
     */
    public void ActivateItem(Item i) {
        i.activate(inventory);
    }

    @Override
    public void explode() {
        super.explode();
        inventory.explode();
        scene.GetManager().removeSettler(this);
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
}