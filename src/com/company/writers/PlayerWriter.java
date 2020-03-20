package com.company.writers;

import java.io.FileWriter;
import java.io.IOException;

public class PlayerWriter {

    private  static final String NEW_LINE = "\n";
    public static String write(final String data, final String filename) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(filename, true);
            myWriter.write(NEW_LINE + data);
            myWriter.close();
            return "done";
        } catch (IOException e) {
            e.printStackTrace();
            return "done but an error occured";
        }
    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}
