package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;
import com.viseguardstudios.asteroid_miner.model.item.Item;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PlaceCmd extends Command {
    @Override
    public String getName() {
        return "place";
    }

    @Override
    public void Execute(String[] params) {
        if(params.length < 3){
            System.out.println("Not enough params");
            return;
        }
        List<Player> players = Engine.getInstance().getScene().GetManager().getAllPlayers();
        for(Player player : players){
            for(Vessel v : player.getOwnedVessels()){
                if(v.getName().equals(params[2])){
                    if(v.getInventory().getItems() == null){
                        System.out.println("Inventory is empty");
                        return;
                    }
                    System.out.println("Items:");
                    int number = 1;
                    for (Item i : v.getInventory().getItems()){
                        //TODO item neve?
                        number++;
                    }
                    Scanner sc = new Scanner(System.in);
                    number = sc.nextInt();
                    v.placeItem(v.getInventory().getItems().get(number - 1));
                }
            }
        }
        System.out.println("Vessel not found");
    }
}
