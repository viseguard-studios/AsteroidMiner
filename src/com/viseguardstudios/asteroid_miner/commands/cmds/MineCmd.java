package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;
import com.viseguardstudios.asteroid_miner.model.item.Item;

import java.util.List;

public class MineCmd extends Command {
    @Override
    public String getName() {
        return "mine";
    }

    @Override
    public void Execute(String[] params) {
        if (params.length < 2) {
            System.out.println("Not enough params");
            return;
        }

        GameManager gm = Engine.getInstance().getScene().GetManager();
        List<Player> players = gm.getAllPlayers();
        for (Player player : players) {
            if(player == gm.getCurrentPlayer()){
                for (Vessel v : player.getOwnedVessels()) {
                    if (v.getName().equals(params[1])){
                        Item mined = v.mine();
                        if(mined != null){
                            var ast = v.getCurrentAsteroid();
                            System.out.println(ast.getName()+" mined ");
                            System.out.println("* Item: " + mined.getType());
                        }
                        else {
                            System.out.println("can't mine");
                        }
                        return;
                    }
                }
            }
        }
        System.out.println("Vessel not found");
    }
}