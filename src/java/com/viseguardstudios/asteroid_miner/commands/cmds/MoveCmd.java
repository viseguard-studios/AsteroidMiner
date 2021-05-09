package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.MovableEntity;

import java.util.List;
import java.util.Scanner;

public class MoveCmd extends Command {
    @Override
    public String getName() {
        return "move";
    }

    @Override
    public void Execute(String[] params) {

        if(params.length < 2){
            System.out.println("Not enough params");
            return;
        }

        String vesselName = params[1];

        var sss = Engine.getInstance().getScene().getEntities();

        MovableEntity entity = null;
        for (var s : sss) {
            if (s.getName().equals(vesselName) && s instanceof MovableEntity) {
                entity = (MovableEntity) s;
            }
        }

        if (entity == null) {
            System.out.println("No such Entity");
            return;
        }

        if(entity.turnUsed){
            System.out.println("Turn is already used");
            return;
        }

        System.out.println("Neighbours:");

        List<Asteroid> reachableAsteroids = entity.getCurrentAsteroid().ReachableAsteroids();
        for (int i = 0, reachableAsteroidsSize = reachableAsteroids.size(); i < reachableAsteroidsSize; i++) {
            Asteroid targets = reachableAsteroids.get(i);
            System.out.println(i+" - " + targets.getName());
        }

        int choice = Integer.parseInt(Engine.in.nextLine());

        entity.move(reachableAsteroids.get(choice));
    }
}
