import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {
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

        //TASK-1. Update salaries of developers whose salaries are less than average salary with average salary
//        String query1 = "UPDATE developers SET salary = (SELECT AVG(salary) FROM developers) WHERE salary < (SELECT AVG(salary) FROM developers)";
//
//        int numOfRowsUpdated =statement.executeUpdate(query1);
//        System.out.println("numOfRowsUpdated = " + numOfRowsUpdated);

        // To see the data
        String query2 = "SELECT id, name, salary, prog_lang FROM developers";
        ResultSet rs1 =  statement.executeQuery(query2);

        while (rs1.next()){
            System.out.println(rs1.getInt("id") + "-- " +
                    rs1.getString("name") + "-- " +
                    rs1.getInt("salary") + "-- " +
                    rs1.getString("prog_lang"));
        }

        System.out.println("********** Task 2 **********");
        // Task 2: Add a developer in the developers table (id, name, salary, prog_lang)
//        String query3 = "INSERT INTO developers (id, name, salary, prog_lang) VALUES (201, 'John Doe', 60000, 'Java')";
//        statement.executeUpdate(query3);

//        OR
//        int numOfRowsUpdated1 =statement.executeUpdate(query3);
//        System.out.println("numOfRowsUpdated1 = " + numOfRowsUpdated1);

        // To see the data
        String query4 = "SELECT id, name, salary, prog_lang FROM developers";
        ResultSet rs2 =  statement.executeQuery(query4);

        while (rs2.next()){
            System.out.println(rs2.getInt("id") + "-- " +
                    rs2.getString("name") + "-- " +
                    rs2.getInt("salary") + "-- " +
                    rs2.getString("prog_lang"));
        }

        System.out.println("********** Task 3 **********");
        // Task 3:  DELETE rows from developers table if  prog_lang is “Ruby”
//        String query5 = "DELETE FROM developers WHERE prog_lang = 'Ruby' ";
//        String query5 = "DELETE FROM developers WHERE prog_lang LIKE 'Ruby' ";
        String query5 = "DELETE FROM developers WHERE prog_lang ILIKE 'ruby' ";

        int rowsDeleted = statement.executeUpdate(query5);
        System.out.println("rowsDeleted = " + rowsDeleted); // 4


        // Close the connection
        statement.close();
        connection.close();
        System.out.println("Connection closed");


    }
}
