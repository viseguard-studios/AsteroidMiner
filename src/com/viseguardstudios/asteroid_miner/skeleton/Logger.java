package com.viseguardstudios.asteroid_miner.skeleton;

/**
 * A loggert megvalosito osztaly kiirja standard inputra a fuggvenyhivasokat
 */
public class Logger {

    /**
     * Be van-e kapcsolva
     */
    public static boolean enabled = false;

    /**
     * A behuzasmerete
     */
    private static int depth = -1;
    
    public static void setEnabled(boolean e){
        enabled = e;
    }

    /** A fuggveny megmutatja a behuzas merteket
     * @return a behuzas merteke
     */
    public static int getDepth() {
        return depth;
    }

    /** A fuggveny beallitja a behuzas merteket
     * @param dep a behuzas merteke
     */
    public static void setDepth(int dep) {
        depth = dep;
    }
    public static void returned(){
        depth--;
    }

    /** A fuggveny a parameterben kapott uzenetet a standard outputra tovabbitja
     * @param message az uzenet
     */
    public static void functionCalled(String message) {
        if(enabled) {
            for (int i = 0; i < depth; i++)
                System.out.print("\t");
            System.out.println(message);
            depth++;
        }
    }

    public static void log(String message) {
        if(enabled) {
            for (int i = 0; i < depth; i++)
                System.out.print("\t");
            System.out.println(message);
        }
    }
}