package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import javax.swing.*;
import java.awt.*;

public class EndGame extends JPanel {


    public EndGame(boolean win) {
        var layout = new GridLayout(2,1);
        this.setLayout(layout);
        JPanel titlePanel = new JPanel(new FlowLayout());

        titlePanel.add(Box.createHorizontalStrut(30));
        JLabel title;
        if(win) {
            title = new JLabel("Congratulation! You Won!", JLabel.CENTER);
        }
        else {
            title = new JLabel("Sorry, you lost this time!", JLabel.CENTER);
        }
        title.setFont(new Font("font", Font.BOLD+ Font.ITALIC, 54));
        titlePanel.add(title);

        titlePanel.add(Box.createHorizontalStrut(30));
        this.add(titlePanel);

        JPanel actions = new JPanel(new FlowLayout());

        JButton okay = new JButton("Exit");
        actions.add(okay);
        okay.addActionListener(e -> {
            System.exit(0);
        });

        this.add(actions);
    }

}
