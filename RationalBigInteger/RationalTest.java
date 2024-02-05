/* Program Name: RationalTest.java
   Author: Jose Ramirez
   Last Updated: 2/4/2024
   Summary: The test program for M03 Programming Assigment 2.

   IMPORTANT:
   Run This code for this to work properly.
 */

package RationalBigInteger;

import java.math.BigInteger;
import java.util.Scanner;

public class RationalTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the first rational number (numerator denominator): ");
        BigInteger num1 = input.nextBigInteger();
        BigInteger den1 = input.nextBigInteger();
        Rational rational1 = new Rational(num1, den1);

        System.out.print("Enter the second rational number (numerator denominator): ");
        BigInteger num2 = input.nextBigInteger();
        BigInteger den2 = input.nextBigInteger();
        Rational rational2 = new Rational(num2, den2);

        System.out.println(rational1 + " + " + rational2 + " = " + rational1.add(rational2));
        System.out.println(rational1 + " - " + rational2 + " = " + rational1.subtract(rational2));
        System.out.println(rational1 + " * " + rational2 + " = " + rational1.multiply(rational2));
        System.out.println(rational1 + " / " + rational2 + " = " + rational1.divide(rational2));

        input.close();
    }
}
