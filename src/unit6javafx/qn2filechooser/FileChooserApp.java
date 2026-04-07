package unit6javafx.qn2filechooser;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileChooserApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX File Chooser");

        Label fileLabel = new Label("No file selected");
        Button chooseBtn = new Button("Choose File");

        chooseBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");

            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                fileLabel.setText("Selected File: " + selectedFile.getAbsolutePath());
            } else {
                fileLabel.setText("No file selected");
            }
        });

        VBox vbox = new VBox(15, chooseBtn, fileLabel);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 500, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}