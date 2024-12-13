import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rakesh","samplepass");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //close connection
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ensure 'products' table exists
    public static void ensureProductTableExists() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String checkTableQuery = "SELECT table_name FROM user_tables WHERE table_name = 'PRODUCTS'";
            ResultSet resultSet = statement.executeQuery(checkTableQuery);

            if (!resultSet.next()) {
                String createTableQuery = "CREATE TABLE products (" +
                        "prod_id VARCHAR2(50), " +
                        "prod_name VARCHAR2(100), " +
                        "prod_price NUMBER)";
                statement.executeUpdate(createTableQuery);
                System.out.println("Table 'products' created successfully.");
            } else {
                System.out.println("Table 'products' already exists.");
            }

            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                closeConnection(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
