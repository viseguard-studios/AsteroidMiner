package com.viseguardstudios.asteroid_miner.model.entities;

import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.util.Namer;
import com.viseguardstudios.asteroid_miner.util.Sprite;
import com.viseguardstudios.asteroid_miner.util.Vector2;

import java.util.List;

/**
 * Egy adott entitás ( vizuális megjelenítéssel rendelkező játékelem) osztálya.
 */
public abstract class Entity {

    public static final Sprite missing = new Sprite("assets/graphics/sprites/missing.png", 10);

    protected String name;

    /**
     * A játéktér tárolója.
     */
    protected Scene scene;

    public boolean turnUsed = false;

    public Vector2 pos;


    /**
     * Default constructor
     */
    public Entity(String name) {
        this.name = name;
    }

    public Entity(){ }


    /**
     * Akkor hívódik meg, ha az adott körben már minden játékos lépett. A robotok ezt használják például a mozgásra.
     * @param closeToSun
     */
    public void roundEnd(boolean closeToSun){
        turnUsed = false;
    };


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


    public String printStatus(){
        return new String(name);
    }

    public void explode(){
        System.out.println("[EVENT] "+ getName()+" exploded");
    };

    /**
     * Visszatér az egységen végezhető műveletekkel
     */
    public abstract List<String> getActions();

    public abstract void doAction(String[] args);

    /**
     * További getterek, setterek
     */

    //public Asteroid getCurrentAsteroid(){return currentAsteroid;}

    public Vector2 getPos(){return pos;}

    public void setPos(Vector2 newpos) {this.pos = newpos;}

    public Sprite getSprite(){return missing;}


    public void setDefaultName(){
        this.name = Namer.getName(this.getClass());
    }

    public void setName(String s){
        name =s;
    }

    public String getName() {
        return name;
    }

}