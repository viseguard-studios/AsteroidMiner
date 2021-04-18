package com.viseguardstudios.asteroid_miner.model.item;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.item.resource.Resource;

/**
 * A vízjég reprezentálására szolgál.
 */
public class Ice extends Resource {

    private boolean evaporated; //volt-e már párolgás a körben

    /**
     * Konstruktor
     */
    public Ice(){
        evaporated = false;
    }

    /**
     * Meghatározza, hogy az átadott Item használható-e a jelenlegi helyett.
     */
    @Override
    public boolean satisfies(Item i) {
        if(i instanceof Ice)
            return true;
        else //nem egyezik az elemtípus
            return false;
    }

    /**
     * Párolgási érték visszaállítása egy kör végén
     */
    @Override
    public void turnEnd(){
        evaporated = false;
    }

    @Override
    public void nearSun(Asteroid a){
        if( (evaporated == false) && (a != null) ){ //ha aszteroida raktárában van és még nem történt párolgás
            a.getInventory().removeItem(this); //eltávolítja magát az aszteroida raktárából
            for (Item i : a.getInventory().getItems()){
                if(this.satisfies(i)) { //ha vele egyező típust talál
                    Ice ice = (Ice)i;
                    ice.setEvaporated(true); //jelzi, hogy van már párolgás
                }
            }
        }
    }

    /**
     * Párolgás gettere, settere
     */
    public boolean getEvaporated(){
        return evaporated;
    }
    public void setEvaporated(boolean ev){
        evaporated = ev;
    }

}