package LoginPages;

import java.sql.*;

//@Jamie-Lee
public class SQLLoginHelper {

    public SQLLoginHelper() throws SQLException {

    }

    public boolean attemptLogin(String role, int id, String pwd) throws SQLException {

        boolean b = false;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM AirViaUser WHERE Role = " + "'" + role + "'" + "AND ID = "
                    + id + " AND Password = " + "'" + pwd + "'");

            if (result.next()) {
                b = true;
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }

        return b;
    }
}
