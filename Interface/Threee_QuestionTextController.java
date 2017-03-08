/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youwanttobeabillionaire;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class Threee_QuestionTextController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void walkAway(ActionEvent event) {
    }

    @FXML
    private void sureAnswer(ActionEvent event) {
    }
    
}
