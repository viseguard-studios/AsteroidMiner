package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Inventory;
import com.viseguardstudios.asteroid_miner.model.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

import java.util.*;

/**
 * A telepesek által elkészíthető receptekért felel. Egy recept adott számú kibányászott nyersanyag (elem) felhasználásával elkészített dolgok pontos hozzávalóit, és az elkészült eredmény típusát tárolja.
 */
public abstract class Recipe {

    /**
     * Default constructor
     */
    public Recipe() {

    }

    /**
     * Az aktuális recepthez szükséges hozzávalók tárolása.
     */
    protected List<Item> input = new LinkedList<>();

    /**
     * Meghatározza, hogy egy adott raktárban és aszteroidán lévő kibányászott nyersanyagkészlet elegendő-e az adott recept elkészítéséhez.
     * @param ss
     * @return
     */
    public boolean CanCraft(SpaceShip ss) {

        /***
         * Az aszteroida és a telepes raktárának vizsgálatához szükséges dolgok lekérdezése (raktárak elemkészlete)
         */
        Logger.functionCalled("ss.GetAsteroid()");
        Asteroid a = ss.getCurrentAsteroid();
        Logger.returned();
        Logger.functionCalled("ss.getInventory()");
        Inventory inv = ss.getInventory();
        Logger.returned();
        Logger.functionCalled("inv.GetItems()");
        List<Item> items = inv.getItems();
        Logger.returned();
        Logger.functionCalled("a.getInventory()");
        Inventory inventory = a.GetInventory();
        Logger.returned();
        Logger.functionCalled("inventory.GetItems()");
        List<Item> aItems = inventory.getItems();
        Logger.returned();

        /***
         * Összegyűjti, hogy összesen hány darab megfelelő elemet kell megtalálni a raktárakban
         */
        int neededItems = 0;
        for(Item i: input)
            neededItems+=i.getAmount();

        /***
         * Megvizsgálja, hogy a telepesnél van-e elég item a készítéshez
         */
        for(Item input: input ){
            Logger.lognl("Check if SpaceShip inventory has enough supply");
            for(Item item: items ) {
                Logger.functionCalled("item.Satisfies(input)");
                neededItems-= item.Satisfies(input);
                Logger.returned();
            }

            if(neededItems==0){
                Logger.lognl("It has enough supply.");
            }
            /***
             * Ha nincs elég item a telepesnél, az aszteroida raktárának item-eit is megvizsgáljuk
             */
            else {
                Logger.lognl("Check if Asteroid inventory has the remaining supply");
                for(Item aItem: aItems ) {
                    Logger.functionCalled("aItem.Satisfies(input)");
                    neededItems-= aItem.Satisfies(input);
                    Logger.returned();
                }
            }
        }

        /***
         * Ha van elég item összesen az elkészítéshez, igazzal, ellenkező esetben hamissal tér vissza
         */
        if(neededItems==0) {
            Logger.lognl("The recipe can be crafted, we have enough supply!");
            return true;
        }
        else {
            Logger.lognl("The recipe can't be crafted!");
            return false;
        }
    }

    /**
     * Akkor hívódik meg ha ténylegesen le akarjuk gyártani ezt a receptet.
     * @param ss
     */
    public void Craft(SpaceShip ss) {

        /***
         * Az aszteroida és a telepes raktárának vizsgálatához szükséges dolgok lekérdezése (raktárak elemkészlete)
         */
        Logger.functionCalled("ss.GetAsteroid()");
        Asteroid a = ss.getCurrentAsteroid();
        Logger.returned();
        Logger.functionCalled("ss.getInventory()");
        Inventory inv = ss.getInventory();
        Logger.returned();
        Logger.functionCalled("a.GetInventory()");
        Inventory inventory = a.GetInventory();
        Logger.returned();

        /***
         * Az összes lehetséges recepthez szükséges item-et eltávolítjuk a telepes/aszteroida raktárából
         */
        Logger.lognl("Removing the necessary items from SpaceShip or Asteroid inventory.");
        for(Item input: input){
            Logger.functionCalled("inv.RemoveItem(input)");
            int amount= inv.RemoveItem(input);
            Logger.returned();
            Logger.functionCalled("input.Reduce(amount)");
            input.Reduce(amount);
            Logger.returned();

            /***
             * Az adott item-ből nincs elegendő a telepesnél, így az aszteroidából távolítjuk el a maradékot
             */
            if(amount != input.getAmount()){
                Logger.functionCalled("inventory.RemoveItem(input)");
                inv.RemoveItem(input);
                Logger.returned();
            }
        }

        /***
         * Sikeres eltávolítások után az eredmény elkészítése
         */
        Logger.functionCalled("MakeResult(ss)");
        MakeResult(ss);
        Logger.returned();
    }

    /**
     * Létrehozza a kívánt terméket a receptből.
     * @param ss
     */
    protected abstract void MakeResult(SpaceShip ss);

    /**
     *  Az összetevők listájának getter-e
     * @return input
     */
    public List<Item> getInput(){
        return input;
    }

}