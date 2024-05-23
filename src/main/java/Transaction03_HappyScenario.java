import java.sql.*;

public class Transaction03_HappyScenario {
    public static void main(String[] args) throws Exception {
        // Create Connection with the DataBase
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_b197", "batch197_user", "password");

        // Create a Statement
        Statement statement = connection.createStatement();

        // We take the control and stop auto-committing
        connection.setAutoCommit(false);  // we switched off the auto shipping

        // Execute SQL query

        //TASK-1. Transfer amount of 1000 from account_nu:1234 to account_nu:5678

        String query = "UPDATE accounts SET amount = amount + ? WHERE account_nu = ? " ;

        PreparedStatement prs1 = connection.prepareStatement(query);
        Savepoint savepoint = null;

        try{

            savepoint = connection.setSavepoint(); // returning point if rollBack() works

            // Set the values for Fred
            prs1.setDouble(1, -1000);
            prs1.setInt(2, 1234);
            prs1.executeUpdate();

//            // Suppose system failed
//            if(true){
//                throw new Exception();
//            }

            // Suppose system didn't fail
            if(false){
                throw new Exception();
            }

            // Set the values for Barnie
            prs1.setDouble(1, 1000);
            prs1.setInt(2, 5678);
            prs1.executeUpdate();

            connection.commit();
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
        connection.close();
        System.out.println("Connection closed");


    }
}
