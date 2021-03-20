package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.model.building.Building;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.resource.Resource;

import java.util.*;

/**
 * Egy aszteroidát jelöl. Minden példányának létrejöttekor beállítódnak a generátor által a tulajdonságai.
 */
public class Asteroid extends Entity {

    /**
     * Default constructor
     */
    public Asteroid() {
    }

    @Override
    public void RoundEnd(boolean closeToSun) {

    }

    /**
     * Az aszteroida magjának mérete, ennyi egységnyi nyersanyag bányászható ki belőle a játék elején, és bányászat után ennyi egységnyi item helyezhető vissza bele.
     */
    private int coreSize;

    /**
     * Igaz, ha felrobbant már az aszteroida, hamis, ha nem.
     */
    private boolean exploded;

    /**
     * Maximum ennyi telepes számára van az adott időpillanatban hely az aszteroidában elbújni. Minden telepes sikeres elbújása után csökken ez a szám.
     */
    private int maxHidingSpace;

    /**
     * Az aszteroida köpenyének mélysége. Ennyi egységet kell még fúrni benne jelenleg, hogy teljesen átfúrt legyen. Minden fúrás után csökken ez a szám.
     */
    private int crustSize;

    /**
     * Igaz, ha az aszteroida már „fel van fedve” a térképen, tehát rajta vagy bármelyik szomszédján már járt telepes vagy robot. Ellenkező esetben hamis.
     */
    private boolean revealed;

    /**
     * Igaz, ha az aszteroidán már járt telepes vagy robot, ellenkező esetben hamis.
     */
    private boolean visited;

    /**
     * Ha az aszteroidában jelenleg megbújik egy telepes, akkor eltárolja, melyik telepesről van szó. Ha nem bújik benne senki, null értéket tárol.
     */
    private Vessel hidingVessel;

    /**
     * Az aszteroida raktára, ami a magba belehelyezett és jelenleg ott tárolt elemeket tartalmazza.
     */
    private Inventory inventory;

    /**
     * A szomszédos aszteroidák tárolója.
     */
    private Set<Asteroid> neighbours;


    /**
     * Az aszteroida magjában lévő nyersanyagkészlet.
     */
    private Resource resource;

    /**
     * Az aszteroidán jelenleg állomásozó járművek tárolója.
     */
    private Set<Vessel> stationed;

    /**
     * Az aszteroidára épített (véglegesen elhelyezett) építmények tárolója.
     */
    private Set<Building> buildings;

    /**
     * Az osztály konstruktora.
     * @param r 
     * @return
     */
    public Asteroid Asteroid(Resource r) {
        // TODO implement here
        return null;
    }

    /**
     * Felrobban az aszteroida. Felrobbantja az összes rajta tartózkodó járművet, hozzáférhetetlenné teszi a raktárat és a rajta lévő épületeket, értesíti a szomszédos aszteroidákat a robbanásról.
     */
    public void Explode() {
        // TODO implement here
    }

    /**
     * Tárolja, hogy melyik aszteroidák elérhetőek jelenleg az adott aszteroidából.
     * @return
     */
    public Set<Asteroid> ReachableAsteroids() {
        // TODO implement here
        return null;
    }

    /**
     * Egy adott jármű elhagyja az aszteroidát, törlődik az ott tartózkodók közül.
     * @param v
     */
    public void Depart(Vessel v) {
        // TODO implement here
    }

    /**
     * Egy adott jármű érkezik az aszteroidára, regisztrálja az ott tartózkodók közé.
     * @param v
     */
    public void Arrive(Vessel v) {
        // TODO implement here
    }

    /**
     * Visszaadja, hogy hány telepes számára van még hely elbújásra jelenleg az aszteroidában, attól függően, hogy jelentleg elbújt-e telepes az aszteroidában.
     * @return
     */
    public int GetAvailableHidingSpace() {
        // TODO implement here
        return 0;
    }

    /**
     * Aszteroida felfedése a térképen, amennyiben még nem volt felfedve.
     */
    public void Reveal() {
        // TODO implement here
    }

    /**
     * Akkor hívódik meg amikor az aszteroida kérgén lévő lyukat akarják mélyíteni.
     */
    public void Drill() {
        // TODO implement here
    }

    /**
     * Akkor hívódik meg amikor az aszteroidából akarnak nyersanyagot kibányászni.
     * @return
     */
    public Item Mine() {
        // TODO implement here
        return null;
    }

    /**
     * Az aszteroidában megpróbál elbújni egy jármű. Igazat ad vissza ha el tud bújni, hamisat ha nem.
     * @param v 
     * @return
     */
    public boolean Hide(Vessel v) {
        // TODO implement here
        return false;
    }

    /**
     * Az adott jármű előbújik az aszteroidából.
     * @param v
     */
    public void Exit(Vessel v) {
        // TODO implement here
    }

    /**
     * Hozzáad egy új épületet az aszteroidához.
     * @param b
     */
    public void AddBuilding(Building b) {
        // TODO implement here
    }

    /**
     * Elem belehelyezése a raktárba. Sikeres behelyezés után igazzal tér vissza, sikertelen művelet után hamissal tér vissza.
     * @param i 
     * @return
     */
    public boolean PlaceItem(Item i) {
        // TODO implement here
        return false;
    }

    /**
     * Új szomszéd hozzáadása.
     * @param a
     */
    public void AddNeigbour(Asteroid a) {
        // TODO implement here
    }

    /**
     * Visszaadja az aszteroida raktárát.
     * @return
     */
    public Inventory GetInventory() {
        // TODO implement here
        return null;
    }

    /**
     * Napvihar esetén hívódik meg minden entitáson, az aszteroidákon nem történik művelet ilyenkor.
     */
    @Override
    public void SolarFlare() {
        // TODO implement here
    }


    public int getCrustSize() {
        return crustSize;
    }

    public void setCrustSize(int crustSize) {
        try {
            if(crustSize >= 0) this.crustSize = crustSize;
            else throw new Exception("crustSize must not smaller than 0!");
        }
        catch (Exception exp){ }

    }

    public int getMaxHidingSpace() {
        return maxHidingSpace;
    }

    public void setMaxHidingSpace(int maxHidingSpace) {
        this.maxHidingSpace = maxHidingSpace;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public Vessel getHidingVessel() {
        return hidingVessel;
    }

    public void setHidingVessel(Vessel hidingVessel) {
        this.hidingVessel = hidingVessel;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}