// Program Name: TestMyDate.java
// Author: Jose Ramirez
// Date Last Updated: 01/26/2024
// Summary: Test program for MyDate.java

// IMPORTANT:
// Run this code for the project to work!!

package MyDateTest;
public class TestMyDate {
    public static void main(String[] args) {
        MyDate date1 = new MyDate();
        MyDate date2 = new MyDate(34355555133101L);

        System.out.println("Date1: Year = " + date1.getYear() + " Month = " + date1.getMonth() + " Day = " + date1.getDay());
        System.out.println("Date2: Year = " + date2.getYear() + " Month = " + date2.getMonth() + " Day = " + date2.getDay());
    }
}
