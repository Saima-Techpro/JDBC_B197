import java.sql.*;

public class Transaction02 {
    public static void main(String[] args) throws SQLException {
        // Create Connection with the DataBase
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_b197", "batch197_user", "password");

        // Create a Statement
        Statement statement = connection.createStatement();

        // Execute SQL query
        // Task 1: Create Table accounts

        String query = "CREATE TABLE IF NOT EXISTS accounts (account_num int UNIQUE,name VARCHAR(50), amount real );";

        statement.execute(query);

        String query1 = "INSERT INTO accounts VALUES(?,?,?)";

        PreparedStatement prs1 = connection.prepareStatement(query1);
        prs1.setInt(1, 1234);
        prs1.setString(2, "Fred");
        prs1.setDouble(3, 9500.50);


        prs1.setInt(1, 5678);
        prs1.setString(2, "Barnie");
        prs1.setDouble(3, 5500.80);

        // Task 2: Transfer amount of 1000 from account_num:1234 to account_num:5678

        String query2 = "UPDATE accounts SET amount = amount + ? WHERE account_num = ? " ;

        PreparedStatement prs2 = connection.prepareStatement(query2);

        // Set the values for Fred
        prs2.setDouble(1, -1000);
        prs2.setInt(2, 1234);
        prs2.executeUpdate();

        // Suppose system failed
        if(true){
            throw new RuntimeException();
        }

        // Set the values for Barnie
        prs2.setDouble(1, 1000);
        prs2.setInt(2, 5678);
        prs2.executeUpdate();

        // Barnie will not receive money because system failed before her account was updated
        // This is a NEGATIVE SCENARIO.. both customers of this bank will not be happy


        // Close the connection
        statement.close();
        prs1.close();
        prs2.close();
        connection.close();
        System.out.println("Connection closed");


    }
}
