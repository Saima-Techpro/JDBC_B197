import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountriesTestWithJUnit {

    // This test case is written in Gherkin Language
        /*
        Given  (pre-condition)
          User connects to the database
        When   (action)
          User sends the query to get the "phone code" from "countries" table
        Then   (verification)
          Verify that the number of "phone code" greater than 300 is 13.
        And    (last step)
          User closes the connection
         */
    @Test
    public void countriesTest() throws SQLException {

        // User connects to the database
        JDBCUtils.connectToDataBase("localhost", "jdbc_b197", "batch197_user", "password");
        JDBCUtils.createStatement();

        // User sends the query to get the "phone code" from "countries" table
        String query = "SELECT phone_code FROM countries";
        ResultSet resultSet = JDBCUtils.statement.executeQuery(query);

        List<Integer> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(resultSet.getInt(1));
        }
        System.out.println(list);

        // Verify that the number of "phone code" greater than 300 is 13.

        int counter = 0;

        for (int w: list){
            if (w > 300){
                counter++;
            }
        }
        System.out.println(counter);

        // ASSERTION => last step for a tester /QA
        // Assert.assertEquals("John", "john");
        Assert.assertEquals(13, counter );

        JDBCUtils.closeStatementAndConnection();



    }
}
