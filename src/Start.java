import LoginPages.StartPage;

/**
 *
 * @author Jamie-Lee
 *
 * This class is used as the ‘main’ class where the application gets initiated from as a new
 * ‘WelcomePage.java’ instance is created, and the user will start their navigation at the welcome page
 * and select their role to login to the system.
 */
public class Start {
    public static void main(String[] args) {
        // Starts the program at the welcome page
        new StartPage().setVisible(true);

        //Test the Writing to docx and convertion from docx to pdf
        /*
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            String d = "Date";

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT AdvisorID, ID, BlankType, Status," + d + "  FROM BlankStock");

            new ProduceReportsOnDocx().ProduceReports("Abdul Rehman", "200006572", "BlankStocks", "BlankStock", result);
            new ProduceReportsOnDocx().ConvertTOPDF("BlankStock");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

         */
    }


}

