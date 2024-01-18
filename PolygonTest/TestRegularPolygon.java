// Program Name: TestRegularPython.java
// Author: Jose Ramirez
// Last Updated: 1/18/2024
// Summary: The test program for RegularPython.java

// Important: Run this file!!

package PolygonTest;

public class TestRegularPolygon {
    public static void main(String[] args) {
        RegularPolygon polygon1 = new RegularPolygon();
        RegularPolygon polygon2 = new RegularPolygon(6, 4);
        RegularPolygon polygon3 = new RegularPolygon(10, 4, 5.6, 7.8);

        System.out.println("Polygon 1: Perimeter = " + polygon1.getPerimeter() + ", Area = " + polygon1.getArea());
        System.out.println("Polygon 2: Perimeter = " + polygon2.getPerimeter() + ", Area = " + polygon2.getArea());
        System.out.println("Polygon 3: Perimeter = " + polygon3.getPerimeter() + ", Area = " + polygon3.getArea());
    }
}
