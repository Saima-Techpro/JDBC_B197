
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtils {

    public static Connection connection;  // Class level variable (larger scope)
    public static Statement statement;
    public static ResultSet resultSet;

    public static PreparedStatement prs;

    // Step 1: Create connection
   //  Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_b197", "batch197_user", "password");

    public static Connection connectToDataBase(String hostName, String dataBaseName, String userName, String password) {
        // Connection connection = null;  // Local variable with a limited scope
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dataBaseName , userName, password);
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    // Step 2: Create Statement
    public static Statement createStatement(){

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }

    // Reusable method to execute query
    public static boolean execute(String query){
        boolean result;     // local variable
        try {
            result = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    // Method to get the data from the column into a list

    public static List<Object> getColumnList(String columnName, String tableName){
        List<Object> list = new ArrayList<>();
        String query = "SELECT "+columnName+" FROM "+ tableName;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                list.add(resultSet.getObject(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return list;
    }

    // Insert values into a column of any table
    public static int executeUpdateWithPreparedSt ( String query) {

        int numOfRowsUpdated = 0;
        try {
            prs = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prs.setInt(1, 102);
            prs.setString(2, "Tom Hanks");
            prs.setInt(3, 6000);
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return numOfRowsUpdated;
    }



    // Drop the table
    public static void dropTable(String tableName){
        try {
            statement.execute("DROP TABLE "+tableName);
            System.out.println("Table "+tableName+" dropped successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Close the statement and connection
    public static void closeStatementAndConnection(){

        try {
            statement.close();
            connection.close();
            System.out.println("Statement and Connection closed successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }























}
