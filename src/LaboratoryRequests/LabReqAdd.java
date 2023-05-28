package LaboratoryRequests;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import MainMenu.Terminal;


public class LabReqAdd extends LabRequest {
    Terminal con = new Terminal();
    RequestLabUID labUID = new RequestLabUID();
    LabRequest lab = new LabRequest();

    public void addReq() throws IOException {

        System.out.println("Patient UID: ");
        lab.setPatientUID(con.sc.nextLine());

        System.out.println("Service Code: ");
        lab.setReqUID(con.sc.nextLine());

        
        
        if (!searchUID(lab.getPatientUID()) || !searchCode(lab.getReqUID())) {
            System.out.println("Does not exist");
            return;
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd;HHmm");
            Date date = new Date();

            
            FileWriter fw = new FileWriter("Database\\Lab\\" + lab.getReqUID() + "_Requests.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            String LabReqUID = labUID.createLabUID(lab.getReqUID(), lab.getPatientUID(), countLineBufferedReader("Database\\Lab\\" + lab.getReqUID() + "_Requests.txt"));

            bw.write(LabReqUID);
            bw.write(";");
            bw.write(lab.getPatientUID());
            bw.write(";");
            bw.write(dateFormat.format(date));
            bw.write(";");
            bw.write("Positive;");
    
            
            bw.newLine();
            System.out.println("Laboratory Request" + LabReqUID + " has been added to file " + lab.getReqUID());
            bw.close();
        }    

    }
    
    public static int countLineBufferedReader(String fileName) {

        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null)
                lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;

    }

    public boolean searchUID(String input) throws IOException {
        File file = new File("Database\\Patient.txt");
        Scanner scan = new Scanner(file);


        String scanStr;
        String[] scanStrSplit;
        while (scan.hasNextLine()) {
            scanStr = scan.nextLine();
            scanStrSplit = scanStr.split(";", 12);
            if (scanStrSplit[0].equals(input)) {
                scan.close();
                return true;
            }
            
        }
        scan.close();
        return false;
    }

    public boolean searchCode(String input) throws IOException {
        File file = new File("Database\\Services.txt");
        Scanner scan = new Scanner(file);
       

        String scanStr;
        String[] scanStrSplit;
        while (scan.hasNextLine()) {
            scanStr = scan.nextLine();
            scanStrSplit = scanStr.split(";", 4);
            if (scanStrSplit[0].equals(input)) {
                scan.close();
                return true;
            }
        }
        scan.close();
        return false;
    }
}
