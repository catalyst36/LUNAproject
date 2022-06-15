package luna;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Connect {
   
   public static Connection getConnection() {
      
      String driver = "oracle.jdbc.driver.OracleDriver";
      String url = "jdbc:oracle:thin:@192.168.143.28:1521:xe";
      String user = "luna";
      String pw = "java";
      
      Connection con = null;
      
      try {
         Class.forName(driver);
         con = DriverManager.getConnection(url, user, pw);
      }catch(ClassNotFoundException e) {
         e.printStackTrace();
         System.out.println("JDBC DRIVER LOADING FAILURE");
      }catch(SQLException e) {
         e.printStackTrace();
         System.out.println("CONNECTION FAILURE");
      }
      
      return con;
      
   }
   
}