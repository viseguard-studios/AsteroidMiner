package com.viseguardstudios.asteroid_miner.model.item.resource;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.item.Item;

/**
 * Az uránium reprezentálására szolgál.
 */
public class Uranium extends Resource {

    private int counter; //számláló a robbanáshoz

    /**
     * Konstruktor
     */
    public Uranium(){
        counter = 0;
    }

    /**
     * Konstruktor értékátadással
     */
    public Uranium(int i){
        if(i>2) throw new IllegalArgumentException("Uranium can't have more than 2 exposures!");
        counter = i;
    }
    /**
     * Meghatározza, hogy az átadott Item használható-e a jelenlegi helyett.
     */
    @Override
    public boolean satisfies(Item i) {
        //nem egyezik az elemtípus
        return (i instanceof Uranium);
    }

    /**
     *Napközelben a számláló határának elérése után felrobbantja a hozzá tartozó aszteroidát
     */
    @Override
    public void nearSun(Asteroid a) {
        if(a != null){ //ha a raktár egy aszteroidához tartozik (telepes, ufo raktárában védett)
            counter+=1;
            //ha elérte a robbanás határát
            if(counter >= 3) {
                a.explode(); //aszteroida robban
            }
        }
    }

    @Override
    public String getName() {
        return "Uranium";
    }

}