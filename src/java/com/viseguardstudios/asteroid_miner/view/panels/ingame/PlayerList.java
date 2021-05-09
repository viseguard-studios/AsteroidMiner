package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.util.StateChangedListener;

import javax.swing.*;
import java.awt.*;

public class PlayerList extends JPanel implements StateChangedListener {

    public PlayerList() {

        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        this.setLayout(layout);

        doPlayerList();

    }

    public void doPlayerList(){
        var mg = Engine.getInstance().getScene().getManager();
        for (var p : mg.getAllPlayers()) {
            JLabel l = new JLabel();
            l.setText(p.getName());
            this.add(l);
        }
    }

    @Override
    public void stateChanged() {

    }
}
