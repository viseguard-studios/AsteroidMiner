package com.viseguardstudios.asteroid_miner.map_loader;


import com.viseguardstudios.asteroid_miner.model.GameManager;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.Scene;
import jdk.jfr.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileOpener {
    private static File file;
    private static Scanner input;
    private static ArrayList<String> commands;

    public static Scene scene = new Scene();
    public static GameManager manager = new GameManager();

    @Label("Important")
    /**
     * Fájl beolvasás előkészítése, FILEOPENER HASZNÁLATA ELŐTT FUTTATNI KELL
     */
    public static void build(String filePath) {
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
        String s = input.split("#")[0];
        return s;

    }

    /**
     * Returns the split version of an object describer
     * @param input - "{type prop1="" prop2="" ... }"
     * @return {"type";"prop1=""";"prop2=""";}
     * @throws Exception
     */
    public static ArrayList<String> getTypeAndPropsFromLine(String input) throws Exception {

        ArrayList<String> found = new ArrayList<>();

        if(input.isBlank() || input.isEmpty()){
            return found;
        }

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
     */
    protected static String[] getPropNameAndValue(String input){
        String[] temp = input.strip().toLowerCase().split("=");
        try {
            if(temp.length!=2) throw new Exception("Bad format in object property");
            if(!(temp[1].isEmpty() || temp[1].isBlank()) && temp[1].contains("\"") )
            temp[1] = temp[1].split("\"")[1];  //Todo Check if this part works correctly (
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Megkeresi, hogy tartalmazza-e az adott leíró a tulajdonságot
     * @param args
     * @param searched
     * @return -1, ha nincs találat, egyébként az első talált index.
     */
    protected static int getPropIndex(ArrayList<String> args, String searched){
        boolean found = false;
        int firstIndex = -1;
        for (int i = 0; i<args.size(); i++){
            var token = args.get(i);
            if(!token.contains("="))
                continue;
            try {
                String parameterName = getPropNameAndValue(args.get(i))[0];
                if(parameterName.equals(searched)){
                    firstIndex = i;
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return firstIndex;
    }

    /**
     * Megkeresi és visszaadja az adott tulajdonság értékét
     * @param rawLine
     * @param searched
     * @return null, ha nincs találat, egyébként az érték stringje
     */
    public static String getPropValue(String rawLine, String searched){
        String value = null;
        searched = searched.toLowerCase(); // a biztonság kedvéért
        ArrayList<String> args = prepareLine(rawLine);
        int index = getPropIndex(args,searched);
        if(index>-1){
            try {
                value = args.get(index);
                value = getPropNameAndValue(value)[1];
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * Visszaadja a sorban található objektum típusát
     * @param rawLine
     * @return
     */
    public static String getObjType(String rawLine){
        if(prepareLine(rawLine).isEmpty())
            return "";
        String type = prepareLine(rawLine).get(0).toLowerCase(); //toLowerCase a biztonság kedvéért
        return type;
    }


    /**
     * Bemeneti sor előkészítése a feldolgozáshoz.
     * @param rawLine
     * @return
     */
    private static ArrayList<String> prepareLine(String rawLine){
        ArrayList<String> value = null;
        try {
            value = getTypeAndPropsFromLine(cutComments((rawLine)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Igaz-hamis változók szövegből való átalakítása
     * @param string
     * @return
     * @throws Exception
     */
    public static boolean getBoolValue(String string) throws Exception {
        string = string.toLowerCase();
        // pozitív érték
        if(string.equals("1")) return true; //extra
        if(string.equals("y")) return true;
        if(string.equals("yes")) return true;
        if(string.equals("t")) return true;
        if(string.equals("true")) return true;
        // negatív érték
        if(string.equals("0")) return false; //extra
        if(string.equals("n")) return false;
        if(string.equals("no")) return false;
        if(string.equals("f")) return false;
        if(string.equals("false")) return false;
        // ha egyik sem
        throw new Exception("Invalid value for boolean property value.");
    }

    /**
     * Megkeresi az első adott névnek megfelelő gyermeket az adott sorokban.
     * @param inputLines
     * @param name
     * @param startLine Melyik eltolástól nézve kezdjünk.
     * @return Melyik sorban található a gyermek, és a lezáró. -1 -el tér vissza egyébként.
     * @throws Exception
     */
    public static int[] getChildLoc(ArrayList<String> inputLines, String name, int startLine) throws Exception {
        if (startLine>=inputLines.size()) throw new Exception("Starting line number exceeds input length.");
        int[] ids = {-1,-1}; // kezdo index es befejezo index

        name = name.toLowerCase();
        for (int i = startLine; i<inputLines.size();i++){
            String currentLine = inputLines.get(i);
            if(!currentLine.trim().isEmpty())
                if(getObjType(currentLine).equals(name)){
                    ids[0] = i;
                    break;
                }
        }
        if(ids[0] == -1) {
            return ids;
        }

        String endMarker = "/"+name;
        for (int i = ids[0]; i<inputLines.size();i++){
            String currentLine = inputLines.get(i);
            if(!currentLine.trim().isEmpty())
                if(getObjType(currentLine).equals(endMarker)){
                    ids[1] = i;
                    break;
                }
        }
        return ids;
    }

    @Override
    protected void finalize() throws Throwable {
        input.close();
        super.finalize();
    }
}
