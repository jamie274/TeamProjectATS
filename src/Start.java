import AdminClasses.AdministrationMenu;
import LoginPages.*;

public class Start {
    public static void main(String args[]) {
        //new WelcomePage().setVisible(true);
        new AdministrationMenu("200006572", "Abdul").setVisible(true);
    }
}

