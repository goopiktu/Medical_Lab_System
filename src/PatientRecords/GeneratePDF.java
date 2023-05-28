package PatientRecords;

import java.io.IOException;

import java.io.File;
import java.util.Scanner;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.element.Cell;

import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import LaboratoryRequests.LabReqSearch;
import LaboratoryRequests.LabRequest;

import Services.Service;

/*
 * 0 - UID
 * 1 - Last Name
 * 2 - First Name
 * 3 - Middle Name
 * 4 - Birthday
 * 5 - Gender
 * 6 - Address
 * 7 - Phone Number
 * 8 - National ID no.
 */
public class GeneratePDF {

    public static void generatePDF(String reqUID) throws IOException {

        Patient patient = new Patient();
        Service service = new Service();
        LabRequest request = new LabRequest();
        LabReqSearch req = new LabReqSearch();

        File file = new File("Database\\Patient.txt");
        Scanner scan = new Scanner(file);
        String scanStr;
        String[] scanStrSplit;

        File file2 = new File("Database\\Lab\\" + req.UIDsplit(reqUID) + "_Requests.txt");
        Scanner scan2 = new Scanner(file2);
        while (scan2.hasNextLine()) {
            scanStr = scan2.nextLine();
            scanStrSplit = scanStr.split(";", 6);
            if (scanStrSplit[0].equals(reqUID)) {
                request.setPatientUID(scanStrSplit[1]);
                request.setReqDate(scanStrSplit[2]);
                request.setResult(scanStrSplit[4]);
            }

        }
        scan2.close();

        while (scan.hasNextLine()) {
            scanStr = scan.nextLine();
            scanStrSplit = scanStr.split(";", 12);
            if (scanStrSplit[0].equals(request.getPatientUID())) {
                patient.setLastName(scanStrSplit[1]);
                patient.setFirstName(scanStrSplit[2]);
                patient.setMiddleName(scanStrSplit[3]);
                patient.setBirthday(scanStrSplit[4]);
                patient.setGender(scanStrSplit[5]);
                patient.setAddress(scanStrSplit[6]);
                patient.setPhoneNum(scanStrSplit[7]);

            }
        }
        scan.close();

        File file1 = new File("Database\\Services.txt");
        Scanner scan1 = new Scanner(file1);
        while (scan1.hasNextLine()) {
            scanStr = scan1.nextLine();
            scanStrSplit = scanStr.split(";", 4);
            if (scanStrSplit[0].equals(req.UIDsplit(reqUID))) {
                service.setServiceCode(scanStrSplit[0]);
                service.setDescription(scanStrSplit[1]);
                service.setPrice(scanStrSplit[2]);
            }
        }
        scan1.close();

        String dest = "Database\\pdfout\\" + patient.getLastName() + "_" + req.UIDsplit(reqUID) + "_" + ".pdf";

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);
        Border b1 = new DashedBorder(Color.WHITE, 1);

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

        float[] pointColumnWidths1 = { 512f };
        float[] pointColumnWidths2 = { 256F, 256F };

        Table table1 = new Table(pointColumnWidths1);
        Table table2 = new Table(pointColumnWidths2);
        Table table3 = new Table(pointColumnWidths2);

        PdfPage pdfPage = pdf.addNewPage();
        PdfCanvas canvas1 = new PdfCanvas(pdfPage);
        canvas1.moveTo(38, 400);
        canvas1.lineTo(550, 400);
        canvas1.closePathStroke();

        PdfCanvas canvas2 = new PdfCanvas(pdfPage);
        canvas2.moveTo(38, 580);
        canvas2.lineTo(550, 580);
        canvas2.closePathStroke();

        PdfCanvas canvas3 = new PdfCanvas(pdfPage);
        canvas3.moveTo(38, 688);
        canvas3.lineTo(550, 688);
        canvas3.closePathStroke();

        Cell companyLogo = new Cell();
        companyLogo.add("COMP LOGO");
        companyLogo.setBorder(b1);
        companyLogo.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(companyLogo);

        Cell address = new Cell();
        address.add("Address: 2401 Taft Ave, Malate, Manila, 1004 Metro Manila");
        address.setBorder(b1);
        address.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(address);

        Cell telephoneNumber = new Cell();
        telephoneNumber.add("Telephone: 465-8900");
        telephoneNumber.setBorder(b1);
        telephoneNumber.setTextAlignment(TextAlignment.CENTER);
        table1.addCell(telephoneNumber);

        Cell c1 = new Cell();
        c1.add("Name: " + patient.getLastName() + "," + patient.getFirstName() + " " + patient.getMiddleName());
        table2.addCell(c1);

        Cell c2 = new Cell();
        c2.add("Specimen ID: " + reqUID);
        c2.setBorder(b1);
        table2.addCell(c2);

        Cell c3 = new Cell();
        c3.add("Patient ID: " + patient.getPatientUID());
        c3.setBorder(b1);
        table2.addCell(c3);

        Cell c4 = new Cell();
        c4.add("Collection Date: " + request.getReqDate());
        c4.setBorder(b1);
        table2.addCell(c4);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate d1 = LocalDate.parse(patient.getBirthday(), df);
        LocalDate d2 = LocalDate.parse(request.getReqDate(), df);

        Cell c5 = new Cell();
        c5.add("Age: " + ChronoUnit.YEARS.between(d1, d2));
        c5.setBorder(b1);
        table2.addCell(c5);

        Cell c6 = new Cell();
        c6.add("Birthday: " + patient.getBirthday());
        c6.setBorder(b1);
        table2.addCell(c6);

        Cell c7 = new Cell();
        c7.add("Gender: " + patient.getGender());
        c7.setBorder(b1);
        table2.addCell(c7);

        Cell c8 = new Cell();
        c8.add("Phone Number: " + patient.getPhoneNum());
        c8.setBorder(b1);
        table2.addCell(c8);

        table2.setMargin(50f);

        // Adding cells to the table
        Cell test = new Cell();
        test.add("Test");
        test.setFont(font);
        table3.addCell(test);

        Cell result = new Cell();
        result.add("Result");
        result.setFont(font);
        table3.addCell(result);

        Cell c9 = new Cell();
        c9.add(service.getDescription());
        table3.addCell(c9);

        Cell c10 = new Cell();
        c10.add(request.getResult());
        table3.addCell(c10);

        // Adding Table to document
        doc.add(table1);
        doc.add(table2);
        doc.add(table3);
        // DrawLine(writer,pdf);

        // Closing the document
        doc.close();
        System.out.println("Table created successfully..");
    }

}
