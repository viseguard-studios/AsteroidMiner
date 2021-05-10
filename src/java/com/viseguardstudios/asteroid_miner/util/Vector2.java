package com.viseguardstudios.asteroid_miner.util;

public class Vector2 {

    int x;
    int y;


    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float distance(Vector2 pos) {
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

    public static Vector2 subtract(Vector2 left, int right)
    {
        return new Vector2(left.x- right,left.y- right);
    }

    public Vector2 subtract(int right)
    {
        return subtract(this, right);
    }

    public static Vector2 add(Vector2 left, Vector2 right)
    {
        return new Vector2(left.x+ right.x,left.y+ right.y);
    }

    public Vector2 add(Vector2 right)
    {
        return add(this, right);
    }

    public static Vector2 add(Vector2 left, int right)
    {
        return new Vector2(left.x+ right,left.y+ right);
    }

    public Vector2 add(int right)
    {
        return add(this, right);
    }

    public Vector2 multiply(Vector2 right) {return new Vector2(this.x*right.x,this.y*right.y); }

    public Vector2 multiply(int right) {return new Vector2(this.x*right,this.y*right); }

    public Vector2 multiply(float right) {
        int new_x = (int)Math.floor(this.x*right);
        int new_y = (int)Math.floor(this.y*right);

        return new Vector2(new_x,new_y);
    }

    public Vector2 divide(float right) {
        int new_x = (int)Math.floor(this.x/right);
        int new_y = (int)Math.floor(this.y/right);

        return new Vector2(new_x,new_y);
    }


    public static float distance(Vector2 value1, Vector2 value2)
    {
        Vector2 difference = subtract(value1, value2);
        float ls = Vector2.dot(difference, difference);
        return (float) Math.sqrt(ls);
    }

    public static float dot(Vector2 value1, Vector2 value2)
    {
        return value1.x * value2.x +
                value1.y * value2.y;
    }


    public static float angle(Vector2 a, Vector2 b){
        return (float) Math.atan2((double) b.y - a.y,(double) b.x - a.x);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{x:"+x+" y: "+y+"}";
    }
}
