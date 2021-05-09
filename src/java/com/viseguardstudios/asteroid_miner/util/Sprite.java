package com.viseguardstudios.asteroid_miner.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sprite {

    private BufferedImage img = null;

    private int size;

    public Sprite(String path, int size) {
        this.size = size;

        try {
            File f = null;

            ClassLoader classLoader = getClass().getClassLoader();

            URL resource = classLoader.getResource(path);
            var resource_stream = classLoader.getResourceAsStream(path);
            if (resource == null) {
                throw new IllegalArgumentException("file is not found!");
            }

            f = new File(resource.getFile());

            if(!f.exists()){
                throw new IllegalArgumentException("file is not found!");
            }

            img = ImageIO.read(resource_stream);
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
