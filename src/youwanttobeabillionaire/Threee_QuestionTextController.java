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
import java.sql.PreparedStatement;
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

    private static ArrayList<Integer> order = new ArrayList<>();
    private static String correctAns, curUser;
    private static int num = 1;
    private static int generated;
    private static int index = 0;
    private static int databaseSize = 5;    // remember to change size of database
    private static int money = 0;
    private static int ansIndex;

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
    private Label threeQuestionNumber;
    @FXML
    private AnchorPane threeScreen;
    @FXML
    private ImageView questionImage;
    @FXML
    private Label amount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prepare();
        getQuestion();
        curUser = One_LandingPageController.getUser();
    }

    @FXML
    private void walkAway(ActionEvent event) {
        dataWrite();
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
        prepare();
    }

    @FXML
    private void sureAnswer(ActionEvent event) {
        int factor = 5000;
        RadioButton selected = (RadioButton) questionResponse.getSelectedToggle();
        responseA.setDisable(false);
        responseB.setDisable(false);
        responseC.setDisable(false);
        responseD.setDisable(false);
        try {
            if (selected.getText().equals(correctAns)) {
                money += factor;
                amount.setText("$" + Integer.toString(money));
                selected.setSelected(false);
                getQuestion();
            } else {
                walkAway(event);
            }
        } catch (NullPointerException e) {
            walkAway(event);
        }
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
        Random random = new Random();
        int j = random.nextInt(3);
        threeEliminate.setDisable(true);
        ArrayList<RadioButton> tmp = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            tmp.add(null);
        }
        threeEliminate.setDisable(true);
        tmp.set(0, responseA);
        tmp.set(1, responseB);
        tmp.set(2, responseC);
        tmp.set(3, responseD);
        tmp.remove(ansIndex);
        tmp.remove(j);

        tmp.get(0).setDisable(true);
        tmp.get(1).setDisable(true);
    }

    @FXML
    private void askAudience(ActionEvent event) {
        ArrayList<Integer> percentages = new ArrayList<>();
        threeAskAudience.setDisable(true);
        int u = 0;
        Random random = new Random();
        int ran = random.nextInt(200);
        percentages.add(ran);
        int ran1 = random.nextInt(ran + 1);
        percentages.add(ran1);
        int ran2 = random.nextInt(ran1 + 1);
        percentages.add(ran2);
        int rand3 = random.nextInt(ran2 + 1);
        percentages.add(ran2);

        Collections.shuffle(percentages);

        Alert phoneAlert = new Alert(AlertType.INFORMATION);
        phoneAlert.initModality(Modality.NONE);
        phoneAlert.setTitle("Ask The Audience");
        phoneAlert.setHeaderText("Asking audience...");

        String msg = "Result: \n" + "A :\t" + percentages.get(u) + "  people";
        msg += "\n" + "B :\t" + percentages.get(u + 1) + "  people";
        msg += "\n" + "C :\t" + percentages.get(u + 2) + "  people";
        msg += "\n" + "D :\t" + percentages.get(u + 3) + "  people";

        phoneAlert.setContentText(msg);
        phoneAlert.showAndWait();
        threeAskAudience.setDisable(true);
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

    private void getQuestion() {
        try {
            if (index == databaseSize) {
                //this should be a popup saying congrats or something like that
                threeQuestion.setText("QUESTIONS EXHAUSTED!!");    //remember to change to return to homepage not exit
                return;
            }
            Connection dataConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ywtbab", "root", "root");
            Statement query = dataConnection.createStatement();
            ResultSet output = query.executeQuery("select * from game_question where qNo = " + order.get(index));
            index++;
            if (output.next()) {
                threeQuestion.setText(output.getString("question"));
                threeQuestionNumber.setText(Integer.toString(num++));
                correctAns = output.getString("ans");
                String possAns = output.getString("pAns");
                String[] wAns = possAns.split(",");

                ArrayList<String> possibles = new ArrayList<>();
                for (String a : wAns) {
                    possibles.add(a);
                }
                possibles.add(correctAns);

                Collections.shuffle(possibles);
                ansIndex = possibles.indexOf(correctAns);

                responseA.setText(possibles.get(0));
                responseB.setText(possibles.get(1));
                responseC.setText(possibles.get(2));
                responseD.setText(possibles.get(3));
            }
        } catch (SQLException ex) {
            //database error
        }
    }

    private void prepare() {
        for (int i = 0; i < databaseSize; i++) {
            order.add(i + 1);
        }
        Collections.shuffle(order);
        index = 0;
        num = 1;
    }

    private void dataWrite() {
        try {
            Connection dataConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ywtbab", "root", "root");
            Statement query = dataConnection.createStatement();
            int t = 0;
    
            ResultSet output = query.executeQuery("select * from user_info" + " where user = '"+curUser+"'");
            while (output.next() ) {
                t = output.getInt("money");
                System.out.println(t < money);
            }
            
            if (t < money) {
                System.out.println(money);
                PreparedStatement p =dataConnection.prepareStatement("update user_info set money = " + money + " where user = ?");
                p.setString(1, curUser);
                p.executeUpdate();
            }
                     
        } catch(SQLException e) {
            //database error
        }
    }
}
