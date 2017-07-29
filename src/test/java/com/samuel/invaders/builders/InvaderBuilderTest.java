package com.samuel.invaders.builders;

import com.samuel.invaders.FileHelper;
import com.samuel.invaders.models.Invader;
import org.junit.Before;
import org.junit.Ignore;
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
public class InvaderBuilderTest {

    private InvaderBuilder invaderBuilder;
    private FileHelper fileHelper;
    private List<String> crab;
    private List<String> mapWithCrab;
    private String name = "testname";
    private Invader invader;

    @Before
    public void init(){
        crab = new ArrayList<>();
        crab.add("--o-----o--");
        crab.add("---o---o---");
        crab.add("--ooooooo--");
        crab.add("-oo-ooo-oo-");
        crab.add("ooooooooooo");
        crab.add("o-ooooooo-o");
        crab.add("o-o-----o-o");
        crab.add("---oo-oo---");

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

        fileHelper = mock(FileHelper.class);
        when(fileHelper.getDataFromPath(any(String.class))).thenReturn(crab);
        invaderBuilder = new InvaderBuilder(fileHelper);
        invader = new Invader(name,crab );
    }

    @Test
    public void buildFromFileOK() {

        assertEquals(invaderBuilder.buildFromFile(name,""),invader);
    }

    @Test
    public void buildInvaderFromSubMapSameSize() {
        Invader myInvader = invaderBuilder.buildInvaderFromSubMap(name, crab, 8,
        11, 0, 0);

        assertEquals(myInvader,invader);
    }

    @Test
    public void buildInvaderFromSubMapBigger() {
        fileHelper = mock(FileHelper.class);
        when(fileHelper.getDataFromPath(any(String.class))).thenReturn(mapWithCrab);
        invaderBuilder = new InvaderBuilder(fileHelper);
        Invader myInvader = invaderBuilder.buildInvaderFromSubMap(name, mapWithCrab, 8,
                11, 1, 4);

        assertEquals(myInvader,invader);
    }


}