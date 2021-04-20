package com.viseguardstudios.asteroid_miner.model.entities;

/**
 * Egy mozgatható entitás osztálya
 */

public abstract class MovableEntity extends Entity {


    protected Asteroid currentAsteroid;

    /**
     * Default constructor
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

    public Asteroid getCurrentAsteroid() {
        return currentAsteroid;
    }

    public enum AsteroidPlaces{
        Inside,
        Orbit,
        Vessel
    }

}
