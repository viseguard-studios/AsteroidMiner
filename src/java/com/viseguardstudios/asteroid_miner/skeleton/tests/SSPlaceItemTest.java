package com.viseguardstudios.asteroid_miner.skeleton.tests;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.item.resource.Iron;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.STest;
import com.viseguardstudios.asteroid_miner.skeleton.Test;

import java.util.Scanner;

@STest
public class SSPlaceItemTest extends Test {
    private Asteroid a;
    private SpaceShip ss;
    private Item i;

    @Override
    public void Setup(Scanner sc) {
        a = new Asteroid();
        ss = new SpaceShip(a);
        i = new Iron();
        //IInventory inventory = new Inventory();
        //a.setInventory(inventory);

        System.out.println("Would you like to set the natural resource amount to 0? [Y/N]");
         var answer = sc.nextLine();
        if(!answer.equals("Y")) {
            Iron is = new Iron();
            //is.setAmount(1);
            //a.setResource(is);
        }

        System.out.println("Would you like to set the asteroid crust size to 0? [Y/N]");
        answer = sc.nextLine();
        if(answer.equals("Y")) a.setCrustSize(0);
        else a.setCrustSize(1);

        System.out.println("Should the inventory has free space? [Y/N]");
        answer = sc.nextLine();
        //TODO : fix this
        //if(answer.equals("Y")) a.getInventory().setSize(1);
        //else a.getInventory().setSize(0);

        System.out.println("Would you like the asteroid to be exploded? [Y/N]");
        answer = sc.nextLine();
        a.setExploded(answer.equals("Y"));

    }

    @Override
    public void Run() {
        Logger.functionCalled("ss.PlaceItem(i)");
        ss.placeItem(i);
        Logger.returned();
    }
}
