package hk.edu.polyu.comp.comp2021.clevis.model;
import java.io.*;
import java.util.ArrayList;

/**
 * The type Log mech.
 */
public class LogMech {
    private static final String path = "./";
    private static String txtName = "log.txt";
    private static String htmlName = "log.html";
    private static final ArrayList<String> logArray = new ArrayList<>();

    /**
     * Write.
     *
     * @param text the text
     */
    public static void write(String text) {logArray.add(text);}

    /**
     * Output html.
     */
    public static void outputHtml(){
        int num =0;
        FileWriter writer;
        try {
            writer = new FileWriter(path+getHtmlName());
            String preChar1 = "<!DOCTYPE html>\n";
            String preChar2 = "<html>\n";
            String preChar3 = "<head>\n";
            String preChar4 = "<meta charset=\"utf-8\"> \n";
            String preChar5 = "<title>Log Mech</title> \n";
            String preChar6 = "</head>\n";
            String preChar7 = "<body>\n";
            String preChar8 = "<table border=\"4\">\n";
            String trStart = "<tr>\n";
            String trEnd = "</tr>\n";
            String tdStart = "<td>\n";
            String tdEnd = "</td>\n";

            String afterChar1 = "</table>\n";
            String afterChar2 = "\n";
            String afterChar3 = "</body>\n";
            String afterChar4 = "</html>";

            writer.write(preChar1);
            writer.write(preChar2);
            writer.write(preChar3);
            writer.write(preChar4);
            writer.write(preChar5);
            writer.write(preChar6);
            writer.write(preChar7);
            writer.write(preChar8);

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

            writer.write(afterChar1);
            writer.write(afterChar2);
            writer.write(afterChar3);
            writer.write(afterChar4);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("The Log Mech is broken! The HTML log may be affected!");
        }

    }

    /**
     * Output txt.
     */
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
    /**
     * Gets txt name.
     *
     * @return the txt name
     */

    public static String getTxtName() {
        return txtName;
    }

    /**
     * Sets txt name.
     *
     * @param txtName the txt name
     */
    public static void setTxtName(String txtName) {
        LogMech.txtName = txtName;
    }

    /**
     * Gets html name.
     *
     * @return the html name
     */
    public static String getHtmlName() {
        return htmlName;
    }

    /**
     * Sets html name.
     *
     * @param htmlName the html name
     */
    public static void setHtmlName(String htmlName) {
        LogMech.htmlName = htmlName;
    }
}
