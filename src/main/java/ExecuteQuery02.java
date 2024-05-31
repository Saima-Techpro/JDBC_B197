import java.sql.*;

public class ExecuteQuery02 {
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
        System.out.println("********** Task 1 Using ORDER BY **********");
        //Task-1: Print department name and grade of department which has the second highest pass_grade
        String query1 = "SELECT department, pass_grade FROM departments ORDER BY pass_grade DESC OFFSET 1 LIMIT 1";

        ResultSet rs1 = statement.executeQuery(query1);

        // to see the  data
        while (rs1.next()){
            //System.out.println(rs1.getString("department") + ", " + rs1.getInt("pass_grade"));  // using column name
            System.out.println(rs1.getString(1) + ", " + rs1.getInt(2)); // using column index
        }

        System.out.println("********** Task 1 Using SUBQUERY **********");
        //Task-1: Print department name and grade of department which has the second-highest pass_grade
        String query2 = "SELECT department, pass_grade FROM departments WHERE pass_grade = " +
                "(SELECT MAX(pass_grade) FROM departments WHERE pass_grade <  (SELECT MAX(pass_grade) FROM departments))";

        ResultSet rs2 = statement.executeQuery(query2);

        // to see the  data
        while (rs2.next()){
            //System.out.println(rs1.getString("department") + ", " + rs1.getInt("pass_grade"));  // using column name
            System.out.println(rs2.getString(1) + ", " + rs2.getInt(2)); // using column index
        }

        System.out.println("********** Task 2 **********");
        //Task-2: List department name, campus and highest grades of students from every department

        String query3 = "SELECT department, campus, (SELECT MAX(grade) FROM students s WHERE s.department = d.department) AS max_grade FROM departments d";

        ResultSet rs3 = statement.executeQuery(query3);
        while (rs3.next()){
            //System.out.println(rs3.getString(1) + "-- " + rs3.getString(2) + "-- " + rs3.getInt(3));
            System.out.println(rs3.getString("department") + "-- " + rs3.getString("campus") + "-- " + rs3.getInt("max_grade"));
        }

        // Close the connection
        if(connection != null){
            statement.close();
            connection.close();
            System.out.println("Connection closed");
        }else {
            System.out.println("Connection is not closed");
        }

    }

}
