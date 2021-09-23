package nl.inholland.javafx;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("In holland JavaFX Starter Project");


        GridPane gridPane = new GridPane();

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
        GridPane.setConstraints(loginButton,1,2);

        Label secretLabel = new Label();
        GridPane.setConstraints(secretLabel,0,3);


        gridPane.getChildren().add(usernameLabel);
        gridPane.getChildren().add(passwordLabel);
        gridPane.getChildren().add(usernameInput);
        gridPane.getChildren().add(passwordField);
        gridPane.getChildren().add(loginButton);
        gridPane.getChildren().add(secretLabel);

        //create a StringProperty
        StringProperty passwordProperty = passwordField.textProperty();

        //add the listener to this property
        passwordProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldValue, String newValue)
            {
                loginButton.setVisible(secretLabel.getText().length() != 0);
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
