import java.sql.*;

public class Transaction03_HappyScenario {
    public static void main(String[] args) throws Exception {
        // Create Connection with the DataBase
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_b197", "batch197_user", "password");

        // Create a Statement
        Statement statement = connection.createStatement();
        connection.setAutoCommit(true);

        // Execute SQL query
        // Task 1: Create Table accounts

        String query = "CREATE TABLE IF NOT EXISTS accounts2 (account_num int UNIQUE,name VARCHAR(50), amount real );";

        statement.execute(query);

        String query1 = "INSERT INTO accounts2 VALUES(?,?,?)";

        PreparedStatement prs1 = connection.prepareStatement(query1);
        prs1.setInt(1, 1234);
        prs1.setString(2, "Fatma");
        prs1.setDouble(3, 9500.50);


        prs1.setInt(1, 5678);
        prs1.setString(2, "Omar");
        prs1.setDouble(3, 5500.80);


        // We take the control and stop auto-committing
        connection.setAutoCommit(false);  // we switched off the auto shipping


        //TASK-1. Transfer amount of 1000 from account_nu:1234 to account_nu:5678

        String query2 = "UPDATE accounts SET amount = amount + ? WHERE account_num = ? " ;
        PreparedStatement prs2 = connection.prepareStatement(query2);

        Savepoint savepoint = null;

        try{

            savepoint = connection.setSavepoint(); // returning point if rollBack() works

            // Set the values for Fatma
            prs2.setDouble(1, -1000);
            prs2.setInt(2, 1234);
            prs2.executeUpdate();

//            // Suppose system failed
//            if(true){
//                throw new Exception();
//            }

            // Suppose system didn't fail
            if(false){
                throw new Exception();
            }

            // Set the values for Omar
            prs2.setDouble(1, 1000);
            prs2.setInt(2, 5678);
            prs2.executeUpdate();

            connection.commit();  // we switched on the auto shipping
            System.out.println("Transaction was successful!!");
            connection.close();


        }catch (Exception e){
            connection.rollback();
            System.out.println("Transaction wasn't successful!!");
            connection.close();

        }

        // Close the connection
        statement.close();
        prs1.close();
        prs2.close();
        connection.close();
        System.out.println("Connection closed");


    }
}
