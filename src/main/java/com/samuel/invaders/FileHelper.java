package com.samuel.invaders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHelper {

    public FileHelper() {
    }

    public List<String> getDataFromPath(String fileName) {
        List<String> result = new ArrayList<>();
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.add(line);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
