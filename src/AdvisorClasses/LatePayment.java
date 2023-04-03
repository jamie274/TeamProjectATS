package AdvisorClasses;

import java.sql.*;

/*
        *
        * @author Jamie-Lee
        *
        * This class is used to read the sales table and determine which customers are eligible for late payments
        */
public class LatePayment {

    String customers = "";

    public LatePayment() {}

    public void latePaymentAlert(int staffID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + ", Date, Status FROM Sales Where AdvisorID =" + staffID);

            //inserts all the data in the respective columns
            while(result.next()){
                // if the status == 'can pay later', then the customer ID will be appended to the string 'customers'
                if (result.getString("Status").equals("can pay later")) {
                    customers += Integer.toString(result.getInt("CustomerID")) + ", ";
                }
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /*
            * This method will return the customers String and will remove the trailing ", "
            */
    public String getCustomers() {
        String c = "";
        for (int i = 0; i < customers.length() - 2; ++i) {
            c += customers.charAt(i);
        }
        return c;
    }

}