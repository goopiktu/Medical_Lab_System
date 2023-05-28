package LaboratoryRequests;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import MainMenu.Terminal;

public class LabReqSearch extends LabRequest {
    Terminal con = new Terminal();
    LabRequest request = new LabRequest();

    public void searchReq() throws IOException {

        System.out.println("Do you know the Request's UID? [Y/N]");
        String reqUIdCheck = con.sc.nextLine();

        if (reqUIdCheck.equals("N")) {

            System.out.print("Patient's UID: ");
            String patUID = con.sc.nextLine();
            PatientUID(patUID);

        } else if (reqUIdCheck.equals("Y")) {

            System.out.print("Request's UID: ");
            String reqUID = con.sc.nextLine();
            RequestsLabUID(UIDsplit(reqUID), reqUID);

        }

    }

    public void RequestsLabUID(String code, String uid) throws IOException {

        File file = new File("Database\\Lab\\" + code + "_Requests.txt");
        Scanner scan = new Scanner(file);
        String scanStr;
        String[] scanStrSplit;
        do {
            while (scan.hasNextLine()) {
                scanStr = scan.nextLine();
                scanStrSplit = scanStr.split(";", 6);
                if (scanStrSplit[0].equals(uid) && !(scanStrSplit[0].isEmpty())
                        && !(scanStrSplit[3].equals("D"))) {
                    System.out.printf("%s\t%s\t%s\t%s\t%s",
                            scanStrSplit[0],
                            scanStrSplit[1],
                            scanStrSplit[2],
                            scanStrSplit[3],
                            scanStrSplit[4],
                            scanStrSplit[5]);
                    con.found = true;
                }

            }

            if (!con.found) {
                do {
                    System.out.println("No record found");
                    System.out.println("Search Again[1] or Main Menu[2]");
                    con.searchError = Integer.parseInt(con.sc.nextLine());
                    if (con.searchError == 1) {
                        System.out.println("Searching Again");
                        con.searchRetry = 1;
                    } else if (con.searchError == 2) {
                        System.out.println("Going back to Main Menu...\n\n");
                        System.out.println("================================");
                        con.searchRetry = 0;
                    } else {
                        System.out.println("Incorrect Input, please try again");
                    }

                } while (!(con.searchError == 1) && !(con.searchError == 2));
                con.found = false;
            } else {
                con.searchRetry = 0;
                con.found = false;
            }
        } while (con.searchRetry == 1);

        scan.close();
    }

    public void PatientUID(String uid) throws IOException {

        File folder = new File("Database\\Lab\\");
        File filesList[] = folder.listFiles();
        StringBuffer sb = new StringBuffer();
        Scanner sc = null;
        String[] scanStrSplit;
        do {
            for (File file : filesList) {
                sc = new Scanner(file);
                String input;
                while (sc.hasNextLine()) {
                    input = sc.nextLine();
                    scanStrSplit = input.split(";", 6);
                    if (scanStrSplit[1].equals(uid) && !(scanStrSplit[0].isEmpty())
                            && !(scanStrSplit[3].equals("D"))) {
                        sb.append(input + "\n");
                        con.found = true;
                    }
                }
            }
            if (!con.found) {
                do {
                    System.out.println("No record found");
                    System.out.println("Search Again[1] or Main Menu[2]");
                    con.searchError = Integer.parseInt(con.sc.nextLine());
                    if (con.searchError == 1) {
                        System.out.println("Searching Again");
                        con.searchRetry = 1;
                    } else if (con.searchError == 2) {
                        System.out.println("Going back to Main Menu...\n\n");
                        System.out.println("================================");
                        con.searchRetry = 0;
                    } else {
                        System.out.println("Incorrect Input, please try again");
                    }

                } while (!(con.searchError == 1) && !(con.searchError == 2));
                con.found = false;
            } else {
                con.searchRetry = 0;
                con.found = false;
                System.out.println(sb.toString());
            }
        } while (con.searchRetry == 1);
        sc.close();

    }

    public String UIDsplit(String text) {
        return text.substring(0, 3);
    }

}
