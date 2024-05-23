import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Step 1: Register of the Driver
        // Class.forName("org.postgresql.Driver"); // OPTIONAL since Java 7 is released


        // Step 2: Create Connection with the DataBase
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_b197", "batch197_user", "password");

        if(connection != null){
            System.out.println("Connected successfully");
        }else {
            System.out.println("Connection failed");
        }

        // Step 3: Create a Statement
        Statement statement = connection.createStatement();


        // Create countries table on Database using pgAdmin4

        // Step 4: Execute the query
        //TASK-1. GET "country_name" from countries table with ID between 5 and 10

        String query1 = "SELECT country_name FROM countries WHERE id BETWEEN 5 AND 10 ";
        ResultSet rs1 = statement.executeQuery(query1);
 //       System.out.println("rs1 = " + rs1); // This provides us the reference, not the data itself

//        System.out.println(rs1.next());
//        System.out.println(rs1.getString("country_name"));
//        System.out.println(rs1.next());
//        System.out.println(rs1.getString("country_name"));

        // To avoid repetition, we put rs1.next() in a loop

        System.out.println("==========Task 1=============");
        while (rs1.next()){
            // System.out.println(rs1.getString("country_name"));
            System.out.println(rs1.getString(1)); // we can use index of the column as well to reach to that data
        }


        System.out.println("==========Task 2=============");
        //TASK - 2: Get "phone_code" and "country_name" from countries table, whose phone code is greater than 500

        String query2 = "SELECT phone_code , country_name FROM countries WHERE phone_code > 500 ";
        ResultSet rs2 = statement.executeQuery(query2);
        //System.out.println("rs2 = " + rs2);

//        while (rs2.next()){
//            System.out.println(rs2.getInt("phone_code") + "--> " + rs2.getString("country_name"));
//        }

        System.out.println("========== with column index=============");

        // run with column index
        while (rs2.next()){
            System.out.println(rs2.getInt(1) + "--> " + rs2.getString(2));
        }

        System.out.println("==========Task 3=============");

        // Create developers table through pgAdmin
        // Task 3: Get all information about the developers whose salary is lowest
        String query3 = "SELECT * FROM developers WHERE salary =  (SELECT MIN(salary) FROM developers)";
        ResultSet rs3 = statement.executeQuery(query3);

        while (rs3.next()){
            System.out.println(rs3.getInt("id") + " -- " +
                    rs3.getString("name") + "-- " +
                    rs3.getInt("salary") + "-- " +
                    rs3.getString("prog_lang"));
        }


        System.out.println("==========HOMEWORK TASK=============");


        //   HOMEWORK TASK : Display students' name and grade whose grades are higher than passing grade of branches.

        String query4 = "SELECT name, grade FROM students s INNER JOIN departments d ON s.department = d.department  WHERE s.grade > d.pass_grade";

        ResultSet resultSet4 = statement.executeQuery(query4);
        String names ;
        while (resultSet4.next()){
            System.out.println(resultSet4.getString(1) + "--" + resultSet4.getInt(2));

        }


        // Step 5: Close the connection
        if(connection != null){
            statement.close();
            connection.close();
            System.out.println("Connection closed");
        }else {
            System.out.println("Connection is not closed");
        }

        /*
        CREATE TABLE students (
  id int,
  name varchar(50),
  city varchar(50),
  grade int,
  department varchar(20)
);

INSERT INTO students VALUES(100, 'Ahmet Umit', 'İstanbul', 546, 'Comp.Eng.');
INSERT INTO students VALUES(101, 'R.Nuri Tekin', 'Antalya', 486, 'Management');
INSERT INTO students VALUES(102, 'S.Faik Faruk', 'Bursa', 554, 'Comp.Eng.');
INSERT INTO students VALUES(103, 'Yasar Kemal', 'İstanbul', 501, 'Mathematics');
INSERT INTO students VALUES(104, 'Halide E. Adivar', 'İzmir', 473, 'Psychology');
INSERT INTO students VALUES(105, 'Nazan Bekir', 'İzmir', 432, 'Literature');
INSERT INTO students VALUES(106, 'Peyami Safa', 'Antalya', 535, 'Comp.Eng.');
INSERT INTO students VALUES(107, 'Sabahattin Ali', 'İstanbul', 492, 'Mathematics');

CREATE TABLE departments (
  dept_id int,
  department varchar(20),
  pass_grade int,
  campus varchar(20)
);

INSERT INTO departments VALUES(5001, 'Comp.Eng.', 521,'North');
INSERT INTO departments VALUES(5002, 'Mathematics', 455,'South');
INSERT INTO departments VALUES(5003, 'Psychology', 440,'Hisar');
INSERT INTO departments VALUES(5004, 'Management', 465,'Hisar');
INSERT INTO departments VALUES(5005, 'Literature', 420,'North');
         */





    }
}
