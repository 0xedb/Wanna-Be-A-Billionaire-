/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youwanttobeabillionaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bruno
 */
public class YouWantToBeABillionaire extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        try {
            Connection dataConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ywtbab", "root", "root");
            Statement query = dataConnection.createStatement();
            
            ResultSet output = query.executeQuery("select * from user_info");
            
            while (output.next()) {
                System.out.println(output.getString("user"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(One_LandingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("One_LandingPage.fxml"));
        
        Scene scene = new Scene(root);
        stage.setResizable(false);
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Sacramento");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Nova+Oval");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
