package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;

import java.util.List;
import java.util.Set;

public class NeighboursCmd extends Command {
    @Override
    public String getName() {
        return "neighbours";
    }

    @Override
    public void Execute(String[] params) {
        if (params.length < 2) {
            System.out.println("Not enough params");
            return;
        }
        List<Asteroid> asteroids = Engine.getInstance().getScene().GetManager().getAsteroids();
        for(Asteroid a : asteroids){
            if(a.getName().equals(params[1])){

            }
        }
        System.out.println("Vessel not found");
    }
}
