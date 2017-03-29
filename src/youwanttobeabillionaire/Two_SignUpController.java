/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youwanttobeabillionaire;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class Two_SignUpController implements Initializable {

    private static final ArrayList<String> info = new ArrayList<>(7);
    private static final ArrayList<String> users = new ArrayList<>();
    private Statement sqlQuery;
    private Connection dataConnection;
    private String dob;

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
    @FXML
    private Label warn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dataConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ywtbab", "root", "root");
            Statement query = dataConnection.createStatement();
            ResultSet output = query.executeQuery("select user from user_info");
            while (output.next()) {
                String n = output.getString("user");
                if (!users.contains(n)) {
                    users.add(n);
                }
            }
        } catch (SQLException ex) {
            warn.setText("Database May Not Exist");
        }

        for (int i = 0; i < 7; i++) {
            info.add(null);
        }
    }

    @FXML
    private void submitResponse(ActionEvent event) throws IOException, MySQLIntegrityConstraintViolationException {
        String name, password, response, email, responseConfirm, sex, secQuestion, passwordConfirm;

        name = password = response = email = sex = secQuestion = passwordConfirm = null;

        name = twoName.getText();
        password = twoPassword.getText();
        response = twoResponse.getText();
        email = twoEmail.getText();
        secQuestion = sex = dob = null;
        passwordConfirm = twoConfirmPassword.getText();

        try {
            secQuestion = twoSecurityQuestion.getSelectionModel().getSelectedItem().toString();
            RadioButton selected = (RadioButton) userSex.getSelectedToggle();
            dob = twoDOB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String gender = selected.getText();
            if (gender.equals("Female")) {
                sex = "F";
            } else {
                sex = "M";
            }
        } catch (Exception e) {
            // values set to null
        }

        if (users.contains(name)) {
            warn.setText("User Exists");
        }
        if (name.length() > 0 && !users.contains(name)) {
            nameWarn.setStyle("-fx-text-fill: lime;");
            info.set(0, name);
        } else {
            nameWarn.setStyle("-fx-text-fill: red;");
        }

        if (password.length() > 5) {
            passwordWarn.setStyle("-fx-text-fill: lime;");

        } else {
            passwordWarn.setStyle("-fx-text-fill: red;");
        }

        if ((email.length() > 0) && email.contains("@") && email.contains(".")) {
            emailWarn.setStyle("-fx-text-fill: lime;");
            info.set(4, email);
        } else {
            emailWarn.setStyle("-fx-text-fill: red;");
        }

        if (!password.equals(passwordConfirm) | passwordConfirm.length() < 5) {
            passwordConfirmWarn.setText("*");
            passwordConfirmWarn.setStyle("-fx-text-fill: yellow;");
        } else {
            passwordConfirmWarn.setText("*");
            passwordConfirmWarn.setStyle("-fx-text-fill: lime;");
            info.set(1, password);
        }

        if (response.length() > 1) {
            responseWarn.setStyle("-fx-text-fill: lime;");
        } else {
            responseWarn.setStyle("-fx-text-fill: red;");
        }

        if (secQuestion == null) {
            securityQuestionWarn.setStyle("-fx-text-fill: red");
        } else {
            securityQuestionWarn.setStyle("-fx-text-fill: lime");
        }

        info.set(2, dob);
        info.set(3, sex);
        info.set(5, secQuestion);
        info.set(6, response);

        System.out.println(info);

        if (name == null || password == null || email == null || response == null || secQuestion == null) {
            // stay on the page                        
        } else {
            try {
                Connection dataConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ywtbab", "root", "root");
                PreparedStatement query = dataConnection.prepareStatement("insert into user_info set user=?, password=?, dob=?, sex=?, email=?, sec_question=?, sec_question_response=?");
                query.setString(1, info.get(0));
                query.setString(2, info.get(1));
                query.setString(3, info.get(2));
                query.setString(4, info.get(3));
                query.setString(5, info.get(4));
                query.setString(6, info.get(5));
                query.setString(7, info.get(6));
                query.execute();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initModality(Modality.WINDOW_MODAL);
                alert.setTitle("Success!");
                alert.setHeaderText("Successfully Registered");
                alert.setContentText("You can now log in! :)");
                alert.showAndWait();
                cancelToLandingPage(event);
            } catch (SQLException ex) {
                //database error
            } catch (Exception e) {
                // GUI deals with the error
            }
        }
    }

    @FXML
    public void cancelToLandingPage(ActionEvent event) throws IOException {
        Parent landingPage = FXMLLoader.load(getClass().getResource("One_LandingPage.fxml"));
        Scene landing = new Scene(landingPage);
        Stage land = (Stage) ((Node) event.getSource()).getScene().getWindow();
        land.setScene(landing);
        land.setTitle("You Want To Be A Billionaire?!");
        land.show();
        info.clear();
    }

}
