import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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

    public Station(String stationName, String stationId, double latitude, double longitude) {
        this.stationName = stationName;
        this.stationId = stationId;
        this.lines = new HashSet<>();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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
                stations.add("95th/Dan Ryan");
                stations.add("87th");
                stations.add("79th");
                stations.add("69th");
                stations.add("63rd");
                stations.add("Garfield");
                stations.add("47th");
                stations.add("Sox-35th");
                stations.add("Cermak-Chinatown");
                stations.add("Roosevelt");
                stations.add("Harrison");
                stations.add("Jackson");
                stations.add("Monroe");
                stations.add("Lake");
                stations.add("Grand");
                stations.add("Chicago");
                stations.add("Clark/Division");
                stations.add("North/Clybourn");
                stations.add("Fullerton");
                stations.add("Belmont");
                stations.add("Addison");
                stations.add("Sheridan");
                stations.add("Wilson");
                stations.add("Lawrence");
                stations.add("Argyle");
                stations.add("Berwyn");
                stations.add("Bryn Mawr");
                stations.add("Thorndale");
                stations.add("Granwille");
                stations.add("Loyola");
                stations.add("Morse");
                stations.add("Jarvis");
                stations.add("Howard");
                break;
            case "Blue":
                stations.add("Forest Park");
                stations.add("Harlem(South)");
                stations.add("Oak Park");
                stations.add("Austin");
                stations.add("Cicero");
                stations.add("Pulaski");
                stations.add("Kedzie-Homan");
                stations.add("Western(South)");
                stations.add("Illinois Medical District");
                stations.add("Racine");
                stations.add("UIC-Halsted");
                stations.add("Clinton");
                stations.add("LaSalle");
                stations.add("Jackson");
                stations.add("Monroe");
                stations.add("Washington");
                stations.add("Clark/Lake");
                stations.add("Grand");
                stations.add("Chicago");
                stations.add("Division");
                stations.add("Damen");
                stations.add("Western(North)");
                stations.add("California");
                stations.add("Logan Square");
                stations.add("Belmont");
                stations.add("Addison");
                stations.add("Irving Park");
                stations.add("Montrose");
                stations.add("Jefferson Park");
                stations.add("Harlem(North)");
                stations.add("Cumberland");
                stations.add("Rosemont");
                stations.add("O'Hare");
                break;
            case "Green":
                stations.add("Station P");
                stations.add("Station Q");
                stations.add("Station R");
                break;
            // Add more cases for other CTA lines if needed
        }
        return stations;
    }

    private void calculateFare() {
        String selectedLine = lineComboBox.getValue();
        String selectedStartStation = startStationComboBox.getValue();
        String selectedEndStation = endStationComboBox.getValue();

        if (selectedLine != null && selectedStartStation != null && selectedEndStation != null) {
            // Simulate calculating fare based on the selected stations
            startStation = new Station(selectedStartStation, "STN001", 0.0, 0.0);
            endStation = new Station(selectedEndStation, "STN002", 0.0, 0.0);

            // Simulate calculating fare amount
            fareAmount = calculateFareAmount(selectedStartStation, selectedEndStation);

            // Display fare information (you can customize this part as needed)
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fare Calculation");
            alert.setHeaderText(null);
            alert.setContentText("Fare Amount: $" + fareAmount);
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
        // Simulated method to calculate fare amount based on selected stations
        // You can implement your own logic here
        return 2.50; // Placeholder value, replace with actual calculation
    }

    public static void main(String[] args) {
        launch(args);
    }
}