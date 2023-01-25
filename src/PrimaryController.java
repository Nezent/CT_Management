

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrimaryController implements Initializable{
    DB db = new DB();
    @FXML AnchorPane myMenuBar;
    @FXML
    ImageView myImageView;
    Image myImage = new Image(getClass().getResourceAsStream("ruet.png"));
    @FXML
    private Stage stage;
    private Scene scene;
    @FXML
    private ComboBox<String> ct_type = new ComboBox<>();
    @FXML
    private ComboBox<String> select_roll = new ComboBox<>();

    @FXML
    private Button add;
    @FXML
    private Button logout;
    @FXML
    private TextField marks;
    PreparedStatement pst;
    @FXML
    private TableView<marks> table;
    @FXML
    private TableColumn<marks, Integer> average;

    @FXML
    private TableColumn<marks, Integer> ct1;

    @FXML
    private TableColumn<marks, Integer> ct2;

    @FXML
    private TableColumn<marks, Integer> ct3;

    @FXML
    private TableColumn<marks, Integer> ct4;

    @FXML
    private TableColumn<marks, Integer> rollz;
    @FXML
    private Button load;
    @FXML
    ObservableList<marks> list = FXCollections.observableArrayList();
    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    @FXML
    void Submit(ActionEvent event) throws SQLException {
        String ct = ct_type.getValue();
        Integer roll = Integer.parseInt(select_roll.getValue());
        Integer mark = Integer.parseInt(marks.getText());
        // pst = db.conn.prepareStatement("INSERT INTO `result`(`Roll`,`CT_1`,`CT_2`,`CT_3`,`CT_4`)VALUES(?,?,?,?,?)ON DUPLICATE KEY UPDATE `CT_1` = VALUES(?)");
        // pst.setInt(1, roll);
        // pst.setInt(2, ct);
        // pst.setInt(3, mark);
        if(ct == "CT_1"){
            pst = db.conn.prepareStatement("INSERT INTO `result`(`Roll`,`CT_1`)VALUES(?,?)ON DUPLICATE KEY UPDATE `CT_1` = ?");
            pst.setInt(1, roll);
            pst.setInt(2, mark);
            pst.setInt(3, mark);
        }
        else if(ct == "CT_2"){            
            pst = db.conn.prepareStatement("INSERT INTO `result`(`Roll`,`CT_2`)VALUES(?,?)ON DUPLICATE KEY UPDATE `CT_2` = ?");
            pst.setInt(1, roll);
            pst.setInt(2, mark);
            pst.setInt(3, mark);       
        }
        else if(ct == "CT_3"){
            pst = db.conn.prepareStatement("INSERT INTO `result`(`Roll`,`CT_3`)VALUES(?,?)ON DUPLICATE KEY UPDATE `CT_3` = ?");
            pst.setInt(1, roll);
            pst.setInt(2, mark);
            pst.setInt(3, mark);     
        }
        else{
            pst = db.conn.prepareStatement("INSERT INTO `result`(`Roll`,`CT_4`)VALUES(?,?)ON DUPLICATE KEY UPDATE `CT_4` = ?");
            pst.setInt(1, roll);
            pst.setInt(2, mark);
            pst.setInt(3, mark);        
        }
        int status = pst.executeUpdate();
        if(status == 1){
            System.out.println("Done");
            marks.setText("");
        }
        else{
            System.out.println("Updated Marks");
            marks.setText("");
        }
    }
    @FXML
    void LogOut(ActionEvent event) throws IOException{
        switchToLogIn(event);
    }
    @FXML
    void Load(ActionEvent event){
        table.getItems().clear();
        try{
            pst = db.conn.prepareStatement("SELECT * FROM `result`");
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                list.add(new marks(resultSet.getInt("Roll"), resultSet.getInt("CT_1"), resultSet.getInt("CT_2"), resultSet.getInt("CT_3"), resultSet.getInt("CT_4")));
                rollz.setCellValueFactory(new PropertyValueFactory<marks,Integer>("roll"));
                ct1.setCellValueFactory(new PropertyValueFactory<marks,Integer>("ct1"));
                ct2.setCellValueFactory(new PropertyValueFactory<marks,Integer>("ct2"));
                ct3.setCellValueFactory(new PropertyValueFactory<marks,Integer>("ct3"));
                ct4.setCellValueFactory(new PropertyValueFactory<marks,Integer>("ct4"));
                average.setCellValueFactory(new PropertyValueFactory<marks,Integer>("sum"));
                
            }
            table.setItems(list); 
            
            // ct1.setCellValueFactory(new PropertyValueFactory<marks,String>("ct"));
            // average.setCellValueFactory(new PropertyValueFactory<marks,String>("mark"));
        }
        catch(SQLException ex){
            System.out.println("Connection Invalid or Data can't be loaded");
        }
              
        
    }

    @FXML
    void LogIn(ActionEvent event) throws SQLException, IOException {
        String uname = username.getText();
        String pass = password.getText();
        pst = db.conn.prepareStatement("SELECT * FROM `admin` WHERE `username` = ? AND `password` = ?");
        pst.setString(1, uname);
        pst.setString(2, pass);
        ResultSet resultSet = pst.executeQuery();
        if(resultSet.next()){
            switchToHome(event);
        }
        else{
            System.out.println("Login Failed");
            username.setText("");
            password.setText("");
        }
    }
    public void switchToLogIn(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Image icon = new Image("ruet.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToAddMark(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("add_mark.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Image icon = new Image("ruet.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToHome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Image icon = new Image("ruet.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToResult(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("results.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Image icon = new Image("ruet.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String[] cts = {"CT_1","CT_2","CT_3","CT_4"};
        String[] rolls  = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "19-22"};
        ct_type.getItems().addAll(cts);
        select_roll.getItems().addAll(rolls);  
        db.connect();   
    }
}
