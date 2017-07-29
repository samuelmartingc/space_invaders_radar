package com.samuel.invaders;

import com.samuel.invaders.builders.InvaderBuilder;
import com.samuel.invaders.models.Invader;
import com.samuel.invaders.models.MatchInvader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScannerInvaderTest {

    private InvaderBuilder invaderBuilder;
    private List<String> crab;
    private List<String> mapWithCrab;
    private List<String> mapWithUpperCrab;
    private List<String> mapWithLowerCrab;
    private ScannerInvader scannerInvader;
    private double certainty = 0.99;
    private Invader crabInvader;
    private String crabName = "crabName";

    @Before
    public void init(){
        fillCrab();
        fillMapWithCrab();
        fillMapWithUpperCrab();
        fillMapWithLowerCrab();

        FileHelper fileHelper = mock(FileHelper.class);
        invaderBuilder = new InvaderBuilder(fileHelper);
        scannerInvader = new ScannerInvader(invaderBuilder);
    }

    @Test
    public void scanOK() {
        List<Invader> invaders = new ArrayList<>();
        invaders.add(crabInvader);
        List<MatchInvader> matchInvaders = scannerInvader.scan(certainty,invaders,mapWithCrab);

        assertEquals(matchInvaders.size(),1);
        assertEquals(matchInvaders.get(0).getName(),crabName);
    }

    @Test
    public void scanMultipleInvadersOK() {
        List<Invader> invaders = new ArrayList<>();
        invaders.add(crabInvader);
        invaders.add(crabInvader);
        List<MatchInvader> matchInvaders = scannerInvader.scan(certainty,invaders,mapWithCrab);

        assertEquals(matchInvaders.size(),2);
        assertEquals(matchInvaders.get(0).getName(),crabName);
    }

    @Test
    public void scanUpperOK() {
        List<Invader> invaders = new ArrayList<>();
        invaders.add(crabInvader);
        List<MatchInvader> matchInvaders = scannerInvader.scan(certainty,invaders,mapWithUpperCrab);

        assertEquals(matchInvaders.size(),1);
        assertEquals(matchInvaders.get(0).getName(),"partial_"+crabName);
    }

    @Test
    public void scanLowerOK() {
        List<Invader> invaders = new ArrayList<>();
        invaders.add(crabInvader);
        List<MatchInvader> matchInvaders = scannerInvader.scan(certainty,invaders,mapWithLowerCrab);

        assertEquals(matchInvaders.size(),1);
        assertEquals(matchInvaders.get(0).getName(),"partial_"+crabName);
    }

    private void fillCrab(){
        crab = new ArrayList<>();
        crab.add("--o-----o--");
        crab.add("---o---o---");
        crab.add("--ooooooo--");
        crab.add("-oo-ooo-oo-");
        crab.add("ooooooooooo");
        crab.add("o-ooooooo-o");
        crab.add("o-o-----o-o");
        crab.add("---oo-oo---");
        crabInvader = new Invader(crabName,crab);
    }

    private void fillMapWithCrab(){
        mapWithCrab = new ArrayList<>();
        mapWithCrab.add("ooooooooooooooooooo");
        mapWithCrab.add("oooo--o-----o--oooo");
        mapWithCrab.add("oooo---o---o---oooo");
        mapWithCrab.add("oooo--ooooooo--oooo");
        mapWithCrab.add("oooo-oo-ooo-oo-oooo");
        mapWithCrab.add("ooooooooooooooooooo");
        mapWithCrab.add("ooooo-ooooooo-ooooo");
        mapWithCrab.add("ooooo-o-----o-ooooo");
        mapWithCrab.add("oooo---oo-oo---oooo");
        mapWithCrab.add("ooooooooooooooooooo");
    }

    private void fillMapWithUpperCrab(){
        mapWithUpperCrab = new ArrayList<>();
        mapWithUpperCrab.add("ooooooooooooooooooo");
        mapWithUpperCrab.add("ooooo-ooooooo-ooooo");
        mapWithUpperCrab.add("ooooo-o-----o-ooooo");
        mapWithUpperCrab.add("oooo---oo-oo---oooo");
        mapWithUpperCrab.add("ooooooooooooooooooo");
        mapWithUpperCrab.add("ooooooooooooooooooo");
        mapWithUpperCrab.add("ooooooooooooooooooo");
        mapWithUpperCrab.add("ooooooooooooooooooo");
        mapWithUpperCrab.add("ooooooooooooooooooo");
        mapWithUpperCrab.add("ooooooooooooooooooo");
    }

    private void fillMapWithLowerCrab(){
        mapWithLowerCrab = new ArrayList<>();
        mapWithLowerCrab.add("ooooooooooooooooooo");
        mapWithLowerCrab.add("ooooooooooooooooooo");
        mapWithLowerCrab.add("ooooooooooooooooooo");
        mapWithLowerCrab.add("ooooooooooooooooooo");
        mapWithLowerCrab.add("oooo--o-----o--oooo");
        mapWithLowerCrab.add("oooo---o---o---oooo");
        mapWithLowerCrab.add("oooo--ooooooo--oooo");
        mapWithLowerCrab.add("oooo-oo-ooo-oo-oooo");

    }

}