package com.viseguardstudios.asteroid_miner;

import com.viseguardstudios.asteroid_miner.ItemType;

public class Item {

    int amount;
    ItemType type;

    public Item(ItemType type, int amount) {
        this.type = type;
        this.amount = amount;
    }
}
