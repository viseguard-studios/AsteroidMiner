package com.viseguardstudios.asteroid_miner.map_loader;


import com.viseguardstudios.asteroid_miner.model.Player;

public class PlayerCreator {

    public static Player PlayerFromCommandLine(String input) throws Exception {
        Player player = new Player();
        player.setName("default");
        String processedInput = FileOpener.cutComments(input);

        if (FileOpener.getNameAndPropsFromLine((processedInput)).size()!=1) // If we have properties
          {
          //Getting the name, it should be the only property TODO should we ignore instead?
            try {
                if (!FileOpener.getNameAndPropsFromLine(processedInput).get(0).contentEquals("name")) //If the property is not "name"
                    {
                     throw  new Exception("Bad property in object describer");
                    }
             processedInput = FileOpener.getNameAndPropsFromLine(processedInput).get(1);
             player.setName(processedInput);
             }
             catch (Exception e)
             {
            throw new Exception("Error while setting player name");
             }
        }
        return player;
    }

}
