package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;

import java.util.Set;

public class HideCmd extends Command {
    @Override
    public String getName() {
        return "hide";
    }

    @Override
    public void Execute(String[] params) {
        if (params.length < 3) {
            System.out.println("Not enough params");
            return;
        }

        GameManager gm = Engine.getInstance().getScene().GetManager();
        Set<Player> players = gm.getAllPlayers();
        for (Player player : players) {
            if(player == gm.getCurrentPlayer()){
                for (Vessel v : player.getOwnedVessels()) {
                    if (v.getName().equals(params[2])) {
                        if (params[1].equals("enter")) {
                            if(v.Hide())
                                System.out.println(v.getName() + " is hidden");
                            else
                                System.out.println("can't hide");
                            return;
                        } else if (params[1].equals("exit")) {
                            if(v.ExitHiding())
                                System.out.println(v.getName() + " exit");
                            else
                                System.out.println("can't exit");
                            return;
                        } else {
                            System.out.println("Incorrect parameter");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("Vessel not found");
        return;
    }
}
