package com.viseguardstudios.asteroid_miner.model.recipe;

import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Inventory;
import com.viseguardstudios.asteroid_miner.model.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.building.TeleportGate;
import com.viseguardstudios.asteroid_miner.model.item.*;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

import java.util.Set;

/**
 * Teleportkapu-párok elkészítéséhez szükséges speciális recept típus.
 */
public class TeleportGateRecipe extends Recipe {

    /**
     * Default constructor
     */
    public TeleportGateRecipe() {
        input.add(new Iron(2));
        input.add(new Ice());
        input.add(new Uranium());
    }

    /**
     * A sikeres készítés után ilyen típusú eredményhez ("elemhez") lehet hozzájutni.
     */
    private TeleportGate result;

    /**
     * Az elkészült dolog mennyisége (egyszerre hány egység készül el belőle)
     */
    private int amount;

    /**
     * Létrehozza a kívánt terméket a receptből, figyelembe véve a telepes tárhelyét.
     * @param ss
     */

    @Override
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
        Set<Item> items = inv.getItems();
        Logger.returned();
        Logger.functionCalled("a.getInventory()");
        Inventory inventory = a.GetInventory();
        Logger.returned();
        Logger.functionCalled("inventory.GetItems()");
        Set<Item> aItems = inventory.getItems();
        Logger.returned();

        /***
         * Összegyűjti, hogy összesen hány darab megfelelő elemet kell megtalálni a raktárakban
         */
        int neededItems = 0;
        for(Item i: input)
            neededItems+=i.getAmount();

        /**
         * A telepes raktárából a készítéshez eltávolítandó elemeket számolja,
         * a kapuk behelyezéséhez szükséges szabad hely ellenőrzéséhez fog kelleni.
         */
        int removedFromSS = 0;

        /***
         * Megvizsgálja, hogy a telepesnél van-e elég item a készítéshez
         */
        for(Item input: input ){
            Logger.lognl("Check if SpaceShip inventory has enough supply");
            for(Item item: items ) {
                Logger.functionCalled("item.Satisfies(input)");
                removedFromSS+= item.Satisfies(input);
                Logger.returned();
            }

            neededItems-= removedFromSS;

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
            Logger.lognl("We have enough supply!");
        }
        else {
            Logger.lognl("The recipe can't be crafted!");
            return false;
        }

        /***
         * A telepes tárhelyének vizsgálata.
         */
        int SpaceLeft = ss.getInventory().getSize();
        for(Item i: ss.getInventory().getItems()){
            SpaceLeft-=i.getAmount();
        }

        /***
         *Később általánosításra kerül, hiányzó belső működés miatt egyelőre konkrét számmal hasonlít össze (kapuk által elfoglalt hely)
         */
        Logger.lognl("Checking if the SpaceShip has enough space for storing the gates.");
        if(SpaceLeft< 2){
            Logger.lognl("There isn't enough space to store gates. The recipe can't be crafted. ");
            return false;
        }
        /***
         * Minden feltétel teljesül, el lehet készíteni a receptet.
         */
        Logger.lognl("There is enough space to store gates. We can craft the recipe. ");
        return true;
    }



    protected void MakeResult(SpaceShip ss){
        /***
         * Új teleportkapu-pár létrehozása, konstruktor meghívása
         */
        Logger.lognl("Creating a teleport gate pair, constructor: ");
        Logger.functionCalled("TeleportGateItem()");
        TeleportGateItem gate = new TeleportGateItem();
        Logger.returned();
        /***
         * A telepes raktárába belehelyezzük a kapukat
         */
        Logger.lognl("Inserting teleport gates to SpaceShip inventory.");
        Logger.functionCalled("ss.getInventory().InsertItem(gate)");
        ss.getInventory().InsertItem(gate);
        Logger.returned();
    };

}