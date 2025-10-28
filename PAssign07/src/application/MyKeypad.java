package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class MyKeypad extends Application{
	
	private static final String CorrectPin = "1234";
	private StringBuilder enteredPin = new StringBuilder();
	
	@Override
	public void start(Stage primaryStage) {
		Label titleLabel = new Label("Bank ATM");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label promptLabel = new Label("Enter your 4-digit PIN:");
        TextField pinField = new TextField();
        pinField.setEditable(false);
        pinField.setStyle("-fx-font-size: 18px; -fx-alignment: center;");

        Label statusLabel = new Label();

        KeyPadPane keypad = new KeyPadPane();

        keypad.getChildren().forEach(node -> {
            if (node instanceof Button btn) {
                btn.setOnAction(e -> {
                    String key = btn.getText();
                    if (key.matches("\\d")) {
                        if (enteredPin.length() < 4) {
                            enteredPin.append(key);
                            pinField.setText("*".repeat(enteredPin.length()));
                        }
                    }
                });
            }
        });

        Button enterBtn = new Button("Enter");
        Button clearBtn = new Button("Clear");

        enterBtn.setOnAction(e -> {
            if (enteredPin.toString().equals(CorrectPin)) {
                statusLabel.setText("Access Granted");
            } else {
                statusLabel.setText("Access Denied");
            }
        });

        clearBtn.setOnAction(e -> {
            enteredPin.setLength(0);
            pinField.clear();
            statusLabel.setText("");
        });

        HBox buttonRow = new HBox(10, enterBtn, clearBtn);
        buttonRow.setStyle("-fx-alignment: center;");

        VBox layout = new VBox(15, titleLabel, promptLabel, pinField, keypad, buttonRow, statusLabel);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 500);
        primaryStage.setTitle("ATM PIN Entry");
        primaryStage.setScene(scene);
        primaryStage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}
}
