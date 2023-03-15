import LoginPages.*;

import java.sql.SQLException;

public class Start {
    public static void main(String args[]) throws SQLException {
        new WelcomePage().setVisible(true);
        //test();
    }

    public static void test () throws SQLException {
        int id = 200081454;
        String pwd = "SivaT##_01";
        SQLLoginHelper s = new SQLLoginHelper();

        if (s.attemptLogin("Office Manager", id, "SivaT##_01")) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}

