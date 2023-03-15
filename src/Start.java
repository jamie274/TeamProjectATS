import LoginPages.*;

import java.sql.SQLException;

public class Start {
    public static void main(String args[]) throws SQLException {
        new WelcomePage().setVisible(true);
        test();
    }

    public static void test () throws SQLException {
        SQLLoginHelper s = new SQLLoginHelper();
    }
}

