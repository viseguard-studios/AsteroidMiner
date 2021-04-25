package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import javax.swing.*;
import java.awt.*;

public class InGameGUI extends JPanel {


    public InGameGUI() {

        var insp = new SelectionInspectorPanel();
        MapViewPanel map = new MapViewPanel();

        var content = new JPanel();
        var horizontal = new BorderLayout();
        content.setLayout(horizontal);

        content.add(insp, BorderLayout.LINE_START);
        content.add(map, BorderLayout.CENTER);

        this.add(content);

    }
}
