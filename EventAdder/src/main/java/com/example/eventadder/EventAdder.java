package com.example.eventadder;

// Imports needed for the UI
import javafx.application.Application; // The main class for JavaFX applications
import javafx.geometry.Insets; // Padding
import javafx.scene.Scene; // Container for all the components
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane; // Layout containers to arrange the UI components
import javafx.scene.layout.HBox; // Layout containers to arrange the UI components
import javafx.scene.layout.VBox; // Layout containers to arrange the UI components
import javafx.stage.Stage; // For the main window

public class EventAdder extends Application {
    private DatePicker datePicker;
    private ComboBox<String> categoryComboBox;
    private TextField descriptionField;
    private Button addButton;

    // Overriding the start method to create the UI
    @Override
    public void start(Stage stage) {
        // Create DatePicker
        Label dateLabel = new Label("Date:");
        datePicker = new DatePicker();

        // Creating the ComboBox for a drop-down menu for the categories
        Label categoryLabel = new Label("Category:");
        categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Work", "Personal", "School", "Other");

        // Added the option to choose and add a new category to the list
        Label newCategoryLabel = new Label("New Category:");
        TextField newCategoryField = new TextField();

        // If statement to only allow the user to add a new category
        // if he chose the "Other" option
        categoryComboBox.setOnAction(e -> {
            if (categoryComboBox.getValue().equals("Other")) {
                newCategoryField.setEditable(true);
            } else {
                newCategoryField.setEditable(false);
            }
        });

        // Adding a TextField for the events description
        Label descriptionLabel = new Label("Description:");
        descriptionField = new TextField();

        // Adding a button
        addButton = new Button("Add Event");
        addButton.setOnAction(e -> addEvent());

        // Layout controls
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.addRow(0, dateLabel, datePicker);
        grid.addRow(1, categoryLabel, categoryComboBox);
        grid.addRow(2, newCategoryLabel, newCategoryField);
        grid.addRow(3, descriptionLabel, descriptionField);

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().add(addButton);

        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(grid, buttonBox);

        // Show scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Event Adder");
        stage.show();
    }

    private void addEvent() {
        String date = datePicker.getValue().toString();
        String category = categoryComboBox.getValue().equals("Other") ? categoryComboBox.getEditor().getText() : categoryComboBox.getValue();
        String description = descriptionField.getText();

        // Displaying the data in terminal
        System.out.println("Added event: " + date + " " + category + " " + description);
    }

    public static void main(String[] args) {
        launch();
    }
}
