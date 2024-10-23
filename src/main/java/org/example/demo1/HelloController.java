package org.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    @FXML
    private Button loginbtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
//    @FXML
//    private Label welcomeText;
//    @FXML
//    private Label display_label;
//    @FXML
//    private AnchorPane container;


//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
//    @FXML
//    protected void onClickMeButtonClick() {
//        display_label.setText("You have clicked me!");
//    }
//
//    @FXML
//    protected void onChangeButtonClick() {
//          container.setRotate(63.0);
//    }

}