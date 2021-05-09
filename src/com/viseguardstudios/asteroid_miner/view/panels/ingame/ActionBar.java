package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ActionBar extends JPanel {

    public ActionBar() {

        FlowLayout experimentLayout = new FlowLayout();

        this.setLayout(experimentLayout);

        JButton moveBtn = new JButton("Move");
        moveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpaceShip ss = (SpaceShip)(Engine.getInstance().getScene().getManager().getSelectedEntity()); //selection must be SS to work
                List<Asteroid> asteroids = ss.getCurrentAsteroid().getPhysicalNeighbours();
                List<String> posib =  new ArrayList<>();
                for (Asteroid a: asteroids
                     ) {
                    posib.add(a.getName());
                }

                JOptionPane.showInputDialog(ActionBar.this.getParent().getParent(),"Please choose a destination: ","Choose",JOptionPane.PLAIN_MESSAGE,null,posib.toArray(),"test1");
            }
        });

        this.add(moveBtn);
        this.add(new JButton("Mine"));
        this.add(new JButton("Hide"));
        this.add(new JButton("Drill"));

    }
}
