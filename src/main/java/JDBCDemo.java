import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemo {
    private static final String URL = "jdbc:postgresql://localhost:5432/staff_db";
    private static final String  User = "postgres";
    private static final String Password = "root";
    public static void main(String[] args) {
        Connection conn = null;
        try {
             conn = DriverManager.getConnection(URL,User,Password);
            System.out.println("Connected to database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                System.out.println("Connection closed successfully");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
