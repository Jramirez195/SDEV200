/*
 Program Name: BinaryFormatException.java
 Author: Jose Ramirez
 Date Last Updated: 01/28/2024
 Summary: This custom exception class extends the Exception class, allowing it to be thrown and caught like any other exception.

 IMPORTANT:
 "BinaryConverter.java" is the code to be run in this project!
*/

package BinaryConverter;

public class BinaryFormatException extends Exception {
    public BinaryFormatException(String message) {
        super(message);
    }
}

