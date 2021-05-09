package com.viseguardstudios.asteroid_miner.skeleton.tests;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.SpaceShip;
import com.viseguardstudios.asteroid_miner.model.item.resource.Iron;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;
import com.viseguardstudios.asteroid_miner.skeleton.STest;
import com.viseguardstudios.asteroid_miner.skeleton.Test;

import java.util.Scanner;

@STest
public class SSHideTest extends Test {
    private Asteroid a;
    private SpaceShip ss;
    private SpaceShip ss2;

    @Override
    public void Setup(Scanner sc) {
        a = new Asteroid();
        ss = new SpaceShip(a);
        ss2 = new SpaceShip(a);

        System.out.println("Should this SpaceShip be already hidden? [Y/N]");
        var answer = sc.nextLine();
        ss.setHidden(answer.equals("Y"));

        System.out.println("Would you like to set the natural resource amount to 0? [Y/N]");
        answer = sc.nextLine();
        if(!answer.equals("Y")) {
            Iron is = new Iron();
            //is.setAmount(1);
            //a.setResource(is);
        }

        System.out.println("Would you like to set the asteroid crust size to 0? [Y/N]");
        answer = sc.nextLine();
        if(answer.equals("Y")) a.setCrustSize(0);
        else a.setCrustSize(1);

        System.out.println("Would you like the asteroid to be exploded? [Y/N]");
        answer = sc.nextLine();
        a.setExploded(answer.equals("Y"));

        System.out.println("Would you like someone else hiding in the asteroid right now? [Y/N]");
        answer = sc.nextLine();
        if(answer.equals("Y")){
            a.setHidingVessel(ss2);
        }

    }

    @Override
    public void Run() {
        Logger.functionCalled("ss.Hide()");
        ss.Hide();
        Logger.returned();
    }
}
