package com.viseguardstudios.asteroid_miner.map_loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOpener {
    File file;
    Scanner input;
    ArrayList<String> commands;

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
            commands.add(nextLine); 
        }
    }
    //getter for commands
    public ArrayList<String> getCommands() {
        return commands;
    }

    /**
     * Cuts off parts commented with '#'
     * @param input
     * @return
     */
    public static String cutComments(String input){
        return input.split("#")[0];

    }

    /**
     * Returns the split version of an object describer
     * @param input - "{type prop1="" prop2="" ... }"
     * @return {"type";"prop1=""";"prop2=""";}
     * @throws Exception
     */
    public static ArrayList<String> getNameAndPropsFromLine(String input) throws Exception {

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

    /**
     * Splits a property's name and value
     * @param input "prop1="x""
     * @return {"prop1";"x"}
     * @throws Exception
     */
    public static String[] getPropNameAndValue(String input) throws Exception {
        String[] temp = input.strip().split("=");
        try {
            if(temp.length!=2) throw new Exception();
            temp[1] = temp[1].split("\"")[1];  //Todo Check if this part works correctly (
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
