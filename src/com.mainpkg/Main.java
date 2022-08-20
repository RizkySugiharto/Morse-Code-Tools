package com.mainpkg;

import misc.Utility;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("--------- Morse Code Tools ---------\n");

        System.out.println("( 1 ) Displays a Dictionary of Morse Code");
        System.out.println("( 2 ) Convert sentences to morse code");
        System.out.println("( 3 ) Convert morse code to sentences");
        System.out.println("( 4 ) Convert files morse code to sentences");
        System.out.println("( 5 ) Convert files sentences to morse code");

        Scanner userInput = new Scanner(System.in);
        String[][] dictCode = Utility.createDictCode();

        System.out.print("\nInput the number (1-5) you need: ");
        String numberInput = userInput.next();

        String sentences;
        String morseCode;
        String caseMode;

        switch (numberInput) {
            case "1":
                Utility.show_morseCodeList(dictCode);
                break;
            case "2":
                userInput = new Scanner(System.in);
                System.out.print("Input your sentences: ");
                sentences = userInput.nextLine();
                System.out.println("---------------------------");
                System.out.println("The Sentences:\n\""+sentences+"\"");

                morseCode = Utility.convertToMorseCode(sentences, dictCode);

                System.out.println("---------------------------");
                System.out.println("Morse Code result:\n\""+morseCode+"\"");
                System.out.println("---------------------------");

                break;
            case "3":
                userInput = new Scanner(System.in);
                System.out.print("Input your Morse Code: ");
                morseCode = userInput.nextLine();

                userInput = new Scanner(System.in);
                System.out.print("Upper Case [up] or Lower Case [low]? ");
                caseMode = userInput.nextLine();

                System.out.println("---------------------------");
                System.out.println("The Morse Code:\n\""+morseCode+"\"");

                sentences = Utility.convertToSentences(morseCode, dictCode, caseMode);

                System.out.println("---------------------------");
                System.out.println("Sentences result:\n\""+sentences+"\"");
                System.out.println("---------------------------");

                break;
            case "4":
                userInput = new Scanner(System.in);
                System.out.print("Upper Case [up] or Lower Case [low]? ");
                caseMode = userInput.nextLine();

                Utility.Files_ConvertToSentences(dictCode, caseMode);

                System.out.println("----------------------------");
                System.out.println("      Successfully !!!      ");
                System.out.println("----------------------------");

                break;
            case "5":
                Utility.Files_ConvertToMorseCode(dictCode);

                System.out.println("----------------------------");
                System.out.println("      Successfully !!!      ");
                System.out.println("----------------------------");
        }

    }

}
