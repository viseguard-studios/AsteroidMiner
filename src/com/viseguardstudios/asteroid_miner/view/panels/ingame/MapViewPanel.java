package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.util.StateChangedListener;

import javax.swing.*;
import java.awt.*;

public class MapViewPanel extends JPanel implements StateChangedListener {

    Scene scene;

    public MapViewPanel() {
        scene = Engine.getInstance().getScene();
        scene.getManager().addListener(this);

        this.setPreferredSize(new Dimension(700,700));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(new Color(40, 78, 95));

        var size = this.getSize();

        g.fillRect(0,0, size.width, size.height);

        for (var ent : scene.getEntities()) {
            if(scene.getManager().getSelectedEntity() != ent){
                g.setColor(Color.gray);
            }
            else {
                g.setColor(Color.CYAN);
            }


            var pos = ent.getPos();

            if(pos != null) {

                g.fillOval(pos.getX()-5, pos.getY()-5, 10, 10);
            }
            if(ent instanceof Asteroid){
                Asteroid a = (Asteroid) ent;
                var neir = a.getPhysicalNeighbours();
                for (var n : neir) {
                    if(n != scene.getManager().getSelectedEntity()) {
                        var end = n.getPos();
                        g.drawLine(pos.getX(), pos.getY(), end.getX(), end.getY());
                    }
                }

            }
        }

    }

    @Override
    public void stateChanged() {
        this.repaint();
    }
}
