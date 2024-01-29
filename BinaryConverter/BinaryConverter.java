/*
Program Name: BinaryConverter.java
Author: Jose Ramirez
Date Last Updated: 01/28/2024
Summary: This program converts binary strings to their decimal equivalents and throws a custom BinaryFormatException if the input string contains characters other than '0' or '1'.

IMPORTANT:
This is the code to run, do not run the other code (I'm not sure if you can, but don't).
 */
package BinaryConverter;

public class BinaryConverter {
    public static int bin2dec(String binaryString) throws BinaryFormatException {
        int decimal = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            char bit = binaryString.charAt(i);
            if (bit == '0' || bit == '1') {
                decimal = decimal * 2 + (bit - '0');
            } else {
                // If the current character is not 0 or 1, throw an exception
                throw new BinaryFormatException("Invalid binary string: " + binaryString);
            }
        }
        return decimal;
    }

    public static void main(String[] args) {
        try {
            System.out.println("1101 in decimal is: " + bin2dec("1101"));
            System.out.println("11012 in decimal is: " + bin2dec("11012")); // This will cause an exception
        } catch (BinaryFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}
