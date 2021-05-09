package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;

public class HelpCmd extends Command {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void Execute(String[] params) {

        var cmds = Engine.getInstance().getCmdExec().getCommands();
        for (Command cmd : cmds.values()) {
            System.out.println("- " + cmd.getName());
        }

    }
}
