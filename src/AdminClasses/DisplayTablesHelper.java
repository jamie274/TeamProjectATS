package AdminClasses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author Abdul Rehman
 */
// Class used to read data from the database and display
// it in tables on the User Dashboards
public class DisplayTablesHelper {

    // The variable UserID, UserFirstName, UserLastName and UserRole are to store the data retrieved from the db.
    private String userID;
    private String userFirstName;
    private String userLastName;
    private String userRole;
//"Advisor ID", "Blank ID", "Blank Type", "Blank Status", "Used Status"

    private String btAdvisorID, btBlankID, btBlankType, btBlankStatus, btBlankUsedSatatus;
    public DisplayTablesHelper(){}


    public void DisplayBlankTable(JTable blankTable){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT AdvisorID, ID, BlankType, Status, UsedStatus FROM BlankStock");

            //this stores all the meta data recived from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) blankTable.getModel();

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
                btAdvisorID = result.getString("AdvisorID");
                btBlankID = Integer.toString(result.getInt("ID"));
                btBlankType = Integer.toString(result.getInt("BlankType"));
                btBlankStatus = result.getString("Status");
                btBlankUsedSatatus = result.getString("UsedStatus");

                String[] row = {btAdvisorID, btBlankID, btBlankType, btBlankStatus, btBlankUsedSatatus};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }

    }
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

                userID = result.getString("ID");
                userFirstName = result.getString("FirstName");
                userLastName = result.getString("LastName");
                userRole = result.getString("Role");

                String[] row = {userID, userFirstName, userLastName, userRole};
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
