import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Step 1: Register of the Driver
        // Step 2: Create Connection with the DataBase
        // Step 3: Create a Statement
        // Step 4: Execute the query
        // Step 5: Close the connection


        // Step 1: Register of the Driver

        Class.forName("org.postgresql.Driver"); // OPTIONAL since Java 7 is released


        // Step 2: Create Connection with the DataBase
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_b197", "batch197_user", "password");

        if(connection != null){
            System.out.println("Connected successfully");
        }else {
            System.out.println("Connection failed");
        }

        // Step 3: Create a Statement

        Statement statement = connection.createStatement();

        // Step 4: Execute the query

        // Task1: create a table named "employees" with column names of : "employee_id", "employee_name", "salary"
        boolean sql1 = statement.execute("CREATE TABLE IF NOT EXISTS employees ( employee_id INT, employee_name VARCHAR(50), salary INT )");
        System.out.println("sql1 = " + sql1); // false

        /*

        execute() method returns boolean value and can be used with DDL and DQL
        1. execute() method with DDL => it always returns FALSE
        2. execute() method with DQL => it can return TRUE or FALSE
             if you get a resultSet after running this execute() method, it will return TRUE

         */

        //TASK 2: add Varchar (20) column name "city" to employee table
        String query1 = "ALTER TABLE employees ADD COLUMN IF NOT EXISTS city VARCHAR (20)";

        boolean sql2 = statement.execute(query1);
        System.out.println("sql2 = " + sql2); // false

        // Task 3: Drop the table

        String query2 = "DROP TABLE employees";
        statement.execute(query2);


        // Step 5: Close the connection
        if(connection != null){
            statement.close();
            connection.close();
            System.out.println("Connection closed");
        }else {
            System.out.println("Connection is not closed");
        }




    }
}
