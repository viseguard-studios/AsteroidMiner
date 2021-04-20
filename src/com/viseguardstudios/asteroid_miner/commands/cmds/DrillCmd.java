package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Entity;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;

import java.util.ArrayList;
import java.util.List;
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

        GameManager gm = Engine.getInstance().getScene().GetManager();
        List<Player> players = gm.getAllPlayers();
        for(Player player : players){
            if(player == gm.getCurrentPlayer()){
                for(Vessel v : player.getOwnedVessels()){
                    if(v.getName().equals(params[1])) {
                        if(v.Drill()){
                            var ast = v.getCurrentAsteroid();
                            System.out.println(ast.getName()+" crust = "+ast.getCrustSize());
                        }
                        else{
                            System.out.println("can't drill");
                        }
                        return;
                    }
                }
            }
        }
        System.out.println("Vessel not found");
    }
}
