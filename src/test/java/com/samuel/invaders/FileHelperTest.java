package com.samuel.invaders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileHelperTest {

    private FileHelper fileHelper;
    private List<String> crab;

	@Before
	public void init(){

		fileHelper = new FileHelper();

        crab = new ArrayList<>();
        crab.add("--o-----o--");
        crab.add("---o---o---");
        crab.add("--ooooooo--");
        crab.add("-oo-ooo-oo-");
        crab.add("ooooooooooo");
        crab.add("o-ooooooo-o");
        crab.add("o-o-----o-o");
        crab.add("---oo-oo---");
	}

	@Test
	public void pathNotFoundReturnsEmptyList() {
        List<String> data = fileHelper.getDataFromPath("");

		assertEquals(data,new ArrayList<String>());
	}

    @Test
    public void dataObtained() {
        List<String> data = fileHelper.getDataFromPath("src/main/resources/known_alien/crab.txt");

        assertEquals(data,crab);
    }

}