package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.util.StateChangedListener;
import com.viseguardstudios.asteroid_miner.util.Vector2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import static java.awt.event.KeyEvent.*;

public class MapViewPanel extends JPanel implements StateChangedListener {

    Scene scene;

    Vector2 cameraPos = new Vector2(250,250);
    float scale = 1;

    Vector2 viewPortSize = new Vector2(700,700);


    public MapViewPanel() {
        scene = Engine.getInstance().getScene();
        scene.getManager().addListener(this);

        this.setFocusable(true);
        this.requestFocus();
        this.requestFocusInWindow();


        try {
            asteroidImage = ImageIO.read(new File("assets\\graphics\\sprites\\ast.png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        this.setPreferredSize(new Dimension(viewPortSize.getX(), viewPortSize.getY()));

        this.addMouseListener(new FocusMouse(this));

    }

    private class FocusMouse extends MouseAdapter
    {
        JPanel a;

        public FocusMouse(JPanel a) {
            this.a = a;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            a.requestFocusInWindow();
            super.mouseClicked(e);
        }
    }

    private static Image asteroidImage = null;


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //moveCamera();

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
                var viewPos = ViewProject(pos);


                if (ent instanceof Asteroid) {
                    Asteroid a = (Asteroid) ent;
                    var neir = a.getPhysicalNeighbours();

                    for (var n : neir) {
                        if (n != scene.getManager().getSelectedEntity()) {
                            var end = ViewProject(n.getPos());
                            g.drawLine(viewPos.getX(), viewPos.getY(), end.getX(), end.getY());
                        }
                    }
                    //Aszteroida kirajzolása
                    drawImage(g, asteroidImage, viewPos, 20);
                }
            }
        }

    }

    @Override
    public void stateChanged() {
        this.repaint();
    }

    /**
     * Kép kirajzolása az adott helyen.
     * @param g Graphics osztály
     * @param img Kép
     * @param loc Pozíció(képernyő px)
     */
    public void drawImage(Graphics g,Image img, Vector2 loc, int size){
        Graphics2D g2D = (Graphics2D)g; //kasztolás

        float w = size*scale;
        float h = size*scale;

        Vector2 viewPos = loc;

        float centerX = viewPos.getX()- w/2;
        float centerY = viewPos.getY()- h/2;

        g2D.drawImage(img,(int)centerX,(int)centerY,(int)w,(int)h, null);// kép kirajzolása
    }

    private Vector2 ViewProject(Vector2 loc) {
        Vector2 viewPos = loc.subtract(cameraPos.subtract(viewPortSize.multiply(0.5f)));
        viewPos = viewPos.multiply(scale);
        return viewPos;
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        if(e.getID() == KEY_PRESSED) {
            updateCamera(e);
        }
    }

    public void updateCamera(KeyEvent e){ //TODO NEM KAPJUK MEG A KEY EVENTEKET!!!

        /*
         * Hasznalando karakterkodok: le - 40
         * 	fel: 38
         *  balra: 37
         *  jobbra: 39
         *  enter: 10
         *
         *  A : 97
         *  W : 119
         *  S : 115
         *  D : 100
         */
        boolean changed = false;

        int keyStates = e.getKeyCode();
        if(keyStates == VK_D){
            cameraPos.setX(cameraPos.getX()+10);
            changed = true;
        }
        if(keyStates == VK_A){
            cameraPos.setX(cameraPos.getX()-10);
            changed = true;
        }
        if(keyStates == VK_W){
            cameraPos.setY(cameraPos.getY()+10);
            changed = true;
        }
        if(keyStates == VK_S){
            cameraPos.setY(cameraPos.getY()-10);
            changed = true;
        }
        if(keyStates == VK_Q){
            scale += 0.01;
            changed = true;
        }
        if(keyStates == VK_E){
            scale -= 0.01;
            changed = true;
        }

        if(changed){
            this.repaint();
        }

    }


}
