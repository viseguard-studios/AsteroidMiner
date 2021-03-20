package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.model.building.TeleportGate;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.recipe.Recipe;

/**
 * A telepesekért felelős osztály.
 */
public class SpaceShip extends Vessel {

    /**
     * Default constructor
     */
    public SpaceShip(Asteroid a) {
        super(a);

    }

    /**
     * Az utoljára lehelyezett kapu referenciája. Arra használjuk hogy a telepes által lerakott kapu párokat össze lehessen kötni.
     */
    private TeleportGate lastPlacedGate;

    /**
     * A telepes által folyamatosan hordozott raktár.
     */
    private Inventory inventory;


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
        return 0;
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
        // TODO implement here
    }

    /**
     * Elem belehelyezése az aktuális aszteroidába, amin tartózkodik. Sikeres behelyezés után igazzal tér vissza, a saját raktárból eltávolítja az elemet. Sikertelen művelet után hamissal tér vissza.
     * @param i 
     * @return
     */
    public boolean PlaceItem(Item i) {
        // TODO implement here
        return false;
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

}