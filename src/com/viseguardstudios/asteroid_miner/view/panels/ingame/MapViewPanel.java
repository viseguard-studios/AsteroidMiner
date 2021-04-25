package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.util.StateChangedListener;

import javax.swing.*;
import java.awt.*;

public class MapViewPanel extends JPanel implements StateChangedListener {


    public MapViewPanel() {
        this.setPreferredSize(new Dimension(500,500));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(new Color(40, 78, 95));

        var size = this.getSize();

        g.fillRect(0,0, size.width, size.height);

    }

    @Override
    public void stateChanged() {
        this.repaint();
    }
}
