package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;

public class StatusCmd extends Command {
    @Override
    public String getName() {
        return "status";
    }

    @Override
    public void Execute(String[] params) {
        if(Engine.getInstance().getScene() != null) {

            var entities = Engine.getInstance().getScene().getEntities();

            if (params.length == 1) {


                for (var ent : entities) {
                    System.out.println(ent.getName());
                }

            }
            else {
                for (var ent :
                        entities) {
                    if (ent.getName().equals(params[1])) {
                        var s = ent.printStatus();
                        System.out.println(s);
                    }
                }
            }
        }
        else
        {
            System.out.println("NO Scene");
        }
    }
}
