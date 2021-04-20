package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;
import com.viseguardstudios.asteroid_miner.model.item.Item;

import java.util.List;

public class PickupCmd extends Command {
    @Override
    public String getName() {
        return "pickup";
    }

    @Override
    public void Execute(String[] params) {
        if(params.length < 4){
            System.out.println("Not enough params");
            return;
        }

        GameManager gm = Engine.getInstance().getScene().getManager();
        List<Player> players = gm.getAllPlayers();
        var player = gm.getCurrentPlayer();

        for(Vessel v : player.getOwnedVessels()){
            if(v.getName().equals(params[3])) {
                if(v.getCurrentAsteroid().isMined()){
                    String type = params[1];
                    Item item = v.getCurrentAsteroid().pickupItem(type);
                    v.getInventory().insertItem(item);
                    if(item == null)
                        System.out.println("can't find the item");
                }
                else
                    System.out.println("can't pick up items");
            }

        }


        //System.out.println("Vessel not found");
    }
}
