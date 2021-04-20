package com.viseguardstudios.asteroid_miner.util;

public class Vector2 {

    int x;
    int y;


    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Vector2 pos) {
        return distance(this, pos);
    }

    public static Vector2 subtract(Vector2 left, Vector2 right)
    {
        return new Vector2(left.x- right.x,left.y- right.y);
    }

    public Vector2 subtract(Vector2 right)
    {
        return subtract(this, right);
    }

    public static double distance(Vector2 value1, Vector2 value2)
    {
        Vector2 difference = subtract(value1, value2);
        double ls = Vector2.dot(difference, difference);
        return Math.sqrt(ls);
    }

    public static double dot(Vector2 value1, Vector2 value2)
    {
        return value1.x * value2.x +
                value1.y * value2.y;
    }

    @Override
    public String toString() {
        return "{x:"+x+" y: "+y+"}";
    }
}
