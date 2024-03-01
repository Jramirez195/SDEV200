/*  Program Name: databaseComparison.java
    Author: Jose Ramirez
    Date Last Updated: 2/27/2024
    Summary: This JavaFX project demonstrates database performance by inserting a thousand records into a database table.
 */

package DatabaseComparison;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import java.util.Optional;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class databaseComparison extends Application {

    private static final int RECORD_COUNT = 1000;
    private static final String TABLE_NAME = "Temp";

    private Connection connection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button connectButton = new Button("Connect to Database");
        connectButton.setOnAction(e -> showDbConnectionDialog());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.getChildren().add(connectButton);

        primaryStage.setTitle("Database Performance Comparison");
        primaryStage.setScene(new Scene(grid, 400, 200));
        primaryStage.show();
    }


    private void showDbConnectionDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Database Connection");
        dialog.setHeaderText("Enter database connection details:");

        ButtonType connectButtonType = new ButtonType("Connect");
        dialog.getDialogPane().getButtonTypes().addAll(connectButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        TextField urlField = new TextField();
        urlField.setPromptText("JDBC URL");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");

        grid.add(new Label("JDBC URL:"), 0, 0);
        grid.add(urlField, 1, 0);
        grid.add(new Label("Username:"), 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(new Label("Password:"), 0, 2);
        grid.add(passwordField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == connectButtonType) {
                return new Pair<>(urlField.getText(), usernameField.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(this::connectToDatabase);
    }

    private void connectToDatabase(Pair<String, String> connectionDetails) {
        try {
            String url = connectionDetails.getKey();
            String username = connectionDetails.getValue();
            String password = getPasswordFromUser();

            connection = DriverManager.getConnection(url, username, password);
            insertRecordsWithoutBatch();
            insertRecordsWithBatch();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Connection Error", "Error connecting to the database.");
        }
    }

    private void insertRecordsWithoutBatch() {
        long startTime = System.currentTimeMillis();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO " + TABLE_NAME + "(num1, num2, num3) VALUES (?, ?, ?)");

            for (int i = 0; i < RECORD_COUNT; i++) {
                preparedStatement.setDouble(1, Math.random());
                preparedStatement.setDouble(2, Math.random());
                preparedStatement.setDouble(3, Math.random());
                preparedStatement.executeUpdate();
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Insertion without batch: " + duration + " milliseconds");
    }

    private void insertRecordsWithBatch() {
        long startTime = System.currentTimeMillis();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO " + TABLE_NAME + "(num1, num2, num3) VALUES (?, ?, ?)");

            for (int i = 0; i < RECORD_COUNT; i++) {
                preparedStatement.setDouble(1, Math.random());
                preparedStatement.setDouble(2, Math.random());
                preparedStatement.setDouble(3, Math.random());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Insertion with batch: " + duration + " milliseconds");
    }

    private String getPasswordFromUser() {
        // I can implement a secure way to obtain the password from the user but... do I really need tooo...
        // For simplicity, let's use a constant password here.
        return "password";
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
