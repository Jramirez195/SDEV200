/* Program Name: TextColorSelector.java
   Author: Jose Ramirez
   Last Updated: 2/14/2024
   Summary: This JavaFX project allows selecting and preview text color and opacity values through sliders.
 */

package TextColorSelector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TextColorSelector extends Application {

    private Slider redSlider;
    private Slider greenSlider;
    private Slider blueSlider;
    private Slider opacitySlider;
    private Label colorLabel;
    private TextField wordTextField;

    @Override
    public void start(Stage primaryStage) {
        // Create sliders for red, green, blue, and opacity
        redSlider = createColorSlider("Red:");
        greenSlider = createColorSlider("Green:");
        blueSlider = createColorSlider("Blue:");
        opacitySlider = createColorSlider("Opacity:");

        // Label to display selected color
        colorLabel = new Label("Selected Color");
        colorLabel.setStyle("-fx-font-size: 18;");

        // Text field to display the word with the selected color and opacity
        wordTextField = new TextField("Word");
        wordTextField.setEditable(false);
        wordTextField.setStyle("-fx-font-size: 18;");

        // Set initial color and update the word text
        updateColor();

        // Handlers for slider value changes
        redSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateColor());
        greenSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateColor());
        blueSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateColor());
        opacitySlider.valueProperty().addListener((observable, oldValue, newValue) -> updateColor());

        // Layout setup using a GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Red:"), redSlider);
        gridPane.addRow(1, new Label("Green:"), greenSlider);
        gridPane.addRow(2, new Label("Blue:"), blueSlider);
        gridPane.addRow(3, new Label("Opacity:"), opacitySlider);
        gridPane.add(colorLabel, 0, 4, 2, 1);
        gridPane.add(new Label("Word:"), 0, 5);
        gridPane.add(wordTextField, 1, 5, 1, 1);
        gridPane.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(gridPane, 300, 300);

        primaryStage.setTitle("Text Color Selector");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Slider createColorSlider(String label) {
        Slider slider = new Slider(0, 100, 0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setBlockIncrement(5);

        Label sliderLabel = new Label(label);
        sliderLabel.setFont(new Font(12));

        return slider;
    }

    private void updateColor() {
        double red = redSlider.getValue() / 100.0;
        double green = greenSlider.getValue() / 100.0;
        double blue = blueSlider.getValue() / 100.0;
        double opacity = opacitySlider.getValue() / 100.0;

        Color textColor = new Color(red, green, blue, opacity);
        colorLabel.setTextFill(textColor);
        wordTextField.setStyle("-fx-text-fill: rgb(" + (int)(255 * red) + "," + (int)(255 * green) + "," + (int)(255 * blue) + "); -fx-font-size: 18;");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
