package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.util.StateChangedListener;
import com.viseguardstudios.asteroid_miner.util.Vector2;
import com.viseguardstudios.asteroid_miner.view.KeyboardStateTable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapViewPanel extends JPanel implements StateChangedListener {

    Scene scene;

    Vector2 cameraPos = new Vector2(250,250);

    KeyboardStateTable keys = new KeyboardStateTable(); // Ádám előző projektből


    public MapViewPanel() {
        scene = Engine.getInstance().getScene();
        scene.getManager().addListener(this);

        this.addKeyListener(keys);

        try {
            asteroidImage = ImageIO.read(new File("assets\\graphics\\sprites\\ast.png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        this.setPreferredSize(new Dimension(700,700));
    }

    private static Image asteroidImage = null;



    @Override
    public void paint(Graphics g) {
        super.paint(g);

        moveCamera();

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
                //Aszteroida kirajzolása
                drawImage(g,asteroidImage,pos,10);
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
     * @param scale Skála
     */
    public void drawImage(Graphics g,Image img, Vector2 loc, float scale){
        Graphics2D g2D = (Graphics2D)g; //kasztolás
        //transzformációs mátrix
        AffineTransform matrix = new AffineTransform();
        float w = 20*scale;
        float h = 20*scale;

        Vector2 viewPos = loc.subtract(cameraPos);
        viewPos = viewPos.multiply((int)scale);

        float centerX = viewPos.getX()-w/2;
        float centerY = viewPos.getY()-h/2;

        matrix.translate(centerX,centerY);
        matrix.scale(scale,scale);

        g2D.drawImage(img,(int)centerX,(int)centerY,(int)w,(int)h, null);// kép kirajzolása
    }

    public void moveCamera(){ //TODO NEM KAPJUK MEG A KEY EVENTEKET!!!

        /*
         * Hasznalando karakterkodok: le - 40
         * 	fel: 38
         *  balra: 37
         *  jobbra: 39
         *  enter: 10
         */

        boolean[] keyStates = keys.getKeyStates();
        if(keyStates[39]){
            cameraPos.setX(cameraPos.getX()+10);
        }
        if(keyStates[37]){
            cameraPos.setX(cameraPos.getX()-10);
        }
        if(keyStates[40]){
            cameraPos.setX(cameraPos.getX()+10);
        }
        if(keyStates[38]){
            cameraPos.setX(cameraPos.getX()-10);
        }

    }
}
