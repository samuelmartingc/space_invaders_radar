package com.samuel.invaders.builders;

import com.samuel.invaders.FileHelper;
import com.samuel.invaders.models.Invader;

import java.util.ArrayList;
import java.util.List;

public class InvaderBuilder {

    private final FileHelper fileHelper;
    public InvaderBuilder(FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }

    public Invader buildFromFile(String name, String fileName) {
        return new Invader(name,fileHelper.getDataFromPath(fileName));
    }

    public Invader buildInvaderFromSubMap(String name, List<String> map, int height,
                                          int width, int verticalStart, int horizontalStart) {
        List<String> finalBody = new ArrayList<>();
        List<String> rawBody = new ArrayList<>();
        final int verticalEnd = verticalStart + height;
        final int horizontalEnd = horizontalStart + width;
        for (int vertical = verticalStart; vertical < verticalEnd; vertical++) {
            rawBody.add(map.get(vertical));
        }

        for (String line : rawBody) {
            finalBody.add(line.substring(horizontalStart,horizontalEnd));
        }
        return new Invader(name,finalBody);
    }

}
