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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class Two_SignUpController implements Initializable {

    @FXML
    private DatePicker twoDOB;
    @FXML
    private RadioButton userMale;
    @FXML
    private ToggleGroup userSex;
    @FXML
    private RadioButton userFemale;
    @FXML
    private Button twoSubmit;
    @FXML
    private Button twoCancel;
    @FXML
    private TextField twoName;
    @FXML
    private TextField twoEmail;
    @FXML
    private PasswordField twoPassword;
    @FXML
    private PasswordField twoConfirmPassword;
    @FXML
    private ComboBox<?> twoSecurityQuestion;
    @FXML
    private TextField twoResponse;
    @FXML
    private TextField twoConfirmResponse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submitResponse(ActionEvent event) {
    }

    @FXML
    private void cancelToLandingPage(ActionEvent event) throws IOException {
        Parent landingPage = FXMLLoader.load(getClass().getResource("One_LandingPage.fxml"));
        Scene landing = new Scene(landingPage);
        Stage land = (Stage) ((Node) event.getSource()).getScene().getWindow();
        land.setScene(landing);
        land.setTitle("You Want To Be A Billionaire?!");
        land.show();
    }
    
}
