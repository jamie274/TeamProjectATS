import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class SQLRegisterHelper {
 public void registerStaff(String roleLabel, String idLabel, String firstNameLabel, String lastNameLabel, String emailLabel, String passwordLabel, AbstractButton table){
     try{
         // connecting to the database server
         Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                 "in2018g08_a", "R8pV1HmN");

         Statement stm = con.createStatement();
         ResultSet result = stm.executeQuery("INSERT INTO AirViaUser(id, role, firstname, lastname, email, password) VALUES () ");


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

         //insert all the data in the respective columns

         if(result.next()){
             roleLabel = result.getString("Role");
             idLabel = result.getString("ID");
             firstNameLabel = result.getString("FirstName");
             lastNameLabel = result.getString("LastName");
             emailLabel = result.getString("Email");
             passwordLabel = result.getString("Password");

             String[] row = {idLabel, roleLabel, firstNameLabel, lastNameLabel, emailLabel,passwordLabel};
             model.addRow(row);
         }
         // close the connection to db
         con.close();
     } catch (Exception e) {
         e.printStackTrace();
     }
 }

    }

