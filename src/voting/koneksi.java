package voting;

import com.mysql.jdbc.Connection;
import java.lang.System.Logger;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class koneksi {
    private final String url = "jdbc:mysql://localhost:3306/candidate";
    private final String username = "root";
    private final String password = "";
    private Connection koneksi = null;
 
    public Connection getConnection() {
        try {
            koneksi = (Connection) DriverManager.getConnection(url,username,password);
            System.out.println("Koneksi Berhasil");
                    
        } catch (SQLException e) {
            System.err.println("Koneksi Berhasil");
        }
        return koneksi;
    }
    
//    public static ResultSet executeQuery(String SQL) {
//        ResultSet rs = null;
//        Connection koneksi = getConnection();
//        try {
//            Statement st = koneksi.createStatement();
//            rs = st.executeQuery(SQL);
//        } catch (Exception e) {
////            Logger.getLogger
//        }
//        
//    }
}
