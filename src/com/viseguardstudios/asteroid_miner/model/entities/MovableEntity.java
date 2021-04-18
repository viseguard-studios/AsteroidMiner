package com.viseguardstudios.asteroid_miner.model.entities;

/**
 * Egy mozgatható entitás osztálya
 */

public abstract class MovableEntity extends Entity {


    /**
     * Default constructor
     *
     * @param name
     */
    public MovableEntity() {
    }

    public abstract AsteroidPlaces getPlace();

    /**
     * Az entitás mozgását valósítja meg: eltávolítás az aktuális helyről, hozzáadás az újhoz, pozíció beállítása
     */
    public void move(Asteroid to){
        currentAsteroid.depart(this);
        to.arrive(this);
        this.pos = to.getPos();
    }

    /**
     * Az aktuális aszteroida felrobbanásakor hívódik meg. Néhány leszármazott felüldefiniálja.
     */
    public void AsteroidExploded(){
        explode();
    }

    public enum AsteroidPlaces{
        Inside,
        Orbit,
        Vessel
    }

}
