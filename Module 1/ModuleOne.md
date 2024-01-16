## Module 1
### M01 Programming Assignment (1)
#### Chapter 6 - 6.9 {Conversions Between Feet And Meters}
Write a class that contains the following two methods:

```
/** Convert from feet to meters */
public static double footToMeter(double foot)

/** Convert from meters to feet */
public static double meterToFoot(double meter)
```
The formula for the conversion is:
```
meter = 0.305 * foot
foot = 3.279 * meter
```
Write a test program that invokes these methods to display the following tables:
```
Feet    Meters  |   Meters    Feet
-----------------------------------
1.0     0.305   |   20.0      65.574
2.0     0.610   |   25.0      81.967
3.0     0.915   |   30.0      98.361
4.0     1.220   |   35.0      114.754
5.0     1.525   |   40.0      131.148
6.0     1.830   |   45.0      147.541
7.0     2.135   |   50.0      163.934
8.0     2.440   |   55.0      180.328
9.0     2.745   |   60.0      196.721
10.0    3.050   |   65.0      213.115
```

### M01 Programming Assignment (2)
#### Chapter 6 - 6.31 {Financial: Credit Card Number Validation}
Credit card numbers follow certain patterns. A credit card number must have between 13 and 16 digits. It must start with:

- 4 for Visa Cards
- 5 for Master Cards
- 37 for American Express Cards
- 6 for Discovery Cards

In 1954, Hans Luhn of IBM proposed an algorithm for validating credit card numbers. The algorithm is useful to determine whether a card number is entered correctly, or whether a credit card is scanned correctly by a scanner. Credit card numbers are generated following this validity check, commonly known as the Luhn check or the Mod 10 check, which can be described as follows (for illustration, consider the card number 4388576018402626)
1. Double every second digit from right to left. If doubling of a digit results in a two-digit number, add up the two digits to get a single-digit number.
2. Now add all single-digit numbers from Step 1.
```
4 + 4 + 8 + 2 + 3 + 1 + 7 + 8 = 37
```
3. Add all digits in the odd places from right to left in the card number.
```
6 + 6 + 0 + 8 + 0 + 7 + 8 + 3 = 38
```
4. Sum the results from Step 2 and Step 3.
```
37 + 38 = 75
```
5. If the result from Step 4 is divisible by 10, the card number is valid; otherwise, it is invalid. For example, the number 4388576018402626 is invalid, but the number 4388576018410707 is valid.

Write a program that prompts the user to enter a credit card number as a long integer. Display whether the number is valid or invalid. Design your program to use the following methods:
```
/** Return true if the card number is valid */
public static boolean isValid(long number)

/** Get the result from Step 2 */
public static int sumOfDoubleEvenPlace(long number)

/** Return this number if it is a single digit, otherwise, return the sum of the two digits */
public static int getDigit(int number)

/** Return sum of odd-place digits in number */
public static int sumOfOddPlace(long number)

/** Return true if the number d is a prefix for number */
public static boolean prefixMatched(long number, int d)

/** Return the number of digits in d */
public static in getSize(long d)

/** Return the first k number of digits from number. If the number of digits in number is less than k, return number. */
public static long getPrefix(long number, int k)
```
(You may also implement this program by reading the input as a string and processing the string to validate the credit card.)
```
Enter a credit card number as a long intger:
    4388576018410707
4388576018410707 is valid
```
```
Enter a credit card number as a long integer:
    4388576018402626
4388576018402626 is invalid
```

### M01 Programming Assignment (3)
#### Chapter 8 - 8.29 {Identical Arrays}
The two-dimensional arrays m1 and m2 are identical if they have the same contents. Write a method that returns true if m1 and m2 are identical, using the following header:
```
public static boolean equals(int[][] m1, int[][] m2)
```
Write a test program that prompts the user to enter two 3 X 3 arrays of integers and displays whether the two are identical. Here are the sample runs:
```
Enter list1: 51 25 22 6 1 4 24 54 6
Enter list2: 51 22 25 6 1 4 24 54 6
The two arrays are identical
```
```
Enter list1: 51 5 22 6 1 4 24 54 6
Enter list2: 51 22 25 6 1 4 24 54 6
The two arrays are not identical
```

### M01 Programming Assignment (4)
#### Chapter 9 - 9.9 {Geometry: N-Sided Regular Polygon}
In an n-sided regular polygon, all sides have the same length and all angles have the same degree (i.e., the polygon is both equilateral and equiangular). Design a class named RegularPolygon that contains:

- A private int data field named n that defines the number of sides in the polygon with default value 3.
- A private double data field named side that stores the length of the side with default value 1.
- A private double data field named x that defines the x-coordinate of the polygon's center with default value 0.
- A private double data field named y that defines the y-coordinate of the polygon's center with default value 0.
- A no-arg constructor that creates a regular polygon with default values.
- A constructor that creates a regular polygon with the specified number of sides and length of side, centered at (0, 0).
- A constructor that creates a regular polygon with the specified number of sides, length of side, and x- and y-coordinates.
- The accessor and mutator methods for all data fields.
- The method getPerimeter() that returns the perimeter of the polygon.
- The method getArea() that returns the area of the Polygon.

The formula for computing the area of a regular polygon is:

$$
Area = \frac{n \times s^2}{4 \times \tan(\frac{\pi}{n})}
$$

Draw the UML diagram for the class then implement the class. Write a test program that creates three RegularPolygon objects, created using the no-arg constructor using RegularPolygon (6, 4), and using RegularPolygon(10, 4, 5.6, 7.8). For each object, displays its perimeters and area.