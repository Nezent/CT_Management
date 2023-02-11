

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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
    private Text counter = new Text();
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
    ObservableList<marks> list2 = FXCollections.observableArrayList();
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    @FXML
    void Submit(ActionEvent event) throws SQLException {
        String ct = ct_type.getValue();
        Integer roll;
        Integer mark;
        int status = 0;
        if(marks.getText().equals("a") || marks.getText().equals("A")){
            mark = -1;
        }
        else{
            mark = Integer.parseInt(marks.getText());
        }
        if(select_roll.getValue() == "19-22"){
            roll = 1910022;
        }
        else{
            roll = Integer.parseInt(select_roll.getValue());
        }
        // pst = db.conn.prepareStatement("INSERT INTO `result`(`Roll`,`CT_1`,`CT_2`,`CT_3`,`CT_4`)VALUES(?,?,?,?,?)ON DUPLICATE KEY UPDATE `CT_1` = VALUES(?)");
        // pst.setInt(1, roll);
        // pst.setInt(2, ct);
        // pst.setInt(3, mark);
        if(ct == "CT_1"){
            if(mark > 20 || mark < -1){
                status = -2;
            }
            else{
                pst = db.conn.prepareStatement("INSERT INTO `result`(`Roll`,`CT_1`)VALUES(?,?)ON DUPLICATE KEY UPDATE `CT_1` = ?");
                pst.setInt(1, roll);
                pst.setInt(2, mark);
                pst.setInt(3, mark);
                status = pst.executeUpdate();
            }
        }
        else if(ct == "CT_2"){   
            if(mark > 20 || mark < -1){
                status = -2;
            }         
            else{
                pst = db.conn.prepareStatement("INSERT INTO `result`(`Roll`,`CT_2`)VALUES(?,?)ON DUPLICATE KEY UPDATE `CT_2` = ?");
                pst.setInt(1, roll);
                pst.setInt(2, mark);
                pst.setInt(3, mark);   
                status = pst.executeUpdate();   
            }
        }
        else if(ct == "CT_3"){
            if(mark > 20 || mark < -1){
                status = -2;
            }
            else{
                pst = db.conn.prepareStatement("INSERT INTO `result`(`Roll`,`CT_3`)VALUES(?,?)ON DUPLICATE KEY UPDATE `CT_3` = ?");
                pst.setInt(1, roll);
                pst.setInt(2, mark);
                pst.setInt(3, mark); 
                status = pst.executeUpdate(); 
            }
        }
        else{
            if(mark > 20 || mark < -1){
                status = -2;
            }
            else{
                pst = db.conn.prepareStatement("INSERT INTO `result`(`Roll`,`CT_4`)VALUES(?,?)ON DUPLICATE KEY UPDATE `CT_4` = ?");
                pst.setInt(1, roll);
                pst.setInt(2, mark);
                pst.setInt(3, mark);   
                status = pst.executeUpdate();   
            }
        }
        if(status == 1){
            System.out.println("Done");
            marks.setText("");
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("success.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                ScaleTransition st = new ScaleTransition(Duration.millis(100));
                st.setInterpolator(Interpolator.EASE_BOTH);
                stage.initStyle(StageStyle.UNDECORATED);
                st.setFromX(0);
                st.setFromY(0);
                st.setToX(1);
                st.setToY(1);
                stage.setScene(scene);
                stage.show();
                stage.setX(835);
                stage.setY(590);
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(events -> stage.close());
                delay.play();
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
        else if(status == -2){
            marks.setText("");
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("error.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                ScaleTransition st = new ScaleTransition(Duration.millis(100));
                st.setInterpolator(Interpolator.EASE_BOTH);
                stage.initStyle(StageStyle.UNDECORATED);
                st.setFromX(0);
                st.setFromY(0);
                st.setToX(1);
                st.setToY(1);
                stage.setScene(scene);
                stage.show();
                stage.setX(880);
                stage.setY(590);
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(events -> stage.close());
                delay.play();
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
        else{
            System.out.println("Updated Marks");
            marks.setText("");
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("update.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                ScaleTransition st = new ScaleTransition(Duration.millis(100));
                st.setInterpolator(Interpolator.EASE_BOTH);
                stage.initStyle(StageStyle.UNDECORATED);
                st.setFromX(0);
                st.setFromY(0);
                st.setToX(1);
                st.setToY(1);
                stage.setScene(scene);
                stage.show();
                stage.setX(835);
                stage.setY(590);
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(events -> stage.close());
                delay.play();
            }
            catch(IOException e){
                System.out.println(e);
            }
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
    void Export(ActionEvent event) throws IOException, DocumentException, SQLException{
        String path = "C:\\Users\\siraj\\Desktop\\Result.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();
        Paragraph ruet = new Paragraph("Rajshahi University of Engineering & Technology, Rajshahi", FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD));
        Paragraph heading = new Paragraph("Department of ECE,RUET");
        heading.setAlignment(Element.ALIGN_CENTER);
        heading.setSpacingAfter(30f);
        ruet.setAlignment(Element.ALIGN_CENTER);
        ruet.setSpacingAfter(20f);
        document.add(ruet);
        document.add(heading);
        PdfPTable table = new PdfPTable(6);
        table.getDefaultCell().setPadding(4);
        PdfPCell c1 = new PdfPCell(new Phrase("Roll", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        c1.setPadding(4);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("CT_1", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        c1.setPadding(4);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("CT_2", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        c1.setPadding(4);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("CT_3", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        c1.setPadding(4);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("CT_4", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        c1.setPadding(4);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Average", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        c1.setPadding(4);
        table.addCell(c1);
        table.setHeaderRows(1);
        pst = db.conn.prepareStatement("SELECT * FROM `result`");
        ResultSet resultSet = pst.executeQuery();
        while(resultSet.next()){
            list2.add(new marks(resultSet.getInt("Roll"), resultSet.getInt("CT_1"), resultSet.getInt("CT_2"), resultSet.getInt("CT_3"), resultSet.getInt("CT_4")));
        }
        for(int i = 0; i < list2.size(); ++i){
            table.addCell(Integer.toString(list2.get(i).getRoll()));
            if(list2.get(i).getCt1() < 0){
                table.addCell("A");
            }
            else{
                table.addCell(Integer.toString(list2.get(i).getCt1()));
            }
            if(list2.get(i).getCt2() < 0){
                table.addCell("A");
            }
            else{
                table.addCell(Integer.toString(list2.get(i).getCt2()));
            }
            if(list2.get(i).getCt3() < 0){
                table.addCell("A");
            }
            else{
                table.addCell(Integer.toString(list2.get(i).getCt3()));
            }
            if(list2.get(i).getCt4() < 0){
                table.addCell("A");
            }
            else{
                table.addCell(Integer.toString(list2.get(i).getCt4()));
            }
            table.addCell(Integer.toString(list2.get(i).getSum()));
        }
        document.add(table);
        document.close();
        System.out.println("Finished generating the pdf");
        list2.clear();
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
        stage.setTitle("Department of ECE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void switchToAddMark(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("add_mark.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Image icon = new Image("ruet.png");
        stage.getIcons().add(icon);
        stage.setTitle("Department of ECE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void switchToHome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage) myMenuBar.getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Image icon = new Image("ruet.png");
        stage.getIcons().add(icon);
        stage.setTitle("Department of ECE");
        stage.setScene(scene);
        stage.setResizable(false);
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
        stage.setTitle("Department of ECE");
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    public String Count() throws SQLException{
        String count = "";
        pst = db.conn.prepareStatement("SELECT COUNT(`Roll`) AS COUNTER FROM `result`");
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            count = rs.getString("COUNTER");            
        }
        return count;
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String[] cts = {"CT_1","CT_2","CT_3","CT_4"};
        String[] rolls  = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "19-22"};
        ct_type.getItems().addAll(cts);
        select_roll.getItems().addAll(rolls);  
        db.connect();   
        try {
            counter.setText(Count());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
