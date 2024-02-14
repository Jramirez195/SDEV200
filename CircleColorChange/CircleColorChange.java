/* Program Name: CircleColorChange.java
   Author: Jose Ramirez
   Last Updated: 2/14/2024
   Summary: A Java program that uses JavaFX to create a window that when clicked, a black button appears.
 */

package CircleColorChange;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleColorChange extends Application {

    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(50, Color.WHITE);

        StackPane root = new StackPane();
        root.getChildren().add(circle);

        Scene scene = new Scene(root, 300, 250);

        // Add event handlers for mouse press and release
        scene.setOnMousePressed(e -> handleMousePress(circle));
        scene.setOnMouseReleased(e -> handleMouseRelease(circle));

        primaryStage.setTitle("Circle Color Change");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleMousePress(Circle circle) {
        circle.setFill(Color.BLACK);
    }

    private void handleMouseRelease(Circle circle) {
        circle.setFill(Color.WHITE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
