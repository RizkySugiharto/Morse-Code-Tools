package misc;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Utility {

    public static void show_morseCodeList(String[][] dictCode) throws Exception {

        FileReader daftarCode_File = new FileReader("daftarCode.txt");
        BufferedReader daftarCode_Buffered = new BufferedReader(daftarCode_File);

        String code = daftarCode_Buffered.readLine();

        System.out.println("| Mean |   Code    |");
        System.out.println("--------------------");

        for (int i = 0 ; i < dictCode[0].length ; i++) {
            System.out.printf("|   %-3s|  %-7s  |\n", dictCode[0][i], dictCode[1][i]);
        }

        System.out.println("--------------------");

    }

    public static String[][] createDictCode() throws Exception {

        FileReader daftarCode_File = new FileReader("daftarCode.txt");
        BufferedReader daftarCode_Buffered = new BufferedReader(daftarCode_File);
        String[][] dictCode = new String[2][55];
        String line = daftarCode_Buffered.readLine();

        int i = 0;
        while (line != null) {
            StringTokenizer str;

            if (i == 50) {
                str = new StringTokenizer(line, "~");
            } else {
                str = new StringTokenizer(line, "=");
            }

            dictCode[0][i] = str.nextToken();
            dictCode[1][i] = str.nextToken();
            i++;
            line = daftarCode_Buffered.readLine();
        }

        return dictCode;

    }

    public static String convertToMorseCode(String sentences, String[][] dictCode) throws Exception {
        sentences = sentences.toUpperCase();
        String morseCode = "";

        for (int i = 0 ; i < sentences.length() ; i++) {
            for (int j = 0 ; j < dictCode[0].length ; j++) {
                String character = ""+sentences.charAt(i);
                if (character.equalsIgnoreCase(dictCode[0][j])) {
                    morseCode = morseCode+dictCode[1][j]+" ";
                    break;
                }
            }
        }

        return morseCode;
    }

    public static String convertToSentences(String morseCode, String[][] dictCode, String caseMode) throws Exception {
        Scanner lines = new Scanner(morseCode);
        String line = "";
        String sentences = "";
        boolean isExist = false;

        try {
            line = lines.next();
            isExist = true;
        } catch (Exception err) {
            isExist = false;
        }

        while (isExist) {

            for (int i = 0 ; i < dictCode[0].length ; i++) {
                if ( line.equalsIgnoreCase(dictCode[1][i]) ) {
                    sentences = sentences + dictCode[0][i];
                }
            }

            try {
                line = lines.next();
                isExist = true;
            } catch (Exception err) {
                isExist = false;
            }
        }

        if (caseMode.equalsIgnoreCase("up")) {
            sentences = sentences.toUpperCase();
        } else if (caseMode.equalsIgnoreCase("low")) {
            sentences = sentences.toLowerCase();
        } else {
            System.err.println("ERROR: Please choose \"up\" or \"low\"");
            return "";
        }

        return sentences;
    }

    public static void Files_ConvertToSentences(String[][] dictCode, String caseMode) throws Exception {
        File directoryInput = new File("MorseCode to Sentences/input");
        File[] directoryInput_Files = directoryInput.listFiles();
        FileReader[] filesInput_File = new FileReader[directoryInput_Files.length];
        FileWriter[] filesOutput_File = new FileWriter[directoryInput_Files.length];
        BufferedReader[] fileInput_Buffered = new BufferedReader[directoryInput_Files.length];
        BufferedWriter[] fileOutput_Buffered = new BufferedWriter[directoryInput_Files.length];
        String morseCode;
        String sentences;

        for (int i = 0 ; i < directoryInput_Files.length ; i++) {

            filesInput_File[i] = new FileReader("MorseCode to Sentences/input/"+directoryInput_Files[i].getName());
            fileInput_Buffered[i] = new BufferedReader(filesInput_File[i]);
            filesOutput_File[i] = new FileWriter("MorseCode to Sentences/output/"+directoryInput_Files[i].getName());
            fileOutput_Buffered[i] = new BufferedWriter(filesOutput_File[i]);

            morseCode = fileInput_Buffered[i].readLine();
            fileOutput_Buffered[i].write("");

            while (morseCode != null) {
                sentences = convertToSentences(morseCode, dictCode, caseMode);
                fileOutput_Buffered[i].append(sentences);
                fileOutput_Buffered[i].flush();
                fileOutput_Buffered[i].newLine();
                morseCode = fileInput_Buffered[i].readLine();
            }

        }

    }

    public static void Files_ConvertToMorseCode(String[][] dictCode) throws Exception {

        File directoryInput = new File("Sentences to MorseCode/input");
        File[] directoryInput_Files = directoryInput.listFiles();
        FileReader[] filesInput_File = new FileReader[directoryInput_Files.length];
        FileWriter[] filesOutput_File = new FileWriter[directoryInput_Files.length];
        BufferedReader[] fileInput_Buffered = new BufferedReader[directoryInput_Files.length];
        BufferedWriter[] fileOutput_Buffered = new BufferedWriter[directoryInput_Files.length];
        String morseCode;
        String sentences;

        for (int i = 0 ; i < directoryInput_Files.length ; i++) {

            filesInput_File[i] = new FileReader("Sentences to MorseCode/input/"+directoryInput_Files[i].getName());
            fileInput_Buffered[i] = new BufferedReader(filesInput_File[i]);
            filesOutput_File[i] = new FileWriter("Sentences to MorseCode/output/"+directoryInput_Files[i].getName());
            fileOutput_Buffered[i] = new BufferedWriter(filesOutput_File[i]);

            sentences = fileInput_Buffered[i].readLine();

            while (sentences != null) {
                morseCode = convertToMorseCode(sentences, dictCode);
                fileOutput_Buffered[i].append(morseCode);
                fileOutput_Buffered[i].flush();
                fileOutput_Buffered[i].newLine();
                sentences = fileInput_Buffered[i].readLine();
            }

        }

    }

}
