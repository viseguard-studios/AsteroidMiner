package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Entity;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.util.PositionChangeListener;
import com.viseguardstudios.asteroid_miner.util.StateChangedListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ActionBar extends JPanel implements StateChangedListener {

    Scene scene;
    Entity selected = null;
    private List<PositionChangeListener> listeners = new ArrayList<>();

    List<JComponent> buttons = new ArrayList<>();

    public ActionBar() {
        scene = Engine.getInstance().getScene();
        scene.getManager().addListener(this);

        FlowLayout experimentLayout = new FlowLayout();

        this.setPreferredSize(new Dimension(0,50));

        this.setLayout(experimentLayout);

        /*
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
         */

    }

    public void DrawButtons(){
        //remove old ones
        for (var b : buttons) {
            this.remove(b);
        }

        //add new ones
        var actions = selected.getActions();
        if(actions != null) {
            for (var a : actions) {
                JButton moveBtn = new JButton(a);
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
                        ss.doAction(new String[]{(String)JOptionPane.showInputDialog(ActionBar.this.getParent().getParent(),"Please choose a destination: ","Choose",JOptionPane.PLAIN_MESSAGE,null,posib.toArray(),"")});
                        ActionBar.this.getParent().repaint();
                    }
                });
                this.add(moveBtn);
                buttons.add(moveBtn);
            }
        }

        this.repaint();
    }

    @Override
    public void stateChanged() {
        if(selected != scene.getManager().getSelectedEntity()){
            selected = scene.getManager().getSelectedEntity();
            DrawButtons();
        }
    }
}
