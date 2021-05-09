package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import javax.swing.*;
import java.util.List;

public class ChoosePanel extends JInternalFrame {

    String[] list;

    public ChoosePanel(String title, boolean resizable, boolean closable, boolean maximizable) {
        super(title, resizable, closable, maximizable);
    }

    public ChoosePanel(String[] list) {
        super("Please choose from the list:",false,false,false);
        this.list = list;
    }


}
