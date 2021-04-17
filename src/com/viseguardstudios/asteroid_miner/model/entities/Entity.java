package com.viseguardstudios.asteroid_miner.model.entities;

import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.util.Namer;
import com.viseguardstudios.asteroid_miner.util.Vector2;

/**
 * Egy adott entitás ( vizuális megjelenítéssel rendelkező játékelem) osztálya.
 */
public abstract class Entity {


    /**
     * A játéktér tárolója.
     */
    protected Scene scene;


    public Vector2 pos;



    String name;

    public void setName(String s){
        name =s;
    }

    public String getName() {
        return name;
    }

    /**
     * Default constructor
     */
    public Entity(String name) {
        this.name = name;
    }

    public Entity(){
        this.name = Namer.getName(this.getClass());
    }

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
        // TODO implement here
        return scene;
    }

    /**
     * Napviharról értesíti az egységet.
     */
    public abstract void SolarFlare();

    public void setScene(Scene s) {
        this.scene = s;
    }


    public void printStatus(){
        System.out.println(name);
    }

}