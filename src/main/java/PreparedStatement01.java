import java.sql.*;

public class PreparedStatement01 {
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
        //TASK-1. Update pass_grade to 475 of Mathematics department (use PreparedStatement)

        // Normal query
       // String query1 = "UPDATE departments SET department = 475 WHERE department = 'Mathematics' ";
       // String query1 = "UPDATE departments SET pass_grade = 475 WHERE department ILIKE 'mathematics' ";
       // String query1 = "UPDATE departments SET pass_grade = 475 WHERE department ILIKE 'literature' ";

        // NOTE: To avoid repetition, we create parameterised query

        // parameterised query  => is used in PreparedStatement
        String query1 = "UPDATE departments SET pass_grade = ? WHERE department ILIKE ? ";
        PreparedStatement prs1 = connection.prepareStatement(query1);

        // Set the values
        prs1.setInt (1 , 475);
        prs1.setString(2, "mathematics" );

        // execute the prepared statement
        int rowsUpdated = prs1.executeUpdate();
        System.out.println("rowsUpdated = " + rowsUpdated); // 1

        // To see the data

        String query2 = "SELECT * FROM departments";
        ResultSet rs2 = statement.executeQuery(query2);

        while (rs2.next()){
            System.out.println(rs2.getInt("dept_id") + "-- " +
                    rs2.getString("department") + "-- " +
                    rs2.getInt("pass_grade") + "-- " +
                    rs2.getString("campus"));
        }

        System.out.println("*********Task 2**********");
        // TASK-2. Update pass_grade to 455 of Literature department (use PreparedStatement)
        // We will use the prepared statement so no need to create the query again
        prs1.setInt(1, 455);
        prs1.setString(2, "Literature");

        // execute the prepared statement
        prs1.executeUpdate();

        // To see the data

        String query3 = "SELECT * FROM departments";
        ResultSet rs3 = statement.executeQuery(query3);

        while (rs3.next()){
            System.out.println(rs3.getInt("dept_id") + "-- " +
                    rs3.getString("department") + "-- " +
                    rs3.getInt("pass_grade") + "-- " +
                    rs3.getString("campus"));
        }

        System.out.println("*********Task 3 **********");
        //Task 3 : Using preparedStatement, delete students who are from Mathematics department, from students table.

       // String query4 = "DELETE FROM students WHERE  department ILIKE 'Mathematics' "; // NORMAL QUERY
        String query4 = "DELETE FROM students WHERE  department ILIKE ? "; // PARAMETERISED QUERY

        PreparedStatement prs2 = connection.prepareStatement(query4);
        // Set the values
        prs2.setString(1, "Mathematics");

        // execute the prepared statement

        int rowsDeleted = prs2.executeUpdate();
        System.out.println("rowsDeleted = " + rowsDeleted);


        System.out.println("*********Task 4 **********");

        //task 4: Insert software engineering department using prepared statement into departments table.
        //        // (dept_id = 5006, department = 'Science', pass_grade=475, campus='South')


        String query5 = "INSERT INTO departments VALUES (?, ?, ?, ?)";
        PreparedStatement prs3 = connection.prepareStatement(query5);

        // Set the values
        prs3.setInt(3, 475);
        prs3.setInt(1, 5006);
        prs3.setString(2, "Science");
        prs3.setString(4, "South");

        int rowsInserted = prs3.executeUpdate();
        System.out.println("rowsInserted = " + rowsInserted); // 1



        // Close the connection
        statement.close();
        prs1.close();
        prs2.close();
        prs3.close();
        connection.close();
        System.out.println("Connection closed");


    }
}
