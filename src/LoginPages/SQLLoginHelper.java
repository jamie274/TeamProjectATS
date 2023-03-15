package LoginPages;

import java.sql.*;

// @Jamie-Lee
// Class used to read data from the database to confirm a login
public class SQLLoginHelper {

    public SQLLoginHelper() {}

    // attemptLogin method will pass through the entered username and password of the user and compare with data in the database
    public boolean attemptLogin(String role, int id, String pwd) {

        boolean b = false; // this will change to true if the login is successful

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            // executing the sql query to find the user in the database with the user-entered id and password
            ResultSet result = stm.executeQuery("SELECT * FROM AirViaUser WHERE Role = " + "'" + role + "'" + "AND ID = "
                    + id + " AND Password = " + "'" + pwd + "'");

            if (result.next()) {
                b = true; // if the id AND password match, then the login is successful
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }

        return b; // the boolean is returned to indicate a successful or failed login
    }
}
