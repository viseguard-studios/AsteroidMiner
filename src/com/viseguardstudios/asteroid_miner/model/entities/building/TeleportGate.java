package com.viseguardstudios.asteroid_miner.model.entities.building;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;

import java.util.*;

/**
 * A teleportkapukat reprezentáló osztály.
 */
public class TeleportGate extends Building {

    /**
     * Default constructor
     */
    public TeleportGate(int id) {
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

    public TeleportGate getIdPair(int id){
    }

    public void setPair(TeleportGate pair){
    }

    public void addIdListItem(TeleportGate tg){
    }
}