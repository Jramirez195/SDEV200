/* Program Name: staffTable.java
   Author: Jose Ramirez
   Date Last Updated: 2/27/2024
   Summary: This JavaFX application facilitates the management of staff information by providing functionality.
 */

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.sql.*;

public class staffTable extends Application {

    // Database connection parameters
    private static final String DB_URL = "jdbc:your_database_url_here";
    private static final String USER = "your_username_here";
    private static final String PASSWORD = "your_password_here";

    // JavaFX components
    private TextField idField;
    private TextField lastNameField;
    private TextField firstNameField;
    private TextField miField;
    private TextField addressField;
    private TextField cityField;
    private TextField stateField;
    private TextField telephoneField;
    private TextField emailField;
    private Button viewButton, insertButton, updateButton;
    private TableView<StaffRecord> tableView;
    private ObservableList<StaffRecord> staffData;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Staff Management System");

        // Initialize components
        idField = new TextField();
        lastNameField = new TextField();
        firstNameField = new TextField();
        miField = new TextField();
        addressField = new TextField();
        cityField = new TextField();
        stateField = new TextField();
        telephoneField = new TextField();
        emailField = new TextField();

        viewButton = new Button("View");
        insertButton = new Button("Insert");
        updateButton = new Button("Update");

        tableView = new TableView<>();
        staffData = FXCollections.observableArrayList();

        // The set-up for the table columns.
        TableColumn<StaffRecord, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<StaffRecord, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<StaffRecord, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<StaffRecord, String> miColumn = new TableColumn<>("Mi");
        miColumn.setCellValueFactory(cellData -> cellData.getValue().miProperty());

        TableColumn<StaffRecord, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        TableColumn<StaffRecord, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());

        TableColumn<StaffRecord, String> stateColumn = new TableColumn<>("State");
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());

        TableColumn<StaffRecord, String> telephoneColumn = new TableColumn<>("Telephone");
        telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());

        TableColumn<StaffRecord, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());


        // More columns for other fields in a similar way

        tableView.getColumns().addAll(idColumn, lastNameColumn, firstNameColumn, miColumn, addressColumn, cityColumn, stateColumn, telephoneColumn, emailColumn);

        // Button actions
        viewButton.setOnAction(e -> viewRecord());
        insertButton.setOnAction(e -> insertRecord());
        updateButton.setOnAction(e -> updateRecord());

        // Set up layout
        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        inputGrid.add(new Label("ID:"), 0, 1);
        inputGrid.add(idField, 1, 1);

        inputGrid.add(new Label("Last Name:"), 0, 2);
        inputGrid.add(lastNameField, 1, 2);

        inputGrid.add(new Label("First Name:"), 2,2);
        inputGrid.add(firstNameField, 3, 2);

        inputGrid.add(new Label("MI:"), 4, 2);
        inputGrid.add(miField, 5,2);

        inputGrid.add(new Label("Address:"), 0, 3);
        inputGrid.add(addressField, 1, 3);

        inputGrid.add(new Label("City:"), 0, 4);
        inputGrid.add(cityField, 1,4);

        inputGrid.add(new Label("State:"), 2, 4);
        inputGrid.add(stateField, 3, 4);

        inputGrid.add(new Label("Telephone:"), 0, 5);
        inputGrid.add(telephoneField, 1, 5);

        inputGrid.add(new Label("Email:"), 2, 5);
        inputGrid.add(emailField, 3, 5);

        // More fields to the grid

        inputGrid.add(viewButton, 0, 10);
        inputGrid.add(insertButton, 1, 10);
        inputGrid.add(updateButton, 2, 10);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(inputGrid, tableView);

        Scene scene = new Scene(vBox, 700, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    // Will work on this in the future, It's really fun and will like to work on a working table!!
    private void viewRecord() {
    }

    private void insertRecord() {
    }

    private void updateRecord() {
    }
    // A class to represent a Staff record
    public static class StaffRecord {
        private final StringProperty id;
        private final StringProperty lastName;

        private final StringProperty firstName;

        private final StringProperty mi;

        private final StringProperty address;

        private final StringProperty city;

        private final StringProperty state;

        private final StringProperty telephone;

        private final StringProperty email;

        // More properties for other fields in a similar way

        public StaffRecord(String id, String lastName, String firstName, String mi, String address, String city, String state, String telephone, String email) {
            this.id = new SimpleStringProperty(id);
            this.lastName = new SimpleStringProperty(lastName);
            this.firstName = new SimpleStringProperty(firstName);
            this.mi = new SimpleStringProperty(mi);
            this.address = new SimpleStringProperty(address);
            this.city = new SimpleStringProperty(city);
            this.state = new SimpleStringProperty(state);
            this.telephone = new SimpleStringProperty(telephone);
            this.email = new SimpleStringProperty(email);
        }

        public StringProperty idProperty() {return id;}
        public StringProperty lastNameProperty() {return lastName;}
        public StringProperty firstNameProperty() {return firstName;}
        public StringProperty miProperty() {return mi;}
        public StringProperty addressProperty() {return address;}
        public StringProperty cityProperty() {return city;}
        public StringProperty stateProperty() {return state;}
        public StringProperty telephoneProperty() {return telephone;}
        public StringProperty emailProperty() {return email;}
    }
}
