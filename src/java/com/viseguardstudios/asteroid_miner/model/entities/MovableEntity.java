package com.viseguardstudios.asteroid_miner.model.entities;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.util.Vector2;
import com.viseguardstudios.asteroid_miner.view.panels.ingame.ActionBar;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
        if(turnUsed){
            return;
        }
        currentAsteroid.depart(this);
        to.arrive(this);
        arriveTo(to);

        turnUsed = true;
    }

    @Override
    public void doAction(String[] args) {
        if(args[0].equals("move")) {
            if (!turnUsed) {
                List<Asteroid> asteroids = currentAsteroid.getPhysicalNeighbours();
                List<String> posib = new ArrayList<>();
                for (Asteroid a : asteroids
                ) {
                    posib.add(a.getName());
                }
                var window = Engine.getInstance().getMainWindow();

                String res = (String) JOptionPane.showInputDialog(window, "Please choose a destination: ", "Choose", JOptionPane.PLAIN_MESSAGE, null, posib.toArray(), "");

                for (Asteroid a : currentAsteroid.getPhysicalNeighbours()) {
                    if (a.getName().equals(res)) {
                        move(a);
                        scene.getManager().notifyListeners();
                        break;
                    }
                }
            }
        }
    }

    protected void arriveTo(Asteroid to) {
        int i = to.getLocalEntityNumber();
        this.pos = getEntityOrbitPos(to.pos,i);
        this.currentAsteroid = to;
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

    /**
     * A cél, hogy az entitások az aszteroida körül jelenjenek meg.
     * @param asteroidPos
     * @return
     */
    public Vector2 getEntityOrbitPos(Vector2 asteroidPos, int i){
        float orbitRadius = 20;
        int relX = (int) (orbitRadius*Math.cos(i));
        int relY = (int) (orbitRadius*Math.sin(i));
        Vector2 relPos = new Vector2(relX,relY);
        return asteroidPos.add(relPos);

    }

}
