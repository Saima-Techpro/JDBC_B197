import java.sql.*;

public class Transaction01 {

//    public Transaction01(){
//        // default constructor
//    }
//
//    public Transaction01(String  query){
//        // parameterised constructor => overwrites the default if we do not ake it visible
//    }
    public static void main(String[] args) throws Exception {
        // Create Connection with the DataBase
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_b197", "batch197_user", "password");

        if(connection != null){
            System.out.println("Connected successfully");
        }else {
            System.out.println("Connection failed");
        }

        // Create a Statement
        Statement statement = connection.createStatement();

        //  Execute SQL query
        System.out.println("********** Task 1 **********");

        //Task 1 : Using preparedStatement, delete students who are from Comp.Eng. department, from students table.

        // String query1 = "DELETE FROM students WHERE  department ILIKE 'Comp.Eng.' "; // NORMAL QUERY
        String query1 = "DELETE FROM students WHERE  department ILIKE ? "; // PARAMETERISED QUERY

        PreparedStatement prs1 =  connection.prepareStatement(query1);

        // Set the values
        prs1.setString(1, "Comp.Eng.");

        // Get the control of transaction
        //connection.setAutoCommit(true);  // by default .. it allows auto commit of transaction
        // To stop automatic committing, we need to EXPLICITLY turn this setAutoCommit() to FALSE
        connection.setAutoCommit(false); // all transactions will be controlled now just like ON/OFF switch

        // let's suppose our transaction doesn't go ahead as a result of some kind of system failure

//        if (true){
//            throw new Exception();
//        }

        if (true){
           connection.rollback();
        }

        // we allow the shipping / committing
        connection.commit(); // committing the transaction

        prs1.executeUpdate();


        // To see the data
        String query2 = "SELECT * FROM students";

        ResultSet rs = statement.executeQuery(query2);

        while (rs.next()){
            System.out.println(rs.getInt("id") + " -- " +
                    rs.getString("name") + " -- " +
                    rs.getString("city") + " -- " +
                    rs.getString("grade") + " -- " +
                    rs.getString("department"));
        }

        // Close the connection
        statement.close();
        connection.close();
        System.out.println("Connection closed");



    }
}
