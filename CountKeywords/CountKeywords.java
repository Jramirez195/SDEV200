/* Program Name: CountKeywords.java
   Author: Jose Ramirez
   Date Last Updated: 2/11/2024
   Summary: This program counts the number of Java keywords in a given source file (Excluding those within comments and string literals.)
 */

package CountKeywords;

import java.util.*;
import java.io.*;

public class CountKeywords {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java CountKeywords <source-file.java>");
            System.exit(1);
        }

        String filename = args[0];
        File file = new File(filename);

        if (file.exists()) {
            System.out.println("The number of keywords in " + filename + " is " + countKeywords(file));
        } else {
            System.out.println("File " + filename + " does not exist");
        }
    }

    public static int countKeywords(File file) throws Exception {
        String[] keywordString = {"abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum",
                "extends", "for", "final", "finally", "float", "goto",
                "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "package", "private",
                "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this",
                "throw", "throws", "transient", "try", "void", "volatile",
                "while", "true", "false", "null"};

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
        int count = 0;

        Scanner input = new Scanner(file);
        boolean inBlockComment = false;

        while (input.hasNextLine()) {
            String line = input.nextLine();
            // Remove string literals
            line = line.replaceAll("\".*?\"", "");

            // Check for block comments
            if (line.contains("/*")) {
                inBlockComment = true;
            }
            if (inBlockComment) {
                if (line.contains("*/")) {
                    inBlockComment = false;
                    line = line.substring(line.indexOf("*/") + 2);
                } else {
                    // Skip the line if it's inside a block comment
                    continue;
                }
            }

            // Check and remove single-line comments
            if (line.contains("//")) {
                line = line.substring(0, line.indexOf("//"));
            }

            // Use a scanner to read each word on the filtered line
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                String word = lineScanner.next();
                if (keywordSet.contains(word)) {
                    count++;
                }
            }
            lineScanner.close();
        }

        input.close();
        return count;
    }
}
