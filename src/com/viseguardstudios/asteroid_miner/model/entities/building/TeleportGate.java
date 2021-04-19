package com.viseguardstudios.asteroid_miner.model.entities.building;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import jdk.jfr.Label;

import java.util.*;

/**
 * A teleportkapukat reprezentáló osztály.
 */
public class TeleportGate extends Building {

    private Map<TeleportGate,Integer> idList;

    private int pairID;

    /**
     * Default constructor
     *
     */
    public TeleportGate(int pairID) {
        this.pairID = pairID;
    }

    /**
     * Teleportkapu létrehozása.
     * @param home hova kerüljön
     * @param pairID pár id-je
     */
    public TeleportGate(Asteroid home,String name, int pairID) {
        this.name = name;
        this.pairID = pairID;
        this.currentAsteroid = home;
        home.AddBuilding(this);
    }

    @Override
    public void RoundEnd(boolean closeToSun) {

    }

    @Override
    public void SolarFlare() {

    }

    /**
     * Az aszteroida párja.
     */
    private TeleportGate pair;

    public void SetPair(TeleportGate tg){
        //TODO: Check if it was set already
        pair = tg;
    }

    /**
     * Visszaadja az ebből a kapuból elérhető extra aszteroidákat.
     * @return
     */
    public Set<Asteroid> GetRoutes() {
        // TODO implement here
        return null;
    }

    /**
     * Akkor hívódik meg ha a párja megsemmisül és ezáltal ez az oldal deaktiválódik.
     */
    public void PairDestroyed() {
        // TODO implement here
    }

    /**
     * Akkor hívódik meg ha az épület felrobban. Ekkor szól a szomszédjának is a PairDestroyed() fügvénnyel.
     * @param a
     */
    public void Explode(Asteroid a) {
        // TODO implement here
    }

    @Override
    public AsteroidPlaces getPlace() {
        //TODO If it started to wander it should return vessel to ensure it has space
        return AsteroidPlaces.Orbit;
    }

    /**
     * Visszaadja, hogy a párjának milyen ID-je lenne
     * @return
     */
    public int getPairID(){return pairID;}

    public TeleportGate getIdPair(int id){
        return null;
    }

    @Label("MI EZ?? VAN MÁSIK METÓDUS IS!!!")
    @Deprecated
    public void setPair(TeleportGate pair){

        //TODO MIÉRT VAN KÉT PÁR???
    }

    public void addIdListItem(TeleportGate tg){
    }


    public int generateId(){
        return -1; //to be implemented
    }
}