package AdminClasses;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SQLBackupRestoreHelper {
    public static void Backupdbtosql() {
        final String dbPassword = "R8pV1HmN";
        try {
            /*NOTE: Used to create a cmd command*/
            String executeCmd = "\"mysql-8.0.32-winx64\\bin\\mysqldump.exe\" --skip-column-statistics -h smcse-stuproj00.city.ac.uk -u in2018g08_a -p"+ dbPassword + " in2018g08 > backup_db.sql";

            //mysql -u in2018g08_a -p R8pV1HmN -h smcse-stuproj00.city.ac.uk in2018g08
            /*NOTE: Create a new ProcessBuilder and redirect error stream to output stream*/
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", executeCmd);
            processBuilder.redirectErrorStream(true);

            /*NOTE: Start the process and read the output stream*/
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            /*NOTE: Wait for the process to complete*/
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                JOptionPane.showMessageDialog(null, "Database has been Backed Up successfully.");
                System.out.println("Backup Complete");
            } else {
                JOptionPane.showMessageDialog(null, "Error during database Backup");
                System.out.println("Backup Failure");
            }


        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
    }

    public static void Restoredbfromsql() {
        try {
            try {

                String[] executeCmd = new String[] { "\"mysql-8.0.32-winx64\\bin\\mysql.exe\" -h smcse-stuproj00.city.ac.uk -u in2018g08_a -pR8pV1HmN in2018g08 -e \"source backup_db.sql\"" };
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();
                if (processComplete == 0) {
                    JOptionPane.showMessageDialog(null, "Database has been restored successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error during database restore.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error during database restore: " + e.getMessage());
            }

        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }

    }
}
