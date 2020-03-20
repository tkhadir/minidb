package com.company.readers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class PlayerReader {

    public static String find(final String param, final String filename) {
        try {
            File f = new File(filename);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.contains(param)) {
                    s.close();
                    return line;
                }
            }
            s.close();
            return "nothing found!";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "nothing found! an error occured";
        }
    }

    public static List<String> findAll(final String filename) {
        try {
            Path path = Paths.get(filename);
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args)  {
        System.out.println("test");
    }
}
