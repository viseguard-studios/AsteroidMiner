package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.GameManager;

public class SolarDistCmd extends Command {
    @Override
    public String getName() {
        return "solarDistance";
    }

    @Override
    public void Execute(String[] params) {
        GameManager current = Engine.getInstance().getScene().getManager();
        int dist = Integer.parseInt(params[1]);
        current.ChangeAFDistance(dist);

        System.out.println("Solar distance changed to "+dist);
    }
}
