package com.viseguardstudios.asteroid_miner.map_loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOpener {
    File file;
    Scanner input;
    ArrayList<String[]> commands;

    public FileOpener(String filePath) {
        commands = new ArrayList<>();
        file = new File(filePath);
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //loads and parse files to make commands (heavily rely on the correction of the user input)
    public void loadFile() {
        while (input.hasNext()) {
            String nextLine = input.nextLine();
            commands.add(nextLine.split(" "));
        }
    }
    //getter for commands
    public ArrayList<String[]> getCommands() {
        return commands;
    }

    @Override
    protected void finalize() throws Throwable {
        input.close();
        super.finalize();
    }
}
