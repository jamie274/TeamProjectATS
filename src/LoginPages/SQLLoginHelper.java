package LoginPages;

import java.sql.*;

public class SQLLoginHelper {

    public SQLLoginHelper() throws SQLException {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08", "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM AirViaUser");

            while (result.next()) {
                String st = "";
                for (int i = 1; i <= 6; ++i) {
                    st += result.getString(i) + "   ";
                }
                System.out.println(st);
            }

        }
        catch (SQLException s) {
            s.printStackTrace();
        }
    }

}