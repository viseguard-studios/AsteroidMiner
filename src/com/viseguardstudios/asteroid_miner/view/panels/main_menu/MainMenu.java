package com.viseguardstudios.asteroid_miner.view.panels.main_menu;

import com.viseguardstudios.asteroid_miner.model.Engine;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu() {

        var layout = new GridLayout(4,1);
        this.setLayout(layout);

        this.add(new JPanel());

        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(Box.createHorizontalStrut(30));

        JLabel title = new JLabel("Asteroid Miner", JLabel.CENTER);
        title.setFont(new Font("font", Font.BOLD+ Font.ITALIC, 54));
        titlePanel.add(title);

        titlePanel.add(Box.createHorizontalStrut(30));
        this.add(titlePanel);

        JPanel actions = new JPanel(new FlowLayout());

        JButton newGame = new JButton("Start new game");
        actions.add(newGame);
        newGame.addActionListener(e -> {
            //TODO add popup to set the player count or something
            Engine.getInstance().setPlayerCount(1);
            Engine.getInstance().StartGame((int)System.currentTimeMillis());
        });

        actions.add(Box.createHorizontalStrut(10));
        actions.add(new JButton("    Load game     "));
        actions.add(Box.createHorizontalStrut(10));
        actions.add(new JButton("        Exit        "));

        this.add(new JPanel());
        this.add(actions);

    }
}
