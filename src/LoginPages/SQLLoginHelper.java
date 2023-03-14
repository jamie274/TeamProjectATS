package LoginPages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLLoginHelper {
//@Jamie-Lee
    public SQLLoginHelper() {
        this.getConnection();
    }

    public Connection getConnection() {
        Connection con;
        try {
            /*Class.forName("com.mysql.jdbc.Driver");*/
            Properties connectionProps = new Properties();
            connectionProps.put("user", userName);
            connectionProps.put("password", pwd);
            con = DriverManager.getConnection("jdbc:mysql://"
                    + "server smcse-stuproj00.city.ac.uk" + ":" + 3306
                    + "/" + "in2018g08", connectionProps);
        }
        catch(SQLException sqle) {
        }
        finally{
            return con;
        }
    }

}
