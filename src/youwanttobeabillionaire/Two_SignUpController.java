/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youwanttobeabillionaire;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
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
    
    ArrayList<String> info = new ArrayList<>();
    private Statement sqlQuery;
    private Connection dataConnection;

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
    @FXML
    private Label nameWarn;
    @FXML
    private Label emailWarn;
    @FXML
    private Label passwordWarn;
    @FXML
    private Label passwordConfirmWarn;
    @FXML
    private Label securityQuestionWarn;
    @FXML
    private Label responseWarn;
    @FXML
    private Label responseConfirmWarn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submitResponse(ActionEvent event) throws IOException {
        
        
        String name, password, email, response, responseConfirm, sex, secQuestion;
        name = "";
        password = null;
        response = null;
        email = null;
        responseConfirm = null;
        secQuestion = null;
        
//        try {
            nameWarn.setStyle("-fx-text-fill: white;");
            passwordWarn.setStyle("-fx-text-fill: white;");
            responseWarn.setStyle("-fx-text-fill: white;");
            emailWarn.setStyle("-fx-text-fill: white;");
            responseConfirmWarn.setStyle("-fx-text-fill: white;");
            securityQuestionWarn.setStyle("-fx-text-fill: white;");
            
            
            name = twoName.getText();
            password = twoPassword.getText();
            response = twoResponse.getText();
            email = twoEmail.getText();
            responseConfirm = twoConfirmResponse.getText();
//            dataConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ywtbab", "root", "root");
//            sqlQuery = dataConnection.createStatement();
            
//            System.out.println(twoSecurityQuestion.getSelectionModel().getSelectedItem().toString());
            
            
            
            if (name.equals("")) 
                nameWarn.setStyle("-fx-text-fill: red;");
            else
                nameWarn.setText("");
            if (password.equals(""))
                passwordWarn.setStyle("-fx-text-fill: red;");
            else
                passwordWarn.setText("");
            if (response.equals(""))
                responseWarn.setStyle("-fx-text-fill: red;");
            else
                responseWarn.setText("");
            if (email.equals(""))
                emailWarn.setStyle("-fx-text-fill: red;");
            else
                emailWarn.setText("");
            if (twoConfirmResponse.equals(""))
                responseConfirmWarn.setStyle("-fx-text-fill: red;");
            else
                responseConfirmWarn.setText("");
            if (twoSecurityQuestion.getSelectionModel().getSelectedItem().toString() == null)
                securityQuestionWarn.setStyle("-fx-text-fill: red;");
            else
                securityQuestionWarn.setText("");
            if (twoEmail.getText().equals(""))
                emailWarn.setStyle("-fx-text-fill: red;");
            else
                emailWarn.setText("");
            if (!password.equals(twoConfirmPassword.getText())) {
                passwordConfirmWarn.setStyle("-fx-text-fill: blue;");
                passwordConfirmWarn.setText("*");
            }
            else
                passwordConfirmWarn.setText("");
            if(!response.equals(twoConfirmResponse.getText())) {
                responseConfirmWarn.setStyle("-fx-text-fill: blue;");
                responseConfirmWarn.setText("*");
            }
            else
                responseConfirmWarn.setText("");
            
            
            
            cancelToLandingPage(event);
            
            
//            ResultSet output = sqlQuery.executeQuery("INSERT INTO user_info ");
//            if (output.next()) {
//                if (output.getString("user").equals(name)) {   
//                    Parent questionPage = FXMLLoader.load(getClass().getResource("Threee_QuestionText.fxml"));
//                    Scene three = new Scene(questionPage);
//                    Stage quest = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                    quest.setScene(three);
//                    quest.setTitle("Game on!");
//                    quest.show();
//                } 
//                
//                } else {
//                
//            }
//            cancelToLandingPage(event);
//        }catch (SQLException ex) {
//            
//        }
 
    }

    @FXML
    public void cancelToLandingPage(ActionEvent event) throws IOException {
        Parent landingPage = FXMLLoader.load(getClass().getResource("One_LandingPage.fxml"));
        Scene landing = new Scene(landingPage);
        Stage land = (Stage) ((Node) event.getSource()).getScene().getWindow();
        land.setScene(landing);
        land.setTitle("You Want To Be A Billionaire?!");
        land.show();
    }
  
    
    
    
}
