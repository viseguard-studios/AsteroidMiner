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

    /**
     * Az utoljára lehelyezett kapu referenciája. Arra használjuk hogy a telepes által lerakott kapu párokat össze lehessen kötni.
     */
    private TeleportGate lastPlacedGate;

    /**
     * A telepes által folyamatosan hordozott raktár.
     */
    private SSInventory inventory;


    /**
     * Az osztály konstruktora, beállítja az őt kezelő játékost illetve aszteroidát.
     * @param p 
     * @param a 
     * @return
     */
    public SpaceShip SpaceShip(Player p, Asteroid a) {
        // TODO implement here
        return null;
    }

    /**
     * A telepesek által az aszteroida magjában elfoglalt hely nagyságát adja vissza.
     * @return
     */
    public int GetHidingSpaceRequirement() {
        // TODO implement here
        return 1;
    }

    /**
     * Teljesen átfúrt, nem üres magú aszteroidán való tartózkodás esetén a telepes egy egységet kibányászik az ott található nyersanyagból. Az így keletkező elem a telepes raktárába kerül.  Ha sikertelen a művelet (nem teljesülnek a feltételek), nem történik művelet.
     */
    public void Mine() {
        // TODO implement here
    }

    /**
     * A telepes az aktuálisan rendelkezésére álló elemekből egy "receptet" készít.  A felhasznált elemek elhasználódnak, törlődnek a raktárból.
     * @param recipe
     */
    public void Craft(Recipe recipe) {

        Logger.functionCalled("cancraft = recipe.CanCraft(ss)");
        boolean cancraft = recipe.canCraft(this);
        Logger.returned();

        if (cancraft) {
            Logger.functionCalled("recipe.Craft(ss)");
            recipe.craft(this);
            Logger.returned();
        }
    }

    /**
     * Elem belehelyezése az aktuális aszteroidába, amin tartózkodik. Sikeres behelyezés után igazzal tér vissza, a saját raktárból eltávolítja az elemet. Sikertelen művelet után hamissal tér vissza.
     * @param i 
     * @return
     */
    public boolean PlaceItem(Item i) {
        // TODO implement here

        Logger.functionCalled("currentAsteroid.PlaceItem(i)");
        var success = currentAsteroid.PlaceItem(i);
        Logger.returned();
        Logger.lognl("Does the inserting succeed?");
        if(success){
            Logger.lognl("Yes");
            Logger.functionCalled("RemoveItem(i)");
            inventory.removeItem(i);
            Logger.returned();
            return true;
        }
        else {
            Logger.lognl("No");
            return false;
        }
    }

    /**
     * A megadott tárgyon hív egy Activate()-et
     * @param i
     */
    public void ActivateItem(Item i) {
        // TODO implement here
    }

    /**
     * A jelenlegi aszeroida ezen keresztül szól a telep/robot-nak, hogy felrobbant.
     */
    public void AsteroidExploded(){

    }

    @Override
    public void RoundEnd(boolean closeToSun) {

    }

    @Override
    public void SolarFlare() {

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