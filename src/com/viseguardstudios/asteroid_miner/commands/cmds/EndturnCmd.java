package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;

public class EndturnCmd extends Command {

    @Override
    public String getName() {
        return "endturn";
    }

    @Override
    public void Execute(String[] params) {
        Engine.getInstance().getGameManager().takeTurn();
    }
}
