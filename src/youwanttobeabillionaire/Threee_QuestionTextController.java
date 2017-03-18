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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class Threee_QuestionTextController implements Initializable {
    
    private static int questionNo = 1;
    
    @FXML
    private Button threeWalkAway;
    @FXML
    private Button threeSure;
    @FXML
    private Button threeCallFriend;
    @FXML
    private Button threeEliminate;
    @FXML
    private Button threeAskAudience;
    @FXML
    private RadioButton responseA;
    @FXML
    private ToggleGroup questionResponse;
    @FXML
    private RadioButton responseB;
    @FXML
    private RadioButton responseC;
    @FXML
    private RadioButton responseD;
    @FXML
    private Label threeQuestion;
    @FXML
    private Label threeTime;
    @FXML
    private Label threeQuestionNumber;
    @FXML
    private AnchorPane threeScreen;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void walkAway(ActionEvent event) {
        threeScreen.setOpacity(0.70);
        Alert confirm = new Alert(Alert.AlertType.WARNING);
        confirm.setTitle("Quit");
        confirm.setHeaderText("Remember, quitters never WIN!");
        //show how much you've got so far
        confirm.setContentText("");
        confirm.showAndWait();
        
        Parent landingPage = null;
            threeScreen.setOpacity(1);
            try {
                landingPage = FXMLLoader.load(getClass().getResource("One_LandingPage.fxml"));
                
            } catch (IOException ex) {
                //exception occured
            }
            Scene landing = new Scene(landingPage);
            Stage land = (Stage) ((Node) event.getSource()).getScene().getWindow();
            land.setScene(landing);
            land.setTitle("You Want To Be A Billionaire?!");
            land.show();
            threeScreen.setOpacity(1);
    }

    @FXML
    private void sureAnswer(ActionEvent event) {
        threeQuestionNumber.setText(Integer.toString(questionNo++));
        
    }

    @FXML
    private void phoneFriend(ActionEvent event) {
    }

    @FXML
    private void eliminate(ActionEvent event) {
    }

    @FXML
    private void askAudience(ActionEvent event) {
    }
    
}
