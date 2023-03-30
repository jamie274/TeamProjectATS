package ManagerClasses;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLReports {

    public SQLReports() {}

    // the text from the text boxes are passed through as a parameter and then used to insert the new data into the database
    public void recordSale(String saleID, String blankID, String customerID,
                                 String advisorID, String paymentDetails, String saleType,
                           String country, String localTax, String otherTax, String status, String exchangeRate,
                           String commissionRate, String localCurr, String date) {
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.execute("SET FOREIGN_KEY_CHECKS=0");
            String insertStm = ("'" + saleID + "'") + ", " + ("'" + blankID + "'") + ", " + ("'" + customerID + "'") + ", "
                + ("NULL") + ", " + ("'" + advisorID + "'") + ", " + ("'" + paymentDetails + "'") + ", " + ("'" + saleType + "'")
                    + ", " + ("'" + country + "'") + ", " + ("NULL")+ ", " + ("NULL") + ", " + ("'" + status + "'")
                    + ", " + ("'" + date + "'") + ", " + ("'" + localTax + "'") + ", " + ("'" + otherTax + "'") + ", "
                    + ("'" + exchangeRate + "'") + ", " + ("'" + commissionRate + "'")
                    + ", " + ("'" + localCurr + "'");
            stm.execute("SET FOREIGN_KEY_CHECKS=1");
            // data to be inserted into the database

            // insert statement is run
            stm.executeUpdate("INSERT INTO Sales VALUES ( " + insertStm + ")");

            // message box, showing that the customer has been successfully added to the database
            JOptionPane.showMessageDialog(null, "Ticket sale has been successfully recorded");
            // close the connection to db
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
