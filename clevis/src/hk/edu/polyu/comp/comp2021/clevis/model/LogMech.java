package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;

public class LogMech {
    private static ArrayList<String> LogArray = new ArrayList<>();
    public static void write(String shell) {
        LogArray.add(shell);
    }

    public static void outputHtml(){}
    public static void outputTxt(){}
}
