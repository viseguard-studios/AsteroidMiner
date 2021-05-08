package com.viseguardstudios.asteroid_miner.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Sprite {

    private Image img = null;

    private int size;

    public Sprite(String path, int size) {
        this.size = size;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }


    public Image getImg(){
        return img;
    }

    public int getSize() {
        return size;
    }
}
