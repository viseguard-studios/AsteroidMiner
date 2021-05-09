package com.viseguardstudios.asteroid_miner.commands;

import com.viseguardstudios.asteroid_miner.model.Engine;

import java.util.Scanner;

public abstract class Command {

    public abstract String getName();

    public abstract void Execute(String[] params);

}
