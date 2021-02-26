package com.viseguardstudios.asteroid_miner;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    int capacity;
    List<Item> items = new ArrayList<>();

    public Inventory(int capacity) {
        this.capacity = capacity;
    }

    public boolean InsertItem(Item item){
        //Try to add the item to the inventory
        //If there is no space return false
        return false;
    }

}
