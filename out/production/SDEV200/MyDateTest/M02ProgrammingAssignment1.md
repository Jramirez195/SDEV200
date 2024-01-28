# M02 Programming Assignment 1
## Chapter 10 - 10.14 {The Mydate Class}

Design a class named MyDate. The class contains:
- The data field year, month, and day that represent a date. month us 0-based, i.e., 0 is for January.
- A no-arg constructor that creates a MyDateTest object for the current date.
- A constructor that constructs a MyDateTest object with a specified elapsed time since midnight, January 1, 1970, in milliseconds.
- A constructor that constructs a MyDateTest object with the specified year, month, and day.
- Three getter methods for the data fields year, month, and day, respectively.
- A method named setDate(long elapsedTime) that sets a new date for the object using the elapsed time.

Draw the UML diagram for the class then implement the class. Write a test program that creates two MyDateTest objects (using new MyDateTest() and new MyDateTest(34355555133101L)) and displays their year, month, and day.

(**HINT**: The first two constructors will extract the year, month, and day from the elapsed time. For Example, if the elapsed time is 561555550000 milliseconds, the year is 1987, the month is 9, and the day is 18. You may use the GregorianCalendar class discussed in Programming Exercise 9.5 to simplify coding.)