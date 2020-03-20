package com.company;

import com.company.readers.PlayerReader;
import com.company.writers.PlayerWriter;

import java.util.List;
import java.util.Scanner;
public class Player {
    private static final String REGEX_SEPARATOR = " ";
    private static final String GET_QUERY = "cat";
    private static final String SET_CONF_QUERY = "set";
    private static final String UPDATE_QUERY = "upsert";
    private static final String ALL_QUERY = "all";

    private String filename;
    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return  this.filename;
    }

    public boolean isFileNameEmpty() {
        return getFilename() == null ? true : getFilename().trim().isEmpty();
    }

    public Player() {
        System.out.println("init player");
        System.out.println("queries : " + GET_QUERY + " " + UPDATE_QUERY + " " + SET_CONF_QUERY + " " + ALL_QUERY);
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Player p = new Player();
        while (in.hasNextLine()) {
            System.out.println(p.analyseInput(in.nextLine()));
        }
    }

    private String analyseInput(String line) {
        if (line == null || line.isEmpty() || line.trim().isEmpty()) {
            return "la ligne est vide";
        } else if (isFileNameEmpty() && !line.startsWith(SET_CONF_QUERY)) {
            return "db file conf is empty";
        } else if (line.startsWith(GET_QUERY)) {
            return PlayerReader.find(line.split(REGEX_SEPARATOR)[1], getFilename());
        } else if (line.startsWith(SET_CONF_QUERY)) {
            setFilename(line.split(REGEX_SEPARATOR)[1]);
            return "conf updated";
        } else if (line.startsWith(UPDATE_QUERY)) {
            return PlayerWriter.write(line.split(REGEX_SEPARATOR)[1], getFilename());
        } else if (line.startsWith(ALL_QUERY)) {
            List<String> resultList = PlayerReader.findAll(getFilename());
            if (resultList != null) {
                resultList.stream().forEach(System.out::println);
            }
            return "done !";
        } else {
            return "don't understand your query";
        }
    }

}