/* Program Name: ImageGridPane.java
   Author: Jose Ramirez
   Last Updated: 2/14/2024 (Valentine's Day Update)
   Summary: A Java program that uses JavaFX to create a window displaying a grid pane with four imaged arranged in a 2x2 layout.

   This was fun to research about!

   Also, it is important to set up your IDE to the settings for JavaFX. I use IntelJ IDEA as an IDEA, and here are the steps to work it out:
   Add the lib of the javafx file to the libraries in the project structure by pressing ctrl + shift + alt + s.
   Create a new configuration to run Java FX with the module of the project's name.
   In the VM Option add: --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls, javafx.fxml

    Awesome!
 */

package ImageGridPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ImageGridPane extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a grid pane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.setVgap(50);

        // Add images to the grid pane
        Image image1 = new Image("Images/flag1.gif");
        Image image2 = new Image("Images/flag2.gif");
        Image image3 = new Image("Images/flag6.gif");
        Image image4 = new Image("Images/flag7.gif");

        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);
        ImageView imageView4 = new ImageView(image4);

        gridPane.add(imageView1, 0, 0);
        gridPane.add(imageView2, 1, 0);
        gridPane.add(imageView3, 0, 1);
        gridPane.add(imageView4, 1, 1);

        // Create a scene and set it on the stage
        Scene scene = new Scene(gridPane, 900, 500);
        primaryStage.setScene(scene);

        // Set the stage title and show it
        primaryStage.setTitle("Image Grid Pane");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
