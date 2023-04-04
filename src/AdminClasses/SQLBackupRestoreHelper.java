package AdminClasses;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLBackupRestoreHelper {

    public static void Restoredbfromsql(String mysqlPath) {
        try {
            String dbPassword = "R8pV1HmN";

            if (mysqlPath != null && !mysqlPath.isEmpty()) {
                mysqlPath = mysqlPath.trim();
                System.out.println(mysqlPath);
                String backupFileName = "backup_db.sql"; // Replace with actual backup file name
                List<String> command = new ArrayList<>();
                command.add(mysqlPath );
                command.add("-h");
                command.add("smcse-stuproj00.city.ac.uk");
                command.add("-u");
                command.add("in2018g08_a");
                command.add("-p" + dbPassword);
                command.add("in2018g08");
                command.add("-e");
                command.add("source " + backupFileName);
                ProcessBuilder pb = new ProcessBuilder(command);
                Process process2 = pb.start();
                int processComplete = process2.waitFor();
                if (processComplete == 0) {
                    System.out.println("Database has been restored successfully.");
                    JOptionPane.showMessageDialog(null, "Database has been Restored successfully.");
                } else {
                    System.out.println("Error during database restore.");
                    JOptionPane.showMessageDialog(null, "Database Restore has been unsuccessfully.");
                }
            } else {
                System.out.println("MySQL path not found!");
                JOptionPane.showMessageDialog(null, """
                        Please wait couple of Seconds and Try Again
                        Thank you""");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void Backupdbtosql(String mysqlPath) {
        try {
            String dbPassword = "R8pV1HmN";


            if (mysqlPath != null && !mysqlPath.isEmpty()) {
                mysqlPath = mysqlPath.trim();
                String savePath = "backup_db.sql"; //_" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + "
                List<String> command = new ArrayList<>();
                command.add(mysqlPath );
                command.add("--skip-column-statistics");
                command.add("-h");
                command.add("smcse-stuproj00.city.ac.uk");
                command.add("-u");
                command.add("in2018g08_a");
                command.add("-p" + dbPassword);
                command.add("in2018g08");
                ProcessBuilder pb = new ProcessBuilder(command);
                pb.redirectOutput(new File(savePath));
                Process process2 = pb.start();
                int processComplete = process2.waitFor();
                if (processComplete == 0) {
                    System.out.println("Backup created successfully for " + "in2018g08" + " at " + savePath);
                    JOptionPane.showMessageDialog(null, "Database has been Backed Up successfully.");
                } else {
                    System.out.println("Could not create the backup for " + "in2018g08");
                    JOptionPane.showMessageDialog(null, "Database Backup has been unsuccessfully.");
                }
            } else {
                System.out.println("MySQL path not found!");
                JOptionPane.showMessageDialog(null, """
                        Please wait couple of Seconds and Try Again
                        Thank you""");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
