/* Program Name: SymbolMatcherFile.java
   Author: Jose Ramirez
   Last Updated: 2/8/2024
   Summary: This program check a source-code for correctly paired grouping symbols (parentheses, braces, and brackets) using a stack to ensure proper nesting and matching.
 */

package SymbolMatcher;

// Import necessary IO classes for this program.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

// Main class
public class SymbolMatcherFile {

    public static void main(String[] args) {
        // Check if the user provided only one command-line argument.
        if (args.length != 1) {
            System.out.println("Usage: java SymbolMatcher <source-code-file>");
            System.exit(1); // Exit if the number of arguments are not correct.
        }

        // Store the file name.
        String filename = args[0];
        System.out.println("Checking file: " + filename);

        // Attempt to check the symbols from the file.
        try {
            boolean isValid = checkSymbols(filename);
            if (isValid) {
                System.out.println("All grouping symbols are correctly matched.");
            } else {
                System.out.println("Grouping symbols are incorrectly matched.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    // Method to check the pairing of grouping symbols.
    private static boolean checkSymbols(String filename) throws IOException {
        Stack<Character> stack = new Stack<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        int c;
        // Read through the file character by character.
        while ((c = reader.read()) != -1) {
            char character = (char) c;
            switch (character) {
                case '(':
                case '{':
                case '[':
                    stack.push(character);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                default:
                    // Ignore other characters
                    break;
            }
        }
        reader.close();
        return stack.isEmpty();
    }
}
