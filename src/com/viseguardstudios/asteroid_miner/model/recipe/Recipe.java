package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.inventory.IInventory;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.inventory.AsteroidInventory;
import com.viseguardstudios.asteroid_miner.model.inventory.SSInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

import java.util.*;

/**
 * A telepesek által elkészíthető receptekért felel. Egy recept adott számú kibányászott nyersanyag (elem) felhasználásával elkészített dolgok pontos hozzávalóit, és az elkészült eredmény típusát tárolja.
 */
public abstract class Recipe {


    /**
     * Az aktuális recepthez szükséges hozzávalók tárolása.
     */
    protected ArrayList<Item> input = new ArrayList<Item>();

    /**
     * Meghatározza, hogy egy adott raktárban és aszteroidán lévő kibányászott nyersanyagkészlet elegendő-e az adott recept elkészítéséhez.
     * @param ss
     * @return elkészíthető-e
     */
    public boolean canCraft(SpaceShip ss) {

        /***
         * Az aszteroida és a telepes raktárának vizsgálatához szükséges dolgok lekérdezése (raktárak elemkészlete)
         */
        Asteroid a = ss.getCurrentAsteroid();
        SSInventory inv = ss.getInventory();
        ArrayList<Item> items = new ArrayList<Item>(inv.getItems());
        AsteroidInventory inventory = a.getInventory();
        ArrayList<Item> aItems = new ArrayList<Item>(inventory.getItems());
        ArrayList<Item> recipe = new ArrayList<Item>(input);


        /***
         * Megvizsgálja, hogy a telepesnél van-e elég item a készítéshez
         */
        for (int i = 0; i < recipe.size(); i++) {
            Item rec = recipe.get(i);
            for (int j = 0; j < items.size(); j++) {
                Item item = items.get(j);
                if (item.satisfies(rec)) {
                    recipe.remove(rec);
                    items.remove(item);
                    i--;
                    break;
                }
            }
        }

        /**
         * Ha megvan minden hozzávaló, kilép, ha nem, az aszteroida raktárában keres tovább
         */
        if(recipe.size()==0)  //ha megvan az összes szükséges hozzávaló
            return true;

        for (int i = 0; i < recipe.size(); i++) {
            Item rec = recipe.get(i);
            for (int j = 0; j < aItems.size(); j++) {
                Item aItem = aItems.get(j);
                if (aItem.satisfies(rec)) {
                    recipe.remove(rec);
                    aItems.remove(aItem);
                    i--;
                    break;
                }
            }
        }

        /***
         * Ha van elég item összesen az elkészítéshez, igazzal, ellenkező esetben hamissal tér vissza
         */
        if(recipe.size()==0)
            return true;
        else
            return false;
    }


    /**
     * Akkor hívódik meg ha ténylegesen le akarjuk gyártani ezt a receptet.
     */
    public void craft(SpaceShip ss) {

        /***
         * Az aszteroida és a telepes raktárának vizsgálatához szükséges dolgok lekérdezése (raktárak elemkészlete)
         */
        Asteroid a = ss.getCurrentAsteroid();
        SSInventory inv = ss.getInventory();
        AsteroidInventory inventory = a.getInventory();

        /***
         * Az összes lehetséges recepthez szükséges item-et eltávolítjuk a telepes/aszteroida raktárából (ha a telepesnél nincs, az aszteroidán lesz belőle)
         */
        for(Item input: input){
            if(!inv.removeItem(input))
                inventory.removeItem(input);
        }

        /***
         * Sikeres eltávolítások után az eredmény elkészítése
         */
        makeResult(ss);
    }

    /**
     * Létrehozza a kívánt terméket a receptből.
     * @param ss
     */
    protected abstract void makeResult(SpaceShip ss);

    /**
     *  Az összetevők listájának getter-e
     * @return input
     */
    public ArrayList<Item> getInput(){
        return input;
    }

}