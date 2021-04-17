package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;

public class StartgameCmd extends Command {
    @Override
    public String getName() {
        return "startgame";
    }

    @Override
    public void Execute(String[] params) {

        if(params.length < 3){
            System.out.println("Not enough params");
            return;
        }

        int seed = Integer.parseInt(params[1]);
        String file = params[2];

        Engine.getInstance().StartGame(seed);
    }
}
