package com.viseguardstudios.asteroid_miner.view.panels.main_menu;

import com.viseguardstudios.asteroid_miner.model.Engine;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu() {

        var layout = new FlowLayout();

        this.setLayout(layout);

        JButton newGame = new JButton("Start new game");
        this.add(newGame);
        newGame.addActionListener(e -> {
            //TODO add popup to set the player count or something
            Engine.getInstance().setPlayerCount(1);
            Engine.getInstance().StartGame((int)System.currentTimeMillis());
        });


        this.add(new JButton("Button 2"));


    }
}
