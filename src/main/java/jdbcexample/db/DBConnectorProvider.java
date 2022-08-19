package jdbcexample.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectorProvider {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static DBConnectorProvider provider = new DBConnectorProvider();

    private DBConnectorProvider() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static DBConnectorProvider getProvider() {
        return provider;

    }

    public Connection getConnection() {
        try {
           return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
