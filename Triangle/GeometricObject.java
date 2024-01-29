/*
 Program Name: GeometricObject.java
 Author: Jose Ramirez
 Date Last Updated: 01/28/2024
 Summary: The "GeometricObject" class serves as the foundational java program designed to model geometric shapes.

 IMPORTANT:
 Do not run this code, run the TestTriangle.java program for this project to work successfully.
*/

package Triangle;

import java.util.Date;

public class GeometricObject {
    private String color = "white";
    private boolean filled;
    private Date dateCreated;

    public GeometricObject() {
        dateCreated = new Date();
    }

    public GeometricObject(String color, boolean filled) {
        dateCreated = new Date();
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color + " and filled: " + filled;
    }
}
