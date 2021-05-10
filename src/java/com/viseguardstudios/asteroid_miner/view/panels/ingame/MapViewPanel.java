package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Entity;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;
import com.viseguardstudios.asteroid_miner.util.Sprite;
import com.viseguardstudios.asteroid_miner.util.StateChangedListener;
import com.viseguardstudios.asteroid_miner.util.Vector2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.IOException;

import static java.awt.event.KeyEvent.*;

public class MapViewPanel extends JPanel implements StateChangedListener {

    Scene scene;

    Vector2 cameraPos = new Vector2(250,250);
    float scale = 1;

    Vector2 viewPortSize = new Vector2(700,700);

    private static Sprite background = new Sprite("assets/graphics/sprites/background.png", 800);


    public MapViewPanel() {
        scene = Engine.getInstance().getScene();
        scene.getManager().addListener(this);

        this.setFocusable(true);
        this.requestFocus();
        this.requestFocusInWindow();

/*
        try {
            asteroidImage = ImageIO.read(new File("assets\\graphics\\sprites\\ast.png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
 */

        this.setPreferredSize(new Dimension(viewPortSize.getX(), viewPortSize.getY()));

        var mListerner = new FocusMouse(this);
        this.addMouseListener(mListerner);
        this.addMouseWheelListener(mListerner);

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

            var pos = new Vector2(e.getX(),e.getY());
            var wordPos = ReverseViewProject(pos);

            float minDist = Float.MAX_VALUE;
            Entity s = null;
            for (var ent : scene.getEntities()) {
                var entPos = ent.getPos();

                if (entPos != null ){
                    var distance = entPos.distance(wordPos);
                    var spriteSize = ent.getSprite().getSize();

                    if( distance < spriteSize/2f*scale && distance < minDist) {
                        minDist = distance;
                        s = ent;
                    }
                }
            }
            scene.getManager().setSelectedEntity(s);
            super.mouseClicked(e);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            //super.mouseWheelMoved(e);
            zoom((float) Math.pow(1.3f, -1* e.getWheelRotation()));
            a.repaint();
        }
    }

    //private static Image asteroidImage = null;


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //moveCamera();
        Graphics2D g2d = (Graphics2D) g.create();
        ((Graphics2D)g).setComposite(AlphaComposite.SrcOver);

        // g.setColor(new Color(40, 78, 95));

        var size = this.getSize();

        // g.fillRect(0,0, size.width, size.height);

        drawImage(g,background.getImg(),new Vector2(size.width/2,size.height/2),(int)(background.getSize()/scale));

        for (var ent : scene.getEntities()) {



            var pos = ent.getPos();
            if(pos != null) {

                var viewPos = ViewProject(pos);



                if(scene.getManager().getSelectedEntity() != ent){
                    g.setColor(Color.gray);
                }
                else {
                    g.setColor(Color.CYAN);
                    var s = (ent.getSprite().getSize()+3)*scale;
                    g.drawOval((int)(viewPos.getX()-s/2),(int)(viewPos.getY()-s/2), (int)s,(int)s);
                }


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

                }

                drawImage(g, ent.getSprite().getImg() , viewPos, ent.getSprite().getSize());


                if(ent instanceof Vessel) {
                    var p = Engine.getInstance().getScene().getManager().getCurrentPlayer();
                    if (((Vessel) ent).getOwner() == p) {
                        g.setColor(Color.green);
                        var s = (ent.getSprite().getSize())*scale;
                        g.drawRect((int)(viewPos.getX()-s/2),(int)(viewPos.getY()-s/2), (int)s,(int)s);
                    }
                }




            }
        }
        g2d.setPaint(new Color(255,255,0, 49));
        //g2d.setComposite(AlphaComposite.SrcOver.derive(0.2f));
        //((Graphics2D)g).set
        GameManager gameManager = Engine.getInstance().getGameManager();

        if(gameManager.IsSolarStormActive()){
            Vector2 center = ViewProject(gameManager.getSolarStormCenter());
            int rad = (int)(gameManager.getSolarStromRadius()*scale);

            g2d.fillOval(center.getX()-rad, center.getY()-rad,rad*2,rad*2);
        }
        //g2d.setComposite(AlphaComposite.SrcOver);

    }

    @Override
    public void stateChanged() {
        this.repaint();
    }

    /**
     * Kép kirajzolása az adott helyen.
     * @param g Graphics osztály
     * @param img Kép
     * @param loc Pozíció(képernyő px, közép)
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

    private Vector2 ReverseViewProject(Vector2 loc) {
        var a = loc.divide(scale);
        a = a.add(cameraPos.subtract(viewPortSize.multiply(0.5f)));
        return a;
        /*
        Vector2 viewPos = loc.subtract(cameraPos.subtract(viewPortSize.multiply(0.5f)));
        viewPos = viewPos.multiply(scale);
        return viewPos;
        */
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        if(e.getID() == KEY_PRESSED) {
            updateCamera(e);
        }
    }

    public void updateCamera(KeyEvent e){

        boolean changed = false;

        Dimension mapSize = this.getSize();

        //Legyen minimum!
        int movement = ((30/scale) > 2) ? (int)(30/scale) : 2;

        int keyStates = e.getKeyCode();
        if(keyStates == VK_D|| keyStates == VK_RIGHT){
            cameraPos.setX((cameraPos.getX()+movement));
            changed = true;
        }
        if(keyStates == VK_A || keyStates == VK_LEFT){
            cameraPos.setX((cameraPos.getX()-movement));
            changed = true;
        }
        if(keyStates == VK_S || keyStates == VK_DOWN){
            cameraPos.setY((cameraPos.getY()+movement));
            changed = true;
        }
        if(keyStates == VK_W || keyStates == VK_UP){
            cameraPos.setY((cameraPos.getY()-movement));
            changed = true;
        }
        if(keyStates == VK_Q){

            zoom(1.3f);

            changed = true;
        }
        if(keyStates == VK_E){

            zoom(1/1.3f);

            changed = true;
        }

        if(changed){
            this.repaint();
        }

    }

    public void zoom(float amount){
        Dimension mapSize = this.getSize();
        int camX = cameraPos.getX();
        int camY = cameraPos.getY();

        int camCenterX = cameraPos.getX()+(int)(mapSize.width/2/scale);
        int camCenterY = cameraPos.getY()+(int)(mapSize.height/2/scale);

        scale *= amount;

        scale = (scale<0.1)? 0.1f : scale;
        scale = (scale>10.0)? 10.0f : scale;

        camX = camCenterX - (int)(mapSize.width/2/scale);
        camY = camCenterY -(int)(mapSize.height/2/scale);


        cameraPos.setX((camX));
        cameraPos.setY((camY));
    }


}
