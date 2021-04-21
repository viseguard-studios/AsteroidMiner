package com.viseguardstudios.asteroid_miner.commands;

import java.util.Scanner;

public abstract class Command {

    public abstract String getName();
    protected static Scanner scanner = new Scanner(System.in);

    public abstract void Execute(String[] params);

}
