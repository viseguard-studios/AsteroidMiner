package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import javax.swing.*;
import java.awt.*;

public class InGameGUI extends JPanel {


    public InGameGUI() {

        var insp = new SelectionInspectorPanel();
        var map = new MapViewPanel();
        var actions = new ActionBar();
        var pList = new PlayerList();

        var content = new JPanel();
        var horizontal = new BorderLayout();
        content.setLayout(horizontal);

        content.add(insp, BorderLayout.LINE_START);
        content.add(map, BorderLayout.CENTER);
        content.add(actions,BorderLayout.PAGE_END);
        content.add(pList,BorderLayout.PAGE_START);

        this.add(content);

    }
}
