package com.viseguardstudios.asteroid_miner.map_loader;


import com.viseguardstudios.asteroid_miner.model.Player;

public class PlayerCreator {

    public Player PlayerFromName(string name){
        Player player = new Player();
        player.setName(name);
    }

}
