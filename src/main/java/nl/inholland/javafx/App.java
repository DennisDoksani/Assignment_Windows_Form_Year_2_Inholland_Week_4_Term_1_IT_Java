package nl.inholland.javafx;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

import static java.awt.Color.*;


public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("In holland JavaFX Starter Project");

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label usernameLabel = new Label("Username:");
        gridPane.setConstraints(usernameLabel,0,0);

        Label passwordLabel = new Label("Password:");
        gridPane.setConstraints(passwordLabel,0,1);

        TextField usernameInput = new TextField();
        System.out.println(usernameInput.getText());
        usernameInput.setPromptText("username");
        gridPane.setConstraints(usernameInput,1,0);

        PasswordField passwordField = new PasswordField();
        System.out.println(passwordField.getText());
        passwordField.setPromptText("enter password");
        gridPane.setConstraints(passwordField,1,1);

        Button loginButton = new Button("Log in");
        GridPane.setConstraints(loginButton,1,3);

        Label secretLabel = new Label();
        GridPane.setConstraints(secretLabel,0,3);

        Label errorLabel = new Label();
        GridPane.setConstraints(errorLabel,1,2);


        gridPane.getChildren().add(usernameLabel);
        gridPane.getChildren().add(passwordLabel);
        gridPane.getChildren().add(usernameInput);
        gridPane.getChildren().add(passwordField);
        gridPane.getChildren().add(loginButton);
        gridPane.getChildren().add(secretLabel);
        gridPane.getChildren().add(errorLabel);

        //create a StringProperty
        StringProperty passwordProperty = passwordField.textProperty();
        loginButton.setVisible(false);
        //add the listener to this property
        passwordProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldValue, String newValue)
            {
                //errorLabel.setTextFill(red);  <--- Don't know why when I try to change the color there is an error popping up? :/
                String error = errorLabel.getText();
                String password = passwordField.getText();


                boolean validChars = password.matches(".*[a-zA-Z]+.*");
                boolean validNums = password.matches(".*[0-9]+.*");
                boolean validSymbol = password.matches(".*[!@#$%^&*()-+]");
                loginButton.setVisible(false);
                if(password.length() <= 8)
                {
                    error = "Please enter more than 8 characters";
                    errorLabel.setText(error);
                }
                else if(validChars == false && validNums && validSymbol)
                {
                    error = "You are missing a lower or an upper case letter";
                    errorLabel.setText(error);
                }
                else if (validSymbol == false && validChars && validNums)
                {
                    error = "You are missing a special character";
                    errorLabel.setText(error);
                }
                else if(validNums == false && validChars && validSymbol)
                {
                    error = "Your password must require at least a number character";
                    errorLabel.setText(error);
                }
                else if (validChars && validNums && validSymbol && password.length() >= 8)
                {
                    error = "";
                    errorLabel.setText(error);
                    loginButton.setVisible(true);
                }

            }
        });
        //bind the listener to the text property of the new label
        secretLabel.textProperty().bind(passwordProperty);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                System.out.println(usernameInput.getText());
            }
        });


        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();
    }
}
