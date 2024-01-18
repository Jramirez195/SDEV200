// Program Name: RegularPolygon.java
// Author: Jose Ramirez
// Last Updated: 1/18/2024
// Summary: Designing and implementing a Java class named RegularPolygon to model a regular polygon


// IMPORTANT: For program to work, run java program, "TestRegularPython.java" instead of this program.

package PolygonTest;
public class RegularPolygon {
    private int n = 3; // Number of sides
    private double side = 1; // Length of side
    private double x = 0; // x-coordinate of the polygon's center
    private double y = 0; // y-coordinate of the polygon's center

    // No-arg constructor
    public RegularPolygon() {}

    // Constructor with specified number of sides and length of side
    public RegularPolygon(int n, double side) {
        this.n = n;
        this.side = side;
    }

    // Constructor with specified number of sides, length of side, and x- and y-coordinates
    public RegularPolygon(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    // Accessor and mutator methods for all data fields
    public int getN() { return n; }
    public void setN(int n) { this.n = n; }
    public double getSide() { return side; }
    public void setSide(double side) { this.side = side; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    // Method to get the perimeter
    public double getPerimeter() {
        return n * side;
    }

    // Method to get the area
    public double getArea() {
        return (n * Math.pow(side, 2)) / (4 * Math.tan(Math.PI / n));
    }
}
