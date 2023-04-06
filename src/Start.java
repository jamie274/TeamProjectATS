import AdminClasses.AddBlank;
import AdminClasses.AdministrationMenu;
import LoginPages.*;

import java.awt.*;
import java.io.File;

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
        new WelcomePage().setVisible(true);
    }

    public static void openManual() throws Exception {
        try {
            Desktop.getDesktop().open(new File("data/UserManual.pdf"));
        } catch (Exception e){
            System.out.println(e);
        }
    }

}

