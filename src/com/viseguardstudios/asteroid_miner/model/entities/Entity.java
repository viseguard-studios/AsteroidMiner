package com.viseguardstudios.asteroid_miner.model.entities;

import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.util.Namer;
import com.viseguardstudios.asteroid_miner.util.Vector2;

/**
 * Egy adott entitás ( vizuális megjelenítéssel rendelkező játékelem) osztálya.
 */
public abstract class Entity {


    protected String name;

    /**
     * Név lekérdezése
     * @return
     */
    public String getName(){
        return new String(name);
    }

    /**
     * Név átállítása
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Default constructor
     */
    public Entity() {
    }



    /**
     * A játéktér tárolója.
     */
    protected Scene scene;

    protected Asteroid currentAsteroid;

    public Vector2 pos;

/*

    String name;

    public void setName(String s){
        name =s;
    }

    public String getName() {
        return name;
    }
*/
    /**
     * Default constructor
     */
    public Entity(String name) {
        this.name = name;
    }
/*
    public Entity(){
        this.name = Namer.getName(this.getClass());
    }
*/
    /**
     * Akkor hívódik meg, ha az adott körben már minden játékos lépett. A robotok ezt használják például a mozgásra.
     * @param closeToSun
     */
    public abstract void RoundEnd(boolean closeToSun);

    /**
     * A játéktér getter-e.
     * @return
     */
    public Scene GetScene() {
        return scene;
    }
    public void setScene(Scene s) {
        this.scene = s;
    }


    /**
     * Napviharról értesíti az egységet.
     */
    public abstract void SolarFlare();


    public void printStatus(){
        System.out.println(name);
    }

    public abstract void explode();

    /**
     * További getterek, setterek
     */

    public Asteroid getCurrentAsteroid(){return currentAsteroid;}

    public Vector2 getPos(){return pos;}

    public void setPos(Vector2 newpos) {this.pos = newpos;}
}