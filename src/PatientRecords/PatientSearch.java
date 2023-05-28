package PatientRecords;

import java.io.File;
import java.util.Scanner;

import MainMenu.Terminal;
import LaboratoryRequests.*;
import java.io.IOException;

/**
 * PatientSearch
 */
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
public class PatientSearch extends Patient {
    Terminal con = new Terminal();
    Patient patient = new Patient();
    LabRequest request = new LabRequest();
    LabReqEdit edit = new LabReqEdit();

    public void searchPatient() throws IOException {

        int checkSave = 1;
        do {
            System.out.println("Do you know UID? [Y/N]");
            String uidCheck = con.sc.nextLine();
            if (uidCheck.equals("N")) {
                do {
                    System.out.println("Do you know the National Id? [Y/N]");
                    String natIDCheck = con.sc.nextLine();
                    if (natIDCheck.equals("N")) {
                        searchNameBday();
                        checkSave = 0;

                    } else if (natIDCheck.equals("Y")) {
                        searchNID();
                        checkSave = 0;
                    } else {
                        System.out.println("Incorrect input, please try again");
                        checkSave = 1;
                    }
                } while (checkSave == 1);
                checkSave = 0;

            } else if (uidCheck.equals("Y")) {
                searchUID();
                checkSave = 0;

            } else {
                System.out.println("Incorrect input, please try again");
                checkSave = 1;
            }
        } while (checkSave == 1);

    }

    public void pdf() throws IOException {

        System.out.print("Do you want to print a laboratory test result [Y/N]: ");
        String choice = con.sc.nextLine();

        if (choice.equals("Y")) {
            System.out.print("Enter request's UID: ");
            String reqUID = con.sc.nextLine();
            GeneratePDF.generatePDF(reqUID);
        } else {
            // return main menu
        }
    }

    public void searchUID() throws IOException {
        File file = new File("Database\\Patient.txt");
        Scanner scan = new Scanner(file);

        String UID;
        String retryInput;
        String scanStr;
        String[] scanStrSplit;
        do {
            do {
                System.out.println("================================");

                System.out.print("User ID: ");
                UID = con.sc.nextLine();

                System.out.println("Retry Input? [Y/N] ");
                retryInput = con.sc.nextLine();
            } while (retryInput.equals("Y"));

            while (scan.hasNextLine()) {
                scanStr = scan.nextLine();
                scanStrSplit = scanStr.split(";", 12);
                if (scanStrSplit[0].equals(UID) && !(scanStrSplit[0].isEmpty()) && !(scanStrSplit[9].equals("D"))) {
                    System.out.printf("%s\n%s, %s %s\n%s\n%s\n%s\n%s\n%s\n\n",
                            scanStrSplit[0],
                            scanStrSplit[1],
                            scanStrSplit[2],
                            scanStrSplit[3],
                            scanStrSplit[4],
                            scanStrSplit[5],
                            scanStrSplit[6],
                            scanStrSplit[7],
                            scanStrSplit[8]);
                    PatientUID(scanStrSplit[0]);
                    pdf();
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

    public void searchNID() throws IOException {
        File file = new File("Database\\Patient.txt");
        Scanner scan = new Scanner(file);

        String NatID;
        String retryInput;

        String scanStr;
        String[] scanStrSplit;
        do {
            do {
                System.out.println("================================");

                System.out.print("National ID Number: ");
                NatID = con.sc.nextLine();

                System.out.println("Retry Input? (yes or no): ");
                retryInput = con.sc.nextLine();
            } while (retryInput.equals("yes"));

            while (scan.hasNextLine()) {
                scanStr = scan.nextLine();
                scanStrSplit = scanStr.split(";", 12);
                if (scanStrSplit[8].equals(NatID) && !(scanStrSplit[0].isEmpty()) && !(scanStrSplit[9].equals("D"))) {
                    System.out.printf(
                            "Patient's UID: %-13s\nName: %s, %s %s\nBirthday: %-8s \nGender: %-6s \nAddress: %-30s \nPhone Number: %-12s \nNational ID no.: %-15s\n",
                            scanStrSplit[0],
                            scanStrSplit[1],
                            scanStrSplit[2],
                            scanStrSplit[3],
                            scanStrSplit[4],
                            scanStrSplit[5],
                            scanStrSplit[6],
                            scanStrSplit[7],
                            scanStrSplit[8]);
                    PatientUID(scanStrSplit[0]);
                    pdf();
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

    public void searchNameBday() throws IOException {
        File file = new File("Database\\Patient.txt");
        Scanner scan = new Scanner(file);

        String firstName;
        String lastName;
        String birthday;
        String retryInput;
        // String UID;
        String scanStr;
        String[] scanStrSplit;
        do {
            do {
                System.out.println("================================");

                System.out.print("Last Name: ");
                lastName = con.sc.nextLine();

                System.out.print("First Name: ");
                firstName = con.sc.nextLine();

                System.out.print("Birthday: ");
                birthday = con.sc.nextLine();

                System.out.println("Retry Input? [Y/N]");
                retryInput = con.sc.nextLine();
            } while (retryInput.equals("Y"));

            System.out.printf(
                    "Patient's UID Last Name\t\tFirst Name\t Middle Name\t Birthday Gender Address\t\t\tPhone Number National ID no.\n");
            while (scan.hasNextLine()) {
                scanStr = scan.nextLine();
                scanStrSplit = scanStr.split(";", 12);
                if (scanStrSplit[1].equals(lastName) && scanStrSplit[2].equals(firstName)
                        && scanStrSplit[4].equals(birthday) && !(scanStrSplit[0].isEmpty())
                        && !(scanStrSplit[9].equals("D"))) {
                    System.out.printf("%-13s %-17s %-16s %-15s %-8s %-6s %-30Ns %-12s %-15s\n",
                            scanStrSplit[0],
                            scanStrSplit[1],
                            scanStrSplit[2],
                            scanStrSplit[3],
                            scanStrSplit[4],
                            scanStrSplit[5],
                            scanStrSplit[6],
                            scanStrSplit[7],
                            scanStrSplit[8]);
                    PatientUID(scanStrSplit[0]);
                    pdf();
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
        Scanner sc = null;

        String[] scanStrSplit;
        System.out.printf("\nRequest's UID\tLab Test Type\t\tRequest Date\t Result\n");
        for (File file : filesList) {
            sc = new Scanner(file);
            String input;
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                scanStrSplit = input.split(";", 7);
                if (scanStrSplit[1].equals(uid)) {
                    System.out.printf("%-18s %-20s %-16s %-15s\n",
                            scanStrSplit[0],
                            SearchType(edit.UIDsplit(scanStrSplit[0])),
                            scanStrSplit[2],
                            scanStrSplit[4]);
                }
            }
        }
        sc.close();

    }

    public String SearchType(String code) throws IOException {
        File file = new File("Database\\Services.txt");
        Scanner scan = new Scanner(file);
        String scanStr;
        String[] scanStrSplit;
        String returnStr = "";
        while (scan.hasNextLine()) {
            scanStr = scan.nextLine();
            scanStrSplit = scanStr.split(";", 4);
            if (scanStrSplit[0].equals(code) && !(scanStrSplit[0].isEmpty()))
                returnStr = scanStrSplit[1];

        }
        scan.close();
        return returnStr;

    }

}
