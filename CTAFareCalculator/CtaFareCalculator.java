/* Program Name: CtaFareCalculator.java
   Author: Jose Ramirez
   Date Last Updated: 03/10/2023
   Summary: A Java program used to calculate the fare between two stations for a more affordable fare for commuters. (Like me :D)
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
    private int stationNumber;

    public Station(String stationName, String stationId, int stationNumber, double latitude, double longitude) {
        this.stationName = stationName;
        this.stationId = stationId;
        this.lines = new HashSet<>();
        this.stationNumber = stationNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Set<String> getLines() {
        return lines;
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public void removeLine(String line) {
        lines.remove(line);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public int getStationsBetween(Station otherStation, Set<String> commonLines) {
        if (!commonLines.isEmpty()) {
            Map<String, Integer> lineStationMap = new HashMap<>();

            for (String line : commonLines) {
                lineStationMap.put(line, 0);
            }

            // Initialize station numbers
            for (String line : commonLines) {
                lineStationMap.put(line, Math.abs(otherStation.getStationNumber(line) - this.getStationNumber()));
            }

            // Find the minimum station difference
            int minStations = Integer.MAX_VALUE;
            for (String line : commonLines) {
                minStations = Math.min(minStations, lineStationMap.get(line));
            }

            return minStations;
        } else {
            return 0;
        }
    }

    private int getStationNumber(String line) {
        String[] parts = this.stationName.split(":");
        if (parts.length == 2 && parts[0].trim().equals(line)) {
            try {
                return Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
}

class RideTransaction {
    private String transactionId;
    private User user;
    private Station startStation;
    private Station endStation;
    private double fareAmount;
    private String dateTime;
    private boolean paymentStatus;
    private String paymentDate;
    private String transactionType;
    private double baseFare = 0.50;
    private double farePerStation = 0.10;
    public RideTransaction(User user, Station startStation, Station endStation, double fareAmount, String dateTime) {
        this.transactionId = generateTransactionId();
        this.user = user;
        this.startStation = startStation;
        this.endStation = endStation;
        this.fareAmount = fareAmount;
        this.dateTime = dateTime;
        this.paymentStatus = false;
        this.paymentDate = null;
        this.transactionType = "Regular";
    }

    private String generateTransactionId() {
        // Logic to generate a unique transaction ID
        return "TXN" + System.currentTimeMillis();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    public double getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(double fareAmount) {
        this.fareAmount = fareAmount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isPaymentComplete() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}

public class CtaFareCalculator extends Application {

    private User user;
    private Station startStation;
    private Station endStation;
    private double fareAmount;

    private ComboBox<String> lineComboBox;
    private ComboBox<String> startStationComboBox;
    private ComboBox<String> endStationComboBox;

    @Override
    public void start(Stage primaryStage) {
        user = new User("JohnDoe", "password123", "john.doe@example.com");

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        lineComboBox = new ComboBox<>();
        lineComboBox.setPromptText("Select CTA Line");
        ObservableList<String> lines = FXCollections.observableArrayList("Red", "Blue", "Green", "Purple", "Yellow", "Brown", "Pink", "Orange");
        lineComboBox.setItems(lines);
        lineComboBox.setOnAction(event -> updateStations());

        startStationComboBox = new ComboBox<>();
        startStationComboBox.setPromptText("Departing Station");

        endStationComboBox = new ComboBox<>();
        endStationComboBox.setPromptText("Arriving Station");

        Button calculateFareButton = new Button("Calculate Fare");
        calculateFareButton.setOnAction(e -> calculateFare());

        root.getChildren().addAll(lineComboBox, startStationComboBox, endStationComboBox, calculateFareButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("CTA Fare App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateStations() {
        String selectedLine = lineComboBox.getValue();

        if (selectedLine != null) {
            // Simulate retrieving stations based on the selected CTA line
            Set<String> stations = getStationsByLine(selectedLine);

            // Update station ComboBoxes
            startStationComboBox.setItems(FXCollections.observableArrayList(stations));
            endStationComboBox.setItems(FXCollections.observableArrayList(stations));
        }
    }

    private Set<String> getStationsByLine(String line) {
        // Simulated method to get stations based on the selected CTA line
        Set<String> stations = new HashSet<>();
        switch (line) {
            case "Red":
                stations.add("1: 95th/Dan Ryan");
                stations.add("2: 87th");
                stations.add("3: 79th");
                stations.add("4: 69th");
                stations.add("5: 63rd");
                stations.add("6: Garfield");
                stations.add("7: 47th");
                stations.add("8: Sox-35th");
                stations.add("9: Cermak-Chinatown");
                stations.add("10: Roosevelt");
                stations.add("11: Harrison");
                stations.add("12: Jackson");
                stations.add("13: Monroe");
                stations.add("14: Lake");
                stations.add("15: Grand");
                stations.add("16: Chicago");
                stations.add("17: Clark/Division");
                stations.add("18: North/Clybourn");
                stations.add("19: Fullerton");
                stations.add("20: Belmont");
                stations.add("21: Addison");
                stations.add("22: Sheridan");
                stations.add("23: Wilson");
                stations.add("24: Lawrence");
                stations.add("25: Argyle");
                stations.add("26: Berwyn");
                stations.add("27: Bryn Mawr");
                stations.add("28: Thorndale");
                stations.add("29: Granwille");
                stations.add("30: Loyola");
                stations.add("31: Morse");
                stations.add("32: Jarvis");
                stations.add("33: Howard");
                break;
            case "Blue":
                stations.add("1: Forest Park");
                stations.add("2: Harlem(South)");
                stations.add("3: Oak Park");
                stations.add("4: Austin");
                stations.add("5: Cicero");
                stations.add("6: Pulaski");
                stations.add("7: Kedzie-Homan");
                stations.add("8: Western(South)");
                stations.add("9: Illinois Medical District");
                stations.add("10: Racine");
                stations.add("11: UIC-Halsted");
                stations.add("12: Clinton");
                stations.add("13: LaSalle");
                stations.add("14: Jackson");
                stations.add("15: Monroe");
                stations.add("16: Washington");
                stations.add("17: Clark/Lake");
                stations.add("18: Grand");
                stations.add("19: Chicago");
                stations.add("20: Division");
                stations.add("21: Damen");
                stations.add("22: Western(North)");
                stations.add("23: California");
                stations.add("24: Logan Square");
                stations.add("25: Belmont");
                stations.add("26: Addison");
                stations.add("27: Irving Park");
                stations.add("28: Montrose");
                stations.add("29: Jefferson Park");
                stations.add("30: Harlem(North)");
                stations.add("31: Cumberland");
                stations.add("32: Rosemont");
                stations.add("33: O'Hare");
                break;
            case "Green":
                stations.add("1: Harlem/Lake");
                stations.add("2: Oak Park");
                stations.add("3: Ridgeland");
                stations.add("4: Austin");
                stations.add("5: Central");
                stations.add("6: Laramie");
                stations.add("7: Cicero");
                stations.add("8: Pulaski");
                stations.add("9: Conservatory-Central Park Drive");
                stations.add("10: Kedzie");
                stations.add("11: California");
                stations.add("12: Ashland");
                stations.add("13: Morgan");
                stations.add("14: Clinton");
                stations.add("15: Clark/Lake");
                stations.add("16: State/lake");
                stations.add("17: Washington/Wabash");
                stations.add("18: Adams/Wabash");
                stations.add("19: Roosevelt");
                stations.add("20: Cermak-McCormick Place");
                stations.add("21: 35th-Bronzeville-IIT");
                stations.add("22: Indiana");
                stations.add("23: 43rd");
                stations.add("24: 47th");
                stations.add("25: 51st");
                stations.add("26: Garfield");
                stations.add("27: Halsted");
                stations.add("28: Ashland/63rd");
                stations.add("29: King Drive");
                stations.add("30: Cottage Grove");
                break;
            case "Purple":
                stations.add("1: Linden");
                stations.add("2: Central");
                stations.add("3: Noyes");
                stations.add("4: Foster");
                stations.add("5: Davis");
                stations.add("6: Dempster");
                stations.add("7: Main");
                stations.add("8: South Boulevard");
                stations.add("9: Howard");
                stations.add("10: Wilson");
                stations.add("11: Belmont");
                stations.add("12: Wellington");
                stations.add("13: Diversey");
                stations.add("14: Fullerton");
                stations.add("15: Armitage");
                stations.add("16: Sedgwick");
                stations.add("17: Chicago");
                stations.add("18: Merchandise Mart");
                stations.add("19: Washington/Wells");
                stations.add("20: Quincy");
                stations.add("21: LaSalle/VanBuren");
                stations.add("22: Harold Washington Library");
                stations.add("23: Adams/Wabash");
                stations.add("24: Washington/Wabash");
                stations.add("25: State/Lake");
                stations.add("26: Clark/Lake");
                break;
            case "Yellow":
                stations.add("1: Dempster-Skokie");
                stations.add("2: Oakton-Skokie");
                stations.add("3: Howard");
                break;
            case "Brown":
                stations.add("1: Kimball");
                stations.add("2: Kedzie");
                stations.add("3: Francisco");
                stations.add("4: Rockwell");
                stations.add("5: Western");
                stations.add("6: Damen");
                stations.add("7: Montrose");
                stations.add("8: Irving Park");
                stations.add("9: Addison");
                stations.add("10: Paulina");
                stations.add("11: Southport");
                stations.add("12: Belmont");
                stations.add("13: Wellington");
                stations.add("14: Diversey");
                stations.add("15: Fullerton");
                stations.add("16: Armitage");
                stations.add("17: Sedgwick");
                stations.add("18: Chicago");
                stations.add("19: Washington/Wells (Brown)");
                stations.add("20: Quincy(Brown)");
                stations.add("21: LaSalle/VanBuren(Brown)");
                stations.add("22: Harold Washington Library(Brown)");
                stations.add("23: Adams/Wabash(Brown)");
                stations.add("24: Washington/Wabash(Brown)");
                stations.add("25: State/Lake(Brown)");
                stations.add("26: Clark/Lake(Brown)");
                break;
            case "Pink":
                stations.add("1: 54th/Cermak");
                stations.add("2: Cicero");
                stations.add("3: Kostner");
                stations.add("4: Pulaski");
                stations.add("5: Central Park");
                stations.add("6: Kedzie");
                stations.add("7: California");
                stations.add("8: Western");
                stations.add("9: Damen");
                stations.add("10: 18th");
                stations.add("11: Polk");
                stations.add("12: Ashland(Pink)");
                stations.add("13: Morgan(Pink)");
                stations.add("14: Clinton(Pink)");
                stations.add("15: Clark/Lake(Pink)");
                stations.add("16: State/Lake(Pink)");
                stations.add("17: Washington/Wabash(Pink)");
                stations.add("18: Adams/Wabash(Pink)");
                stations.add("19: Harold Washington Library(Pink)");
                stations.add("20: LaSalle/VanBuren(Pink)");
                stations.add("21: Quincy(Pink)");
                stations.add("22: Washington/Wells(Pink)");
                break;
            case "Orange":
                stations.add("1: Midway");
                stations.add("2: Pulaski");
                stations.add("3: Kedzie");
                stations.add("4: Western");
                stations.add("5: 35th/Archer");
                stations.add("6: Ashland");
                stations.add("7: Halsted");
                stations.add("8: Roosevelt");
                stations.add("9: Harold Washington Library(Orange)");
                stations.add("10: LaSalle/VanBuren(Orange)");
                stations.add("11: Quincy(Orange)");
                stations.add("12: Washington/Wells(Orange)");
                stations.add("13: Clark/Lake(Orange)");
                stations.add("14: State/Lake(Orange)");
                stations.add("15: Washington/Wabash(Orange)");
                stations.add("16: Adams/Wabash(Orange)");
                break;
        }
        return stations;
    }

    private void calculateFare() {
        String selectedLine = lineComboBox.getValue();
        String selectedStartStation = startStationComboBox.getValue();
        String selectedEndStation = endStationComboBox.getValue();

        if (selectedLine != null && selectedStartStation != null && selectedEndStation != null) {
            // Simulate calculating fare based on the selected stations
            startStation = new Station(selectedStartStation, "STN001",1, 0.0, 0.0);
            endStation = new Station(selectedEndStation, "STN002",1, 0.0, 0.0);

            // Simulate calculating fare amount
            fareAmount = calculateFareAmount(selectedStartStation, selectedEndStation);

            // Display fare information
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fare Calculation");
            alert.setHeaderText(null);
            alert.setContentText("Your fare for this trip will be: $" + fareAmount);
            alert.showAndWait();
        } else {
            // Display an error message if any selection is missing
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select CTA Line, Departing Station, and Arriving Station.");
            alert.showAndWait();
        }
    }

    private double calculateFareAmount(String startStation, String endStation) {
        return 2.50;
    }

    public static void main(String[] args) {
        launch(args);
    }
}