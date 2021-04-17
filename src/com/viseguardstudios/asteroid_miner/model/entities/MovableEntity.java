package com.viseguardstudios.asteroid_miner.model.entities;

public abstract class MovableEntity extends Entity {


    /**
     * Default constructor
     *
     * @param name
     */
    public MovableEntity() {
    }

    public abstract AsteroidPlaces getPlace();


    public void move(Asteroid to){
        //TODO implement
    }

    public void asteroidExploded(){

    }

    public enum AsteroidPlaces{
        Inside,
        Orbit,
        Vessel
    }

}
