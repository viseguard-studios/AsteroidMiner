package com.viseguardstudios.asteroid_miner.skeleton.tests;

import com.viseguardstudios.asteroid_miner.model.Asteroid;
import com.viseguardstudios.asteroid_miner.model.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.item.Iron;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.STest;
import com.viseguardstudios.asteroid_miner.skeleton.Test;

import java.util.Scanner;

@STest
public class SSPlaceItemTest extends Test {
    private Asteroid a;
    private SpaceShip ss;
    private Item i;

    @Override
    public void Setup(Scanner sc) {
        a = new Asteroid();
        ss = new SpaceShip(a);
        i = new Iron();

        System.out.println("Would you like this spaceship hiding right now? [Y/N]");
        var answer = sc.nextLine();
        ss.setHidden(answer.equals("Y"));

        System.out.println("Enter a asteroid crust size(always enter a integer and bigger than 0!): ");
        answer = sc.nextLine();
        a.setCrustSize((Integer.parseInt(answer)));

        System.out.println("Would you like the asteroid exploded? [Y/N]");
        answer = sc.nextLine();
        a.setExploded(answer.equals("Y"));

    }

    @Override
    public void Run() {
        Logger.functionCalled("ss.PlaceItem(i)");
        ss.PlaceItem(i);
        Logger.returned();
    }
}
