package ManagerClasses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

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

    public void searchAdvisorInterline(String user, JFrame frame) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName FROM AirViaUser WHERE Role = 'Travel Advisor' AND ID = " + user);

            //inserts all the data in the respective columns
            if(result.next()){
                new IndividualInterline(user).setVisible(true);
                frame.dispose();
            } else { // user has not been found
                JOptionPane.showMessageDialog(null, "User does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void searchAdvisorDomestic(String user, JFrame frame) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName FROM AirViaUser WHERE Role = 'Travel Advisor' AND ID = " + user);

            //inserts all the data in the respective columns
            if(result.next()){
                new IndividualDomestic(user).setVisible(true);
                frame.dispose();
            } else { // user has not been found
                JOptionPane.showMessageDialog(null, "User does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void DisplayIndividualInterline(JTable table, String staffID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Interline'");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the table
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
            while(result.next()){
                //ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, DiscountPlanType, PaymentType, SaleType,
                // ExchangeRateCurrency, TotalAmount, AmountPaid, Status, Date
                String ID = Integer.toString(result.getInt("ID"));
                String BlankID = Integer.toString(result.getInt("BlankStockID"));
                String CustomerID = Integer.toString(result.getInt("CustomerID"));
                String Commission = Float.toString(result.getFloat("CommisionRate")) + "%";
                String Payment = result.getString("PaymentType");
                String SaleType = result.getString("SaleType");
                String exchangeRate =  Float.toString(result.getFloat("ExchangeRate"));
                String localCurrency = Float.toString(result.getFloat("LocalCurrency"));
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void DisplayIndividualDomestic(JTable table, String staffID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Domestic'");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the table
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
            while(result.next()){
                //ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, DiscountPlanType, PaymentType, SaleType,
                // ExchangeRateCurrency, TotalAmount, AmountPaid, Status, Date
                String ID = Integer.toString(result.getInt("ID"));
                String BlankID = Integer.toString(result.getInt("BlankStockID"));
                String CustomerID = Integer.toString(result.getInt("CustomerID"));
                String Commission = Float.toString(result.getFloat("CommisionRate")) + "%";
                String Payment = result.getString("PaymentType");
                String SaleType = result.getString("SaleType");
                String exchangeRate =  Float.toString(result.getFloat("ExchangeRate"));
                String localCurrency = Float.toString(result.getFloat("LocalCurrency"));
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void DisplayGlobalDomestic(JTable table){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales WHERE SaleType = 'Domestic'");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the table
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
            while(result.next()){
                //ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, DiscountPlanType, PaymentType, SaleType,
                // ExchangeRateCurrency, TotalAmount, AmountPaid, Status, Date
                String ID = Integer.toString(result.getInt("ID"));
                String BlankID = Integer.toString(result.getInt("BlankStockID"));
                String CustomerID = Integer.toString(result.getInt("CustomerID"));
                String Commission = Float.toString(result.getFloat("CommisionRate")) + "%";
                String Payment = result.getString("PaymentType");
                String SaleType = result.getString("SaleType");
                String exchangeRate =  Float.toString(result.getFloat("ExchangeRate"));
                String localCurrency = Float.toString(result.getFloat("LocalCurrency"));
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void DisplayGlobalInterline(JTable table){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales WHERE SaleType = 'Interline'");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the table
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
            while(result.next()){
                //ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, DiscountPlanType, PaymentType, SaleType,
                // ExchangeRateCurrency, TotalAmount, AmountPaid, Status, Date
                String ID = Integer.toString(result.getInt("ID"));
                String BlankID = Integer.toString(result.getInt("BlankStockID"));
                String CustomerID = Integer.toString(result.getInt("CustomerID"));
                String Commission = Float.toString(result.getFloat("CommisionRate")) + "%";
                String Payment = result.getString("PaymentType");
                String SaleType = result.getString("SaleType");
                String exchangeRate =  Float.toString(result.getFloat("ExchangeRate"));
                String localCurrency = Float.toString(result.getFloat("LocalCurrency"));
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void searchIndividualInterline(JTable table, String staffID, String saleID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Interline' AND ID = " + saleID);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the table
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
                //ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, DiscountPlanType, PaymentType, SaleType,
                // ExchangeRateCurrency, TotalAmount, AmountPaid, Status, Date
                String ID = Integer.toString(result.getInt("ID"));
                String BlankID = Integer.toString(result.getInt("BlankStockID"));
                String CustomerID = Integer.toString(result.getInt("CustomerID"));
                String Commission = Float.toString(result.getFloat("CommisionRate")) + "%";
                String Payment = result.getString("PaymentType");
                String SaleType = result.getString("SaleType");
                String exchangeRate =  Float.toString(result.getFloat("ExchangeRate"));
                String localCurrency = Float.toString(result.getFloat("LocalCurrency"));
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayIndividualInterline(table, staffID); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Ticket Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void searchIndividualDomestic(JTable table, String staffID, String saleID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Domestic' AND ID = " + saleID);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the table
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
                //ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, DiscountPlanType, PaymentType, SaleType,
                // ExchangeRateCurrency, TotalAmount, AmountPaid, Status, Date
                String ID = Integer.toString(result.getInt("ID"));
                String BlankID = Integer.toString(result.getInt("BlankStockID"));
                String CustomerID = Integer.toString(result.getInt("CustomerID"));
                String Commission = Float.toString(result.getFloat("CommisionRate")) + "%";
                String Payment = result.getString("PaymentType");
                String SaleType = result.getString("SaleType");
                String exchangeRate =  Float.toString(result.getFloat("ExchangeRate"));
                String localCurrency = Float.toString(result.getFloat("LocalCurrency"));
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayIndividualDomestic(table, staffID); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Ticket Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void searchGlobalDomestic(JTable table, String saleID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where SaleType = 'Domestic' AND ID = " + saleID);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the table
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
                //ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, DiscountPlanType, PaymentType, SaleType,
                // ExchangeRateCurrency, TotalAmount, AmountPaid, Status, Date
                String ID = Integer.toString(result.getInt("ID"));
                String BlankID = Integer.toString(result.getInt("BlankStockID"));
                String CustomerID = Integer.toString(result.getInt("CustomerID"));
                String Commission = Float.toString(result.getFloat("CommisionRate")) + "%";
                String Payment = result.getString("PaymentType");
                String SaleType = result.getString("SaleType");
                String exchangeRate =  Float.toString(result.getFloat("ExchangeRate"));
                String localCurrency = Float.toString(result.getFloat("LocalCurrency"));
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayGlobalDomestic(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Ticket Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void searchGlobalInterline(JTable table, String saleID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where SaleType = 'Interline' AND ID = " + saleID);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the table
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
                //ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, DiscountPlanType, PaymentType, SaleType,
                // ExchangeRateCurrency, TotalAmount, AmountPaid, Status, Date
                String ID = Integer.toString(result.getInt("ID"));
                String BlankID = Integer.toString(result.getInt("BlankStockID"));
                String CustomerID = Integer.toString(result.getInt("CustomerID"));
                String Commission = Float.toString(result.getFloat("CommisionRate")) + "%";
                String Payment = result.getString("PaymentType");
                String SaleType = result.getString("SaleType");
                String exchangeRate =  Float.toString(result.getFloat("ExchangeRate"));
                String localCurrency = Float.toString(result.getFloat("LocalCurrency"));
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayGlobalInterline(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Ticket Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    // Clears the table of data
    public void ClearTable(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

}
