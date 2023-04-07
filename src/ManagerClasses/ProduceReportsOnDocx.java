package ManagerClasses;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import java.awt.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Author: Abdul Rehman
 * */

public class ProduceReportsOnDocx {

    public static void ProduceReports(String UserName, String UserID, String Title, String FileName, ResultSet ResultSet) {

        try {
            XWPFDocument doc = new XWPFDocument();


            // Set the page orientation to landscape
            CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();

            // create paragraph for the title
            XWPFParagraph para = doc.createParagraph();
            para.setAlignment(ParagraphAlignment.CENTER);
            para.setVerticalAlignment(TextAlignment.CENTER);
            para.setSpacingAfter(200);
            // create run for the title
            XWPFRun run = para.createRun();
            run.setFontSize(44);
            run.setText(Title);

            // create paragraph for the user name and ID
            XWPFParagraph para2 = doc.createParagraph();
            para2.setAlignment(ParagraphAlignment.CENTER);
            para2.setVerticalAlignment(TextAlignment.CENTER);
            para2.setSpacingAfter(200);
            // create run for the user name and ID
            XWPFRun run2 = para2.createRun();
            run2.setText("Username: " + UserName + "     ID: " + UserID);

            // create paragraph for the "Create Table using Result Set" section
            XWPFParagraph para3 = doc.createParagraph();
            // create run for the "Create Table using Result Set" section
            XWPFRun run3 = para3.createRun();
            run3.setText("Create Table using Result Set:");


            // create table
            if (ResultSet != null && ResultSet.getMetaData() != null) {
                int numColumns = ResultSet.getMetaData().getColumnCount();
                // create table with one row for header
                XWPFTable table = doc.createTable();
                XWPFTableRow headerRow = table.getRow(0);
                if (headerRow == null) {
                    headerRow = table.createRow();
                }
                // set column names in the first row
                for (int i = 1; i <= numColumns; i++) {
                    String columnName = ResultSet.getMetaData().getColumnName(i);
                    XWPFTableCell headerCell = headerRow.getCell(i - 1);
                    if (headerCell == null) {
                        headerCell = headerRow.createCell();
                    }
                    headerCell.setText(columnName);
                }
                // create table rows
                while (ResultSet.next()) {
                    XWPFTableRow row = table.createRow();
                    // create cells for each column
                    ResultSetMetaData metaData = ResultSet.getMetaData();
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        String columnName = metaData.getColumnName(i);
                        String value = ResultSet.getString(i);
                        XWPFTableCell cell = row.getCell(i - 1);
                        if (cell == null) {
                            cell = row.createCell();
                        }
                        cell.setText(value);
                    }
                }
            }


            // write the document to file
            FileOutputStream out = new FileOutputStream(new File("reports/" + FileName + ".docx"));
            doc.write(out);
            out.close();
            System.out.println("Done");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void ConvertTOPDF(String FileName){
        try {
            com.aspose.words.Document doc = new com.aspose.words.Document("reports/" + FileName + ".docx");
            doc.save("reports/" + FileName + ".pdf");
            System.out.println("Docx Convert To Pdf Successfull!");
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void OpenReport(String FileName){
        try {
            Desktop.getDesktop().open(new File("reports/" + FileName + ".pdf"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
