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


    public DisplayTablesHelper() {}


    public void DisplayBlankTable(JTable blankTable){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT AdvisorID, ID, BlankType, Status, UsedStatus FROM BlankStock");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the blank table
            DefaultTableModel model = (DefaultTableModel) blankTable.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            while(result.next()){
                //"Advisor ID", "Blank ID", "Blank Type", "Blank Status", "Used Status"
                String btAdvisorID = result.getString("AdvisorID");
                String btBlankID = Integer.toString(result.getInt("ID"));
                String btBlankType = Integer.toString(result.getInt("BlankType"));
                String btBlankStatus = result.getString("Status");
                String btBlankUsedStatus = result.getString("UsedStatus");

                String[] row = {btAdvisorID, btBlankID, btBlankType, btBlankStatus, btBlankUsedStatus};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }

    }

    public void DisplayAdvisorTableManager(JTable staffTable){
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName FROM AirViaUser WHERE Role = 'Travel Advisor'");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) staffTable.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            while(result.next()){

                // The variable UserID, UserFirstName, UserLastName and UserRole are to store the data retrieved from the db.
                String userID = result.getString("ID");
                String userFirstName = result.getString("FirstName");
                String userLastName = result.getString("LastName");


                String[] row = {userID, userFirstName, userLastName};
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }


    //This method takes in JTable to edit the staff table model then add rows of data retrieved from db
    public void DisplayUserTableAdmin(JTable staffTable){
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName, Role FROM AirViaUser");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) staffTable.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            while(result.next()){

                // The variable UserID, UserFirstName, UserLastName and UserRole are to store the data retrieved from the db.
                String userID = result.getString("ID");
                String userFirstName = result.getString("FirstName");
                String userLastName = result.getString("LastName");
                String userRole = result.getString("Role");

                String[] row = {userID, userFirstName, userLastName, userRole};
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void searchStaff(JTable table, String user) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName, Role FROM AirViaUser WHERE ID = " + user);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            if(result.next()){
                // The variable UserID, UserFirstName, UserLastName and UserRole are to store the data retrieved from the db.
                String userID = result.getString("ID");
                String userFirstName = result.getString("FirstName");
                String userLastName = result.getString("LastName");
                String userRole = result.getString("Role");

                String[] row = {userID, userFirstName, userLastName, userRole};
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayUserTableAdmin(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "User does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void searchBlank(JTable table, String blank) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT AdvisorID, ID, BlankType, Status, UsedStatus FROM BlankStock WHERE ID = " + blank);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            if(result.next()){
                //"Advisor ID", "Blank ID", "Blank Type", "Blank Status", "Used Status"
                String btAdvisorID = result.getString("AdvisorID");
                String btBlankID = Integer.toString(result.getInt("ID"));
                String btBlankType = Integer.toString(result.getInt("BlankType"));
                String btBlankStatus = result.getString("Status");
                String btBlankUsedStatus = result.getString("UsedStatus");

                String[] row = {btAdvisorID, btBlankID, btBlankType, btBlankStatus, btBlankUsedStatus};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayBlankTable(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Blank does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void deleteStaff(JTable table, String user) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.executeUpdate("DELETE FROM AirViaUser WHERE ID = " + user);
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName, Role FROM AirViaUser WHERE ID = " + user);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            if(result.next()){
                // The variable UserID, UserFirstName, UserLastName and UserRole are to store the data retrieved from the db.
                String userID = result.getString("ID");
                String userFirstName = result.getString("FirstName");
                String userLastName = result.getString("LastName");
                String userRole = result.getString("Role");

                String[] row = {userID, userFirstName, userLastName, userRole};
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayUserTableAdmin(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "User does not exist, please try again");
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