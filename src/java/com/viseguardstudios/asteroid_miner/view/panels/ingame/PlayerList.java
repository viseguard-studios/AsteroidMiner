package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.util.StateChangedListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerList extends JPanel implements StateChangedListener {

    ArrayList<JLabel> playerLabels = new ArrayList<>();

    JLabel sunDistance;

    public PlayerList() {
        Engine.getInstance().getScene().getManager().addListener(this);


        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        this.setLayout(layout);

        doPlayerList();

        JButton next = new JButton("End Turn");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Engine.getInstance().getScene().getManager().nextPlayer();
            }
        });

        this.add(next);

        sunDistance = new JLabel();

        this.add(sunDistance);

    }

    public void doPlayerList(){
        var mg = Engine.getInstance().getScene().getManager();
        for (var p : mg.getAllPlayers()) {
            JLabel l = new JLabel();
            l.setText(p.getName());
            l.setOpaque(true);
            this.add(l);
            playerLabels.add(l);
        }
    }

    @Override
    public void stateChanged() {
        GameManager manager = Engine.getInstance().getScene().getManager();
        var players = manager.getAllPlayers();
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (p == manager.getCurrentPlayer()) {
                playerLabels.get(i).setBackground(Color.CYAN);
            }
            else {
                playerLabels.get(i).setBackground(Color.gray);
            }
            playerLabels.get(i).repaint();
        }

        sunDistance.setText("Sun distance:" + manager.getSunDistance());
        if(manager.isColseToSun()){
            sunDistance.setBackground(Color.RED);
        }
        else {
            sunDistance.setBackground(Color.YELLOW);
        }

        this.repaint();
    }
}
