package com.viseguardstudios.asteroid_miner.commands;

import com.viseguardstudios.asteroid_miner.commands.cmds.HelpCmd;
import com.viseguardstudios.asteroid_miner.commands.cmds.StartgameCmd;
import com.viseguardstudios.asteroid_miner.commands.cmds.StatusCmd;

import java.util.*;

public class CommandExecutor {

    public Map<String, Command> getCommands() {
        return commands;
    }

    Map<String,Command> commands = new HashMap<>();

    public CommandExecutor() {
        registerCommands();
    }

    private void registerCommands(){
        registerCommand(new StartgameCmd());
        registerCommand(new HelpCmd());
        registerCommand(new StatusCmd());
    }

    public void registerCommand(Command cmd){
        commands.put(cmd.getName(),cmd);
    }

    public void execute(String input){

        var tokens = input.split(" ");

        var cmd = commands.get(tokens[0]);

        if(cmd != null){

            cmd.Execute(tokens);

        }
    }

}
