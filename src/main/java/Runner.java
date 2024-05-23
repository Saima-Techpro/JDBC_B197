import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws SQLException {
        // Register with Driver class  (OPTIONAL)
        // Create the connection
        JDBCUtils.connectToDataBase("localhost", "jdbc_b197", "batch197_user", "password");

        // Create the statement
        JDBCUtils.createStatement();


        // Execute SQL query
        // We need execute() to perform any DDL queries
        // Task1: Create a table named "employees" with column names of : "employee_id", "employee_name", "salary"
        String query1 = "CREATE TABLE IF NOT EXISTS employees ( employee_id INT, employee_name VARCHAR(50), salary INT )";
        JDBCUtils.execute(query1);

        System.out.println("=============Table created!===================");

        // Task : Insert values into employees table.
        // (employee_id = 101, employee_name= 'John Doe', employee_salary=5000)

        String query2 = "INSERT INTO employees VALUES (? , ? , ? )";  // PARAMETERISED QUERY
        PreparedStatement prs1 = JDBCUtils.connection.prepareStatement(query2);
        // set the values
        prs1.setInt(1, 101);
        prs1.setString(2, "John Doe");
        prs1.setInt(3, 5000);
        prs1.executeUpdate();

        System.out.println("=============Values added using prepared statement==============");


        // Using reusable method
        JDBCUtils.executeUpdateWithPreparedSt(query2);  // It's better to insert the values directly in this class instead of
        // changing values for this method in JDBCUtils class
        System.out.println("=============Values added using Reusable Method============");

        String query3 = "INSERT INTO employees VALUES (103, 'Ali Can', 7000 )";
        int rowsAdded = JDBCUtils.statement.executeUpdate(query3);
        System.out.println("rowsAdded = " + rowsAdded);

        System.out.println("===============Values added using normal query==================");

        // get the column data
        System.out.println(JDBCUtils.getColumnList("employee_name", "employees"));

        // Drop the table
        JDBCUtils.dropTable("employees");

        // Close the connection
        JDBCUtils.closeStatementAndConnection();





    }
}
