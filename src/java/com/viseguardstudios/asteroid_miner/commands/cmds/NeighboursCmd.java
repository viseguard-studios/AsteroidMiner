package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;

import java.util.ArrayList;
import java.util.List;

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
        List<Asteroid> asteroids = Engine.getInstance().getScene().getManager().getAsteroids();
        for(Asteroid a : asteroids){
            if(a.getName().equals(params[1])){
                System.out.println("Neighbours:");

                String rType;

                int number = 1;
                List<Asteroid> asteroidList = new ArrayList<Asteroid>();
                asteroidList.addAll(a.getReachableAsteroids());
                for (Asteroid neighbour : asteroidList){
                    if(neighbour.getCrustSize() == 0){
                        if (neighbour.isMined())
                            rType = "Empty";
                        else
                            rType = neighbour.getInventory().getItems().get(0).getName();
                    }
                    else
                        rType = "?";
                    System.out.println("0" + number + " - "
                            + neighbour.getName() + " - "
                            + rType);
                }
                return;

            }
        }
        System.out.println("Asteroid not found");
    }
}
