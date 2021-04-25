package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.util.StateChangedListener;

import javax.swing.*;
import java.awt.*;

public class MapViewPanel extends JPanel implements StateChangedListener {

    Scene scene;

    public MapViewPanel() {
        scene = Engine.getInstance().getScene();

        this.setPreferredSize(new Dimension(700,700));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(new Color(40, 78, 95));

        var size = this.getSize();

        g.fillRect(0,0, size.width, size.height);

        for (var ent : scene.getEntities()) {
            var pos = ent.getPos();
            if(pos != null) {
                g.setColor(Color.CYAN);
                g.fillOval(pos.getX(), pos.getY(), 10, 10);
            }
        }

    }

    @Override
    public void stateChanged() {
        this.repaint();
    }
}
