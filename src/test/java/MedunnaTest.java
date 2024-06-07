import org.junit.Assert;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedunnaTest {

        /*
        Given
          User connects to the database
        When
          User sends the query to get room_id for the given room_number from "room" table
        Then
          Verify that the room_id is unique
        And
          User closes the connection
         */
    @Test
    public void medunnaTest() throws SQLException {
        //DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2","select_user","Medunna_pass_@6");
        // User connects to the database
        JDBCUtils.connectToDataBase("medunna.com", "medunna_db_v2", "select_user", "Medunna_pass_@6");
        JDBCUtils.createStatement();

        //User sends the query to get room_id for the given room_number from "room" table
        String query = "SELECT * FROM room WHERE room_number = ?";

        PreparedStatement prs = JDBCUtils.connection.prepareStatement(query);
        prs.setInt(1,  565656);

        ResultSet rs = prs.executeQuery();

        List<Integer> list = new ArrayList<>();
        while (rs.next()){
            list.add(rs.getInt(1));
        }
        System.out.println("room_id = " + list);

        /*
        //User sends the query to get room_id for the given room_number from "room" table
        String query = "SELECT * FROM room WHERE room_number = ?";
        JDBCUtils.executeUpdateWithPreparedSt(query);

        NOTE: it's not practical to use JDBCUtils.executeUpdateWithPreparedSt() here because you need to change
        the whole internal structure of this reusable method to run with this database
         */

        // Verify that the room_id is unique

        int counter = 0;
        for (int w: list){
            if (w > 1){
                counter++;
            }
        }
        //System.out.println(counter);

        // ASSERTION => last step for a tester /QA
        Assert.assertEquals("room_id is not unique!", 1, counter);


        //User closes the connection
        JDBCUtils.closeStatementAndConnection();

    }


}
