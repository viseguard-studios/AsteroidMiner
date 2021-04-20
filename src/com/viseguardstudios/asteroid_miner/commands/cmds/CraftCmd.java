package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;
import com.viseguardstudios.asteroid_miner.model.recipe.Recipe;
import com.viseguardstudios.asteroid_miner.model.recipe.RobotRecipe;
import com.viseguardstudios.asteroid_miner.model.recipe.SpaceStationRecipe;
import com.viseguardstudios.asteroid_miner.model.recipe.TeleportGateRecipe;

import java.util.List;

public class CraftCmd extends Command {
    @Override
    public String getName() {
        return "craft";
    }

    @Override
    public void Execute(String[] params) {
        if(params.length < 2){
            System.out.println("Not enough params: craft <spaceShip> <recipe>");
            return;
        }
        GameManager gm = Engine.getInstance().getScene().getManager();
        List<Player> players = gm.getAllPlayers();
        Recipe recipe;
        for(Player player : players){
            if(player == gm.getCurrentPlayer()){
                for(Vessel v : player.getOwnedVessels()){
                    if(v.getName().equals(params[1])) {

                        if ("Base".equals(params[2]))
                            recipe = new SpaceStationRecipe();
                        else if ("Gate".equals(params[2]))
                            recipe = new TeleportGateRecipe();
                        else if ("Robot".equals(params[2]))
                            recipe = new RobotRecipe();
                        else {
                            System.out.println("Invalid recipe type. Please choose from these: Base,Gate,Robot.");
                            recipe=null;
                            return;
                        }

                        if(recipe==null){
                            System.out.println("This vessel can't craft.");
                        } else {
                            if(v.Craft(recipe)){
                                System.out.println("You successfully crafted a " + params[2]);
                                if(params[2].equals("Base"))
                                    System.out.println("Congratulations, you won the game!");
                            }
                            else
                                System.out.println("Recipe can't be crafted");
                        }
                    }
                }
            }
        }
    }
}
