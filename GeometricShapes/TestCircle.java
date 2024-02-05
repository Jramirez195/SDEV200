/* Program Name: TestCircle.java
   Author: Jose Ramirez
   Last Updated: 2/4/2024
   Summary: This is the .java test program for this assignment.

   Important!!

  Run this code for this assignment.
 */

package GeometricShapes;
public class TestCircle {
    public static void main(String[] args) {
        Circle circle1 = new Circle(5, "red", true);
        Circle circle2 = new Circle(5, "green", false);
        Circle circle3 = new Circle(4, "blue", true);

        System.out.println("Circle1 Area: " + circle1.getArea() + " Perimeter: " + circle1.getPerimeter());
        System.out.println("Circle2 Area: " + circle2.getArea() + " Perimeter: " + circle2.getPerimeter());
        System.out.println("Circle3 Area: " + circle3.getArea() + " Perimeter: " + circle3.getPerimeter());

        System.out.println("Circle1 equals Circle2? " + circle1.equals(circle2));
        System.out.println("Circle1 equals Circle3? " + circle1.equals(circle3));

        // Comparing circles
        compareCircles(circle1, circle2);
        compareCircles(circle1, circle3);
    }

    public static void compareCircles(Circle c1, Circle c2) {
        int result = c1.compareTo(c2);
        if (result > 0) {
            System.out.println("Circle1 is larger than Circle2");
        } else if (result < 0) {
            System.out.println("Circle1 is smaller than Circle2");
        } else {
            System.out.println("Circle1 is equal to Circle2");
        }
    }
}
