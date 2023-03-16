package AdminClasses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Arrays;

/**
 *
 * @author Abdul Rehman
 */
// Class used to read data from the database and display
// it in tables on the User Dashboards
public class DisplayTablesHelper {

    // The variable UserID, UserFirstName, UserLastName and UserRole are to store the data retrieved from the db.
    private String UserID;
    private String UserFirstName;
    private String UserLastName;
    private String UserRole;

    public DisplayTablesHelper(){}

    //This meathod takes in jtable to edit the stafftable model then add rows of data retrived from db
    public void DisplayUserTable(JTable staffTable){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName, Role FROM AirViaUser");

            //this stores all the meta data recived from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) staffTable.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts coloum names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective coloumns
            while(result.next()){

                UserID = result.getString("ID");
                UserFirstName = result.getString("FirstName");
                UserLastName = result.getString("LastName");
                UserRole = result.getString("Role");

                String[] row = {UserID, UserFirstName, UserLastName, UserRole};
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }

    }

    public void ClearTable(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }
}
