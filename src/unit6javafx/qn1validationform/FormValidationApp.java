package unit6javafx.qn1validationform;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FormValidationApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Form Validation");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();

        Button submitBtn = new Button("Submit");
        Label messageLabel = new Label();

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);

        grid.add(ageLabel, 0, 2);
        grid.add(ageField, 1, 2);

        grid.add(submitBtn, 1, 3);
        grid.add(messageLabel, 1, 4);

        submitBtn.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String age = ageField.getText();

            if (name.isEmpty()) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Name cannot be empty!");
            } else if (!email.contains("@")) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Invalid email!");
            }else if (!age.matches("\\d+")) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Age must be a number!");
            }
            else if (Integer.parseInt(age) <= 0) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Age must not be negative or zero!");
            } else {
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("Form submitted successfully!");
            }
        });

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Main method
    public static void main(String[] args) {
        launch(args);
    }
}
