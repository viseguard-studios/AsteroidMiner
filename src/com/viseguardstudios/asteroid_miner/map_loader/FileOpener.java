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

    /**
     * Cuts off parts commented with '#'
     * @param input
     * @return
     */
    public  String cutComments(String input){
        return input.split("#")[0];

    }

    public ArrayList<String> getNameAndPropsFromLine(String input) throws Exception {

        ArrayList<String> found = new ArrayList<>();

        try {
            String temp = input.split("\\{")[1];
            temp = temp.split("\\}")[0];
            String[] foundParams = temp.strip().split(" ");
            for (int i = 0; i < foundParams.length; i++){
                if (!foundParams[i].isEmpty()) {
                    found.add(foundParams[i]);
                }
            }
        }
        catch (Exception e)
        {
            throw new Exception("Bad format in object describer");
        }

        return found;
    }

    public String[] getPropNameAndValue(String input) throws Exception {
        String[] temp = input.strip().split("=");
        try {
            temp[1] = temp[1].split("\"")[1];
        }
        catch (Exception e)
        {
            throw new Exception("Bad format in object property");
        }
        return temp;
    }

    @Override
    protected void finalize() throws Throwable {
        input.close();
        super.finalize();
    }
}
