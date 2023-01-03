import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    Connection conn;
    String url = "jdbc:mysql://localhost/ece_result2";
    String user = "root";
    String password = "";
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Success");
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Connection Failed");
        }
    }
}
