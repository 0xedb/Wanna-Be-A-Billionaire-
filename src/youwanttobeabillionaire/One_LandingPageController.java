/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youwanttobeabillionaire;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruno
 */

public class One_LandingPageController implements Initializable {
    
    private Statement sqlQuery;
    private Connection dataConnection;
    
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
    @FXML
    private AnchorPane oneScreen;
    @FXML
    private Label loginWarn;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void userLogin(ActionEvent event) throws IOException, SQLException {
        //validate user input
        String name, password;
        name = oneName.getText();
        password = onePassword.getText();
        
        
        try {
            dataConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ywtbab", "root", "root");
            sqlQuery = dataConnection.createStatement();
            
            ResultSet output = sqlQuery.executeQuery("SELECT user, password FROM user_info WHERE (user =" +
                                                    "'" + name + "'" + " AND password='" + password + "')");
            if (output.next()) {
                if (output.getString("user").equals(name)) {      
                    System.out.println("Yesss");
                    Parent questionPage = FXMLLoader.load(getClass().getResource("Threee_QuestionText.fxml"));
                    Scene three = new Scene(questionPage);
                    Stage quest = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    quest.setScene(three);
                    quest.setTitle("Game on!");
                    quest.show();
                } 
                
                } else {
                    loginWarn.setText("*Login Credentials incorrect*");
            }
        }catch (SQLException ex) {
            loginWarn.setText("*You did something very terrible*");
        }
        
    }

    @FXML
    private void adminLogin(ActionEvent event) {
        oneScreen.setOpacity(0.3);
        onePassword.clear();
        oneName.clear();
        loginWarn.setText("");
        Dialog<String> adminOpen = new Dialog<>();
        adminOpen.setTitle("Admin LogIn");
        adminOpen.setHeaderText("Enter the ADMIN password to open the admin panel");
        
        PasswordField pswd = new PasswordField();
        pswd.setPromptText("        *Password*");
        pswd.setText("");
        
        GridPane pane = new GridPane();
        pane.add(pswd, 1, 1);
        pane.setAlignment(Pos.CENTER);
        adminOpen.getDialogPane().setContent(pane);
        pane.setStyle("-fx-background-color:  #B1D6FFE6;");
        
        ButtonType btnLogin = new ButtonType("LogIn", ButtonData.OK_DONE);
        ButtonType btnCancel = new ButtonType("Cancel");
        adminOpen.getDialogPane().getButtonTypes().addAll(btnCancel, btnLogin);
        
        
        @SuppressWarnings("unchecked")
        Optional<String> result = adminOpen.showAndWait();
        if (!"".equals(pswd.getText()) && result.isPresent()) {
            System.out.println(pswd.getText());
            oneScreen.setOpacity(1);
        }
        else {
            System.out.println("something went wrong");
            oneScreen.setOpacity(1);
        }
            
        
        
        
        

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