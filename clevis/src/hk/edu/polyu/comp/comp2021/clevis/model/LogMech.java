package hk.edu.polyu.comp.comp2021.clevis.model;
import java.io.*;
import java.util.ArrayList;

public class LogMech {
    private static final String path = "./";
    private static String txtName = "log.txt";
    private static String htmlName = "log.html";
    private static final ArrayList<String> logArray = new ArrayList<>();
    public static void write(String text) {logArray.add(text);}
    public static void outputHtml(){
        int num =0;
        FileWriter writer;
        try {
            writer = new FileWriter(path+getHtmlName());
            String preChar = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<meta charset=\"utf-8\"> \n" +
                    "<title>Log Mech</title> \n" +
                    "</head>\n" +
                    "<body>\n" + "<table border=\"4\">\n";
            String trStart = "<tr>\n";
            String trEnd = "</tr>\n";
            String tdStart = "<td>\n";
            String tdEnd = "</td>\n";
            String afterChar = "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>";
            writer.write(preChar);

            for (String s:logArray) {
                writer.write(trStart);
                writer.write(tdStart);
                writer.write(String.valueOf(++num));
                writer.write(tdEnd);
                writer.write(tdStart);
                writer.write(s+"\n");
                writer.write(tdEnd);
                writer.write(trEnd);
            }

            writer.write(afterChar);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("The Log Mech is broken! The HTML log may be affected!");
        }

    }
    public static void outputTxt(){
        File f = new File(path+ getTxtName());
        try{
            String beginWord= "----------Start----------";
            String endWord= "-----------End-----------";
            OutputStream fout = new FileOutputStream(f);

            for (int i = 0; i < beginWord.length(); i++) {
                fout.write(beginWord.charAt(i));
            }
            fout.write('\n');
            for (String s:logArray) {
                for (int i = 0; i < s.length(); i++) {
                    fout.write(s.charAt(i));
                }
                fout.write('\n');
            }
            for (int i = 0; i< endWord.length(); i++){
                fout.write(endWord.charAt(i));
            }

            fout.close();

        }catch (java.io.IOException e){
            System.out.println("The Log Mech is broken! The TXT log may be affected!");
        }
    }

    // -------------standardize----------------------
    public static String getTxtName() {
        return txtName;
    }
    public static void setTxtName(String txtName) {
        LogMech.txtName = txtName;
    }
    public static String getHtmlName() {
        return htmlName;
    }
    public static void setHtmlName(String htmlName) {
        LogMech.htmlName = htmlName;
    }
}
