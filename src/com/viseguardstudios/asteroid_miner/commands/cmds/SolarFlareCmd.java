package com.viseguardstudios.asteroid_miner.commands.cmds;

import com.viseguardstudios.asteroid_miner.commands.Command;
import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.util.Vector2;

public class SolarFlareCmd extends Command {
    @Override
    public String getName() {
        return "solarFlare";
    }

    @Override
    public void Execute(String[] params) {
    int x = Integer.parseInt(params[1]); //X koor
    int y = Integer.parseInt(params[2]); //Y koor
    int rad = Integer.parseInt(params[3]); //sugár
    GameManager current = Engine.getInstance().getScene().getManager();
    current.QueueSolarStorm();
    current.setSolarStormCenter(new Vector2(x,y));
    current.setSolarStromRadius(rad);
    System.out.println("A solar flare will happen at "+ x +","+ y +" with radius"+rad);
    }
}
