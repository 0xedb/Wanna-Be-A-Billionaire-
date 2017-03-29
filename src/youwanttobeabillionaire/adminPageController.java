package youwanttobeabillionaire;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class adminPageController implements Initializable {

    @FXML
    private ToggleGroup questionLevel;

    @FXML
    private TextField fourQuestion;

    @FXML
    private TextField fourAns;

    @FXML
    private TextField fourPossibleQuestions;

    @FXML
    private Button questionAdd;
    @FXML
    private Label warn;
    @FXML
    private Label levelWarn;
    @FXML
    private Label questionWarn;
    @FXML
    private Label answerWarn;
    @FXML
    private Label pAnswersWarn;
    @FXML
    private TableView<UserDetails> userTable;
    @FXML
    private TableColumn<UserDetails, String> userName;
    @FXML
    private TableColumn<UserDetails, String> userEmail;
    @FXML
    private TableColumn<UserDetails, Integer> userMoney;
    @FXML
    private Button search;
    @FXML
    private TextField searched;
    @FXML
    private TableColumn<UserDetails, String> userBoard;
    @FXML
    private TableColumn<UserDetails, Integer> moneyBoard;
    @FXML
    private TableView<UserDetails> leaderboard;
    @FXML
    private TableColumn<?, ?> emailBoard;
    @FXML
    private Button editRecord;
    @FXML
    private Button deleteRecord;
    @FXML
    private TextField mail;
    @FXML
    private TextField cash;
    @FXML
    private Button update;

    private static int index;
    @FXML
    private MenuItem aboutUs;
    @FXML
    private MenuItem close;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            dc = new DbConnection();
            Connection conn = dc.con();
            data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("select user, email, money from user_info");
            while (rs.next()) {
                data.add(new UserDetails(rs.getString("user"), rs.getString("email"), rs.getInt("money")));
            }

        } catch (SQLException ex) {
            System.out.println("Error \n" + ex);
        }

        userName.setCellValueFactory(new PropertyValueFactory<>("user"));
        userEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        userMoney.setCellValueFactory(new PropertyValueFactory<>("money"));

        userTable.setItems(null);
        userTable.setItems(data);

    }

    @FXML
    void add(ActionEvent event) {
        try {

            warn.setText("");
            dataConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ywtbab", "root", "root");
            query = dataConnection.prepareStatement("insert into game_question set level=?, question=?, ans=?, pAns=?");
            checkInput();
        } catch (SQLException e) {
            warn.setText("Database may not exist");
        }

    }

    private static Connection dataConnection = null;
    private static PreparedStatement query = null;
    private static String question = null;
    private static int level;
    private static String answer = null;
    private static String possibleQuestions = null;
    private ObservableList<UserDetails> data;
    private DbConnection dc;

    private String[] checkInput() {
        int c = 0;
        try {
            RadioButton selected = (RadioButton) questionLevel.getSelectedToggle();
            level = Integer.parseInt(selected.getText());
            levelWarn.setStyle("-fx-text-fill: lime;");
            c++;

        } catch (Exception e) {
            levelWarn.setStyle("-fx-text-fill: red;");
        }
        question = fourQuestion.getText();

        try {
            Connection dc = DriverManager.getConnection("jdbc:mysql://localhost:3306/ywtbab", "root", "root");
            Statement qr = dc.createStatement();
            ResultSet rSet = qr.executeQuery("select question from game_question where question = " + question);
            if (rSet.next()) {
                System.out.println("matched");
            }
        } catch (SQLException e) {
            System.out.println("BAD");
            e.printStackTrace();
        }
        return null;

    }

    @FXML
    private void landingPage(ActionEvent event) throws IOException {
        Parent landingPage = FXMLLoader.load(getClass().getResource("One_LandingPage.fxml"));
        Scene landing = new Scene(landingPage);
        Stage land = (Stage) ((Node) event.getSource()).getScene().getWindow();
        land.setScene(landing);
        land.setTitle("You Want To Be A Billionaire?!");
        land.show();
    }

    @FXML
    private void userSearch(ActionEvent event) {
        try {
            data = FXCollections.observableArrayList();
            dc = new DbConnection();
            Connection conn = dc.con();

            ResultSet rs = conn.createStatement().executeQuery("select user, email, money from user_info where user = '" + searched.getText() + "'");
            if (rs.next()) {
                warn.setText("");
                data.add(new UserDetails(rs.getString("user"), rs.getString("email"), rs.getInt("money")));
                userBoard.setCellValueFactory(new PropertyValueFactory<>("user"));
                emailBoard.setCellValueFactory(new PropertyValueFactory<>("email"));
                moneyBoard.setCellValueFactory(new PropertyValueFactory<>("money"));

                leaderboard.setItems(null);
                leaderboard.setItems(data);
            } else {
                warn.setText("User not found");
            }

        } catch (SQLException ex) {
            warn.setText("User not found");
        }

    }

    @FXML
    private void edit(ActionEvent event) {

        update.setVisible(true);
        mail.setText("");
        cash.setText("");
        String name = userTable.getSelectionModel().getSelectedItem().getUser();
        String email = userTable.getSelectionModel().getSelectedItem().getEmail();
        mail.setText(email);
        int money = userTable.getSelectionModel().getSelectedItem().getMoney();
        cash.setText(Integer.toString(money));
        index = userTable.getSelectionModel().getSelectedIndex();

    }

    @FXML
    private void delete(ActionEvent event) {
        String name = userTable.getSelectionModel().getSelectedItem().getUser();
        int index = userTable.getSelectionModel().getSelectedIndex();

        try {
            userTable.getItems().remove(index);
            dc = new DbConnection();
            Connection conn = dc.con();
            Statement sm = conn.createStatement();
            sm.execute("delete from user_info where user = '" + name + "'");

        } catch (SQLException e) {
            //database error
            e.printStackTrace();
        }

    }

    @FXML
    private void up(ActionEvent event) {
        String name = userTable.getSelectionModel().getSelectedItem().getUser();
        String email = userTable.getSelectionModel().getSelectedItem().getEmail();
        int money = userTable.getSelectionModel().getSelectedItem().getMoney();

        try {
            Connection conn = dc.con();
            Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("select * from user_info where user = '"+name+"'");
            if(rs.next()) {
                String pass = rs.getString("password");
                String dob = rs.getString("dob");
                String sex = rs.getString("sex");
                String sec = rs.getString("sec_question");
                String secres = rs.getString("sec_question_response");
                
                PreparedStatement ps1 = conn.prepareStatement("delete from user_info where user=?");
                ps1.setString(1, name);
                ps1.executeUpdate();
                
                String a = mail.getText();
                int m = Integer.parseInt(cash.getText());
                
                PreparedStatement ps = conn.prepareStatement("insert into user_info set user=?, password=?, dob=?, sex=?, email=?, sec_question=?, sec_question_response=?, money=?");
                ps.setString(1, name);
                ps.setString(2, pass);
                ps.setString(3, dob);
                ps.setString(4, sex);
                ps.setString(5, a);
                ps.setString(6, sec);
                ps.setString(7, secres);
                ps.setInt(8, m);
                ps.execute();
                System.out.println("got here<<<<<<<<<<");
            }           
            
            
            mail.clear();
            cash.clear();
            update.setVisible(false);
            refresh();
        } catch (SQLException e) {
            //database error
            e.printStackTrace();
        }

    }
    
    private void refresh() {
        try {
            dc = new DbConnection();
            Connection conn = dc.con();
            data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("select user, email, money from user_info");
            while (rs.next()) {
                data.add(new UserDetails(rs.getString("user"), rs.getString("email"), rs.getInt("money")));
            }

        } catch (SQLException ex) {
            System.out.println("Error \n" + ex);
        }

        userName.setCellValueFactory(new PropertyValueFactory<>("user"));
        userEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        userMoney.setCellValueFactory(new PropertyValueFactory<>("money"));

        userTable.setItems(null);
        userTable.setItems(data);
    }

    @FXML
    private void sayAbout(ActionEvent event) {
        Alert phoneAlert = new Alert(Alert.AlertType.INFORMATION);
        phoneAlert.initModality(Modality.NONE);
        phoneAlert.setTitle("About Us");
        phoneAlert.setHeaderText("Created by: ");

        phoneAlert.setContentText("Bruno Edoh \nDavid Sasu \nKelvin Degbotse \nLesapiti Loltolo");
        phoneAlert.showAndWait();
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Platform.exit();
    }

}
