package com.viseguardstudios.asteroid_miner.commands;

public abstract class Command {

    public abstract String getName();

    public abstract void Execute(String[] params);

}
