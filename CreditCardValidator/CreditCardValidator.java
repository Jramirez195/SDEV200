package CreditCardValidator;// Program Name: CreditCardValidator.CreditCardValidator.java
// Author: Jose Ramirez
// Last Updated: 1/17/2024
// Summary: A Java program that validates credit card numbers using the Luhn algorithm, also known as the Mod 10 check.

import java.util.Scanner;

public class CreditCardValidator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a credit card number as a long integer: ");
        long number = input.nextLong();

        System.out.println(number + " is " + (isValid(number) ? "valid" : "invalid"));
    }

    public static boolean isValid(long number) {
        return (getSize(number) >= 13 && getSize(number) <= 16) &&
                (prefixMatched(number, 4) || prefixMatched(number, 5) ||
                        prefixMatched(number, 37) || prefixMatched(number, 6)) &&
                ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);
    }

    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String num = Long.toString(number);
        for (int i = getSize(number) - 2; i >= 0; i -= 2) {
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
        }
        return sum;
    }

    public static int getDigit(int number) {
        if (number < 9) return number;
        return number / 10 + number % 10;
    }

    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String num = Long.toString(number);
        for (int i = getSize(number) - 1; i >= 0; i -= 2) {
            sum += Integer.parseInt(num.charAt(i) + "");
        }
        return sum;
    }

    public static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize((long)d)) == d;
    }

    public static int getSize(long d) {
        String num = Long.toString(d);
        return num.length();
    }

    public static long getPrefix(long number, int k) {
        if (getSize(number) > k)  {
            String num = Long.toString(number);
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }
}
