package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Entity;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;

import java.util.ArrayList;
import java.util.Set;

public class DrillCmd extends Command {
    @Override
    public String getName() {
        return "drill";
    }

    @Override
    public void Execute(String[] params) {
        if(params.length < 2){
            System.out.println("Not enough params");
            return;
        }
        Set<Player> players = Engine.getInstance().getScene().GetManager().getAllPlayers();
        for(Player player : players){
            for(Vessel v : player.getOwnedVessels()){
                if(v.getName().equals(params[1])) {
                    v.Drill();

                    var ast = v.getCurrentAsteroid();

                    System.out.println(ast.getName()+" crust = "+ast.getCrustSize());
                }
                return;
            }
        }
        System.out.println("Vessel not found");
    }
}
