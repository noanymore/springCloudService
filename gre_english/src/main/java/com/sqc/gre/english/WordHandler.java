package com.sqc.gre.english;

import java.io.*;

import static com.sqc.gre.english.StringArrayUtil.devideStringToArray;
import static com.sqc.gre.english.StringArrayUtil.disruptOrder;

/**
 * @author < a href="sqc95111@163.com">sqc</ a>
 * @version 1.0
 * @ClassName WordHandle
 * @Description TODO
 * @date 2019/4/15 11:06
 **/
public class WordHandler {

    private String[] words;

    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String words = readToString("C:\\Users\\sqc95\\Desktop\\English\\words.txt");
        String[] array = devideStringToArray(words);
        array = disruptOrder(array);
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+",");
        }
    }

}
