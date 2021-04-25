package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import javax.swing.*;
import java.awt.*;

public class ActionBar extends JPanel {

    public ActionBar() {

        FlowLayout experimentLayout = new FlowLayout();

        this.setLayout(experimentLayout);

        this.add(new JButton("Drill"));
        this.add(new JButton("Mine"));
        this.add(new JButton("Hide"));
        this.add(new JButton("Move"));

    }
}
