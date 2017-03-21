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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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
    @FXML
    private ImageView questionImage;

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

        questionNo = 1;
    }

    @FXML
    private void sureAnswer(ActionEvent event) {
        try {
            Connection questCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/ywtbab", "root", "root");
            Statement questState = questCon.createStatement();
            ResultSet query = questState.executeQuery("");

        } catch (SQLException e) {
            //database error
        }
        threeQuestionNumber.setText(Integer.toString(questionNo++));

    }

    @FXML
    private void phoneFriend(ActionEvent event) {
        threeCallFriend.setDisable(true);
        String suggestion = pickOne();
        Alert phoneAlert = new Alert(AlertType.INFORMATION);
        phoneAlert.initModality(Modality.NONE);
        phoneAlert.setTitle("Phone A Friend");
        phoneAlert.setHeaderText("Phoning your friend...");
        phoneAlert.setContentText("Your friend thinks the answer is:\n\t\t\t" + suggestion);
        phoneAlert.showAndWait();
    }

    @FXML
    private void eliminate(ActionEvent event) {
        threeCallFriend.setDisable(true);
    }

    @FXML
    private void askAudience(ActionEvent event) {
        threeAskAudience.setDisable(true);
        Random random = new Random();
        int percentage = random.nextInt(101);
        int percentage2 = random.nextInt((100 - percentage) + 1);
        int percentage3 = 100 - percentage2 - percentage; 
        
        Alert phoneAlert = new Alert(AlertType.INFORMATION);
        phoneAlert.initModality(Modality.NONE);
        phoneAlert.setTitle("Ask The Audience");
        phoneAlert.setHeaderText("Asking audience...");
        
        String msg = "Result \n \t A : " + percentage + "% \n";
        String msg1 = "\t B : " + percentage2 + "% \n";
        String msg2 = "\t C : " + percentage3 + "% \n";
        
        phoneAlert.setContentText(msg + msg1 + msg2);
        phoneAlert.showAndWait();
    }

    private String pickOne() {
        ArrayList<String> ans = new ArrayList<>();
        ans.add("A");
        ans.add("B");
        ans.add("C");
        ans.add("D");
        
        Collections.shuffle(ans);
        return ans.get(0);
    }
}
