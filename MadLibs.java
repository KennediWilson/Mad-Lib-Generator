// Kennedi Wilson
// CS 141 Face to Face
// Lab 5 MadLibs 
// This program allows users to create and view Mad Libs.

import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MadLibs {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        while (true) {
            // Display menu options
            System.out.println("Options: (C)reate a new mad lib, (V)iew a mad lib, (Q)uit");
            String choice = console.nextLine().toUpperCase();
            if (choice.equals("C")) {
                createMadLib(console); // Create a new Mad Lib
            } else if (choice.equals("V")) {
                viewMadLib(console); // View an existing Mad Lib
            } else if (choice.equals("Q")) {
                System.out.println("Goodbye!");
                break; // Quit the program
            } else {
                System.out.println("Invalid option, please choose C, V, or Q.");
            }
        }
    }

    // Method to create a new Mad Lib
    public static void createMadLib(Scanner console) {
        System.out.print("Input file name: ");
        String inputFileName = console.nextLine();
        File inputFile = new File(inputFileName);
        // Prompt user until a valid file is found
        while (!inputFile.exists()) {
            System.out.print("File not found. Try again: ");
            inputFileName = console.nextLine();
            inputFile = new File(inputFileName);
        }

        System.out.print("Output file name: ");
        String outputFileName = console.nextLine();
        try {
            // Read input file
            Scanner input = new Scanner(inputFile);
            // Prepare to write to output file
            PrintStream output = new PrintStream(outputFileName);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNext()) {
                    String word = lineScanner.next();
                    // Check for placeholders
                    if (word.startsWith("<") && word.endsWith(">")) {
                        // Extract and format placeholder
                        String placeholder = word.substring(1, word.length() - 1).replace("-", " ");
                        // Determine article based on first letter
                        String article = "a";
                        if ("AEIOUaeiou".indexOf(placeholder.charAt(0)) >= 0) {
                            article = "an";
                        }
                        // Prompt user for placeholder replacement
                        System.out.print("Please type " + article + " " + placeholder + ": ");
                        String replacement = console.nextLine();
                        output.print(replacement + " "); // Write replacement to output file
                    } else {
                        output.print(word + " "); // Write normal word to output file
                    }
                }
                output.println(); // End of line in output file
                lineScanner.close();
            }
            input.close();
            output.close();
            System.out.println("Mad lib created successfully. You can view it using the 'V' option.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to create the mad lib.");
        }
    }

    // Method to view an existing Mad Lib
    public static void viewMadLib(Scanner console) {
        System.out.print("Input file name: ");
        String fileName = console.nextLine();
        File file = new File(fileName);
        // Prompt user until a valid file is found
        while (!file.exists()) {
            System.out.print("File not found. Try again: ");
            fileName = console.nextLine();
            file = new File(fileName);
        }

        try {
            // Read and display the contents of the file
            Scanner input = new Scanner(file);
            System.out.println("\n--- Mad Lib ---");
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
            System.out.println("--- End of Mad Lib ---\n");
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to view the mad lib.");
        }
    }
}
