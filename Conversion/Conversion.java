package Conversion;// Program Name: Conversion.Conversion.java
// Author: Jose Ramirez
// Last Updated: 1/17/2024
// Summary: A Java class with two static methods for unit conversion between feet and meters

public class Conversion {

    // Convert from feet to meters
    public static double footToMeter(double foot) {
        return 0.305 * foot;
    }

    // Convert from meters to feet
    public static double meterToFoot(double meter) {
        return 3.279 * meter;
    }

    public static void main(String[] args) {
        // Display the table headers
        System.out.println("Feet    Meters  |   Meters    Feet");
        System.out.println("-----------------------------------");

        // Generate and display the table rows
        for (int i = 1; i <= 10; i++) {
            double meters = 20 + (i - 1) * 5;
            System.out.printf("%4.1f %7.3f   |   %6.1f      %7.3f\n",
                    (double) i, footToMeter(i),
                    meters, meterToFoot(meters));
        }
    }
}
