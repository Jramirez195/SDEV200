/* Program Name: CtaFareCalculator.java
   Author: Jose Ramirez
   Last Updated: 2/18/2024
   Summary: The draft on the CTA Fare Calculator for my Final Project. This is not final, and it looks very sketchy, but it's just the start!
 */

package CTAFareCalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

class User {
    private String username;
    private String password;
    private String email;
    private double fareBalance;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fareBalance = 0.0;
    }

    // Getters and setters

    public double getFareBalance() {
        return fareBalance;
    }

    public void setFareBalance(double fareBalance) {
        this.fareBalance = fareBalance;
    }
}

class Station {
    private String stationName;
    private String stationId;
    private Set<String> lines;
    private double latitude;
    private double longitude;

    public Station(String stationName, String stationId, double latitude, double longitude) {
        this.stationName = stationName;
        this.stationId = stationId;
        this.lines = new HashSet<>();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and setters

    public Set<String> getLines() {
        return lines;
    }

    public void addLine(String line) {
    }

    public void removeLine(String line) {
        lines.remove(line);
    }
}

class RideTransaction {
    private String transactionId;
    private User user;
    private Station startStation;
    private Station endStation;
    private double fareAmount;
    private boolean paymentStatus;
    private String transactionType;

    public RideTransaction(User user, Station startStation, Station endStation, double fareAmount, String transactionType) {
        this.transactionId = generateTransactionId();
        this.user = user;
        this.startStation = startStation;
        this.endStation = endStation;
        this.fareAmount = fareAmount;
        this.paymentStatus = false;
        this.transactionType = transactionType;
    }

    private String generateTransactionId() {
        // Logic to generate a unique transaction ID
        return "TXN" + System.currentTimeMillis();
    }

    // Getters and setters

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

public class CtaFareCalculator extends Application {

    private User currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize sample data
        User user = new User("john_doe", "password123", "john@example.com");
        Station stationA = new Station("Station A", "STA001", 41.8781, -87.6298);
        stationA.addLine("Red Line");
        stationA.addLine("Blue Line");

        Station stationB = new Station("Station B", "STA002", 41.8676, -87.6426);
        stationB.addLine("Red Line");

        RideTransaction rideTransaction = new RideTransaction(user, stationA, stationB, 2.50, "Single Ride");

        // Set current user for demonstration purposes
        currentUser = user;

        // Create JavaFX elements
        Label fareLabel = new Label("Fare Balance: $" + currentUser.getFareBalance());

        Button calculateFareButton = new Button("Calculate Fare");
        calculateFareButton.setOnAction(e -> calculateFare());

        Button payButton = new Button("Pay Fare");
        payButton.setOnAction(e -> payFare(rideTransaction));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(fareLabel, calculateFareButton, payButton);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CTA Fare Calculator");
        primaryStage.show();
    }

    private void calculateFare() {
        // Logic to calculate fare based on selected stations and update fareLabel
        // For demonstration, updating fare balance by a fixed amount
        currentUser.setFareBalance(currentUser.getFareBalance() + 2.50);
        System.out.println("Fare Calculated. New balance: $" + currentUser.getFareBalance());
    }

    private void payFare(RideTransaction rideTransaction) {
        // Logic to process payment and update payment status
        rideTransaction.setPaymentStatus(true);
        System.out.println("Payment Successful. Fare Paid.");
    }
}
