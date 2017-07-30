package com.samuel.invaders;

import com.samuel.invaders.builders.InvaderBuilder;
import com.samuel.invaders.models.Invader;
import com.samuel.invaders.models.MatchInvader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class InvadersApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvadersApplication.class, args);
        discoverSpaceInvaders(args);
    }

    private static void discoverSpaceInvaders(String[] args) {
        if (args.length < 2){
            System.out.println(">>>>>>>>>> Two arguments are required -> 85 /home/user/radar.txt <<<<<<<<<<");
            return;
        }
        double certainty = Double.parseDouble(args[0])/100;
        String mapPath = args[1];

        FileHelper fileHelper = new FileHelper();
        InvaderBuilder invaderBuilder = new InvaderBuilder(fileHelper);
        List<Invader> invaders = new ArrayList<Invader>();
        invaders.add(getCrab());
        invaders.add(getSquid());
        List<String> map = fileHelper.getDataFromPath(mapPath);
        ScannerInvader scannerInvader = new ScannerInvader(invaderBuilder);

        List<MatchInvader> matchInvaders = scannerInvader.scan(certainty, invaders, map);
        System.out.println( matchInvaders );
        System.out.println("Total: " + matchInvaders.size() + " invaders with " + certainty * 100 + "%");
    }

    private static Invader getCrab(){
        List<String> bodyCrab = new ArrayList<>();
        bodyCrab.add("--o-----o--");
        bodyCrab.add("---o---o---");
        bodyCrab.add("--ooooooo--");
        bodyCrab.add("-oo-ooo-oo-");
        bodyCrab.add("ooooooooooo");
        bodyCrab.add("o-ooooooo-o");
        bodyCrab.add("o-o-----o-o");
        bodyCrab.add("---oo-oo---");
        return new Invader("crab",bodyCrab);
    }

    private static Invader getSquid(){
        List<String> bodySquid = new ArrayList<>();
        bodySquid.add("---oo---");
        bodySquid.add("--oooo--");
        bodySquid.add("-oooooo-");
        bodySquid.add("oo-oo-oo");
        bodySquid.add("oooooooo");
        bodySquid.add("--o--o--");
        bodySquid.add("-o-oo-o-");
        bodySquid.add("o-o--o-o");
        return new Invader("squid",bodySquid);
    }

}
