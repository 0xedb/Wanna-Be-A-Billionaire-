/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youwanttobeabillionaire;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class One_LandingPageController implements Initializable {

    @FXML
    private TextField oneName;
    @FXML
    private PasswordField onePassword;
    @FXML
    private Button oneLogin;
    @FXML
    private Hyperlink oneAdmin;
    @FXML
    private Button oneSignup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void userLogin(ActionEvent event) {
    }

    @FXML
    private void adminLogin(ActionEvent event) {
    }

    @FXML
    private void userSingup(ActionEvent event) throws IOException {
        Parent signUpPage = FXMLLoader.load(getClass().getResource("Two_SignUp.fxml"));
        Scene one = new Scene(signUpPage);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(one);
        primaryStage.setTitle("Sign-Up Here");
        primaryStage.show();
    }
    
}
