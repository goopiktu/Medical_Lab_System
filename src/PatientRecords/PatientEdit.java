package PatientRecords;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import MainMenu.Terminal;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * PatientEdit
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
public class PatientEdit extends Patient {
    Terminal con = new Terminal();

    public void editPatient() throws IOException {
        int checkSave = 1;

        do {
            System.out.println("Do you know UID[Y/N]? ");
            String uidCheck = con.sc.nextLine();
            if (uidCheck.equals("N")) {
                do {
                    System.out.println("Do you know the National Id[Y/N]? ");
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

    public void searchUID() throws IOException {
        File file = new File("Database\\Patient.txt");
        Scanner scan = new Scanner(file);
        String UID;
        String retryInput;

        do {
            System.out.println("================================");

            System.out.print("User ID: ");
            UID = con.sc.nextLine();

            System.out.println("Retry Input? [Y/N]");
            retryInput = con.sc.nextLine();
        } while (retryInput.equals("Y"));
        String scanStr;
        String[] scanStrSplit;
        while (scan.hasNextLine()) {
            scanStr = scan.nextLine();
            scanStrSplit = scanStr.split(";", 12);
            if (scanStrSplit[0].equals(UID)) {
                edit(scanStr);
            }

        }
        scan.close();
    }

    public void searchNID() throws IOException {
        File file = new File("Database\\Patient.txt");
        Scanner scan = new Scanner(file);
        String NatID;
        String retryInput;
        do {
            System.out.println("================================");

            System.out.print("National ID Number: ");
            NatID = con.sc.nextLine();

            System.out.println("Retry Input? [Y/N]");
            retryInput = con.sc.nextLine();
        } while (retryInput.equals("Y"));
        String scanStr;
        String[] scanStrSplit;
        while (scan.hasNextLine()) {
            scanStr = scan.nextLine();
            scanStrSplit = scanStr.split(";", 12);
            if (scanStrSplit[8].equals(NatID)) {
                edit(scanStr);
            }

        }
        scan.close();
    }

    public void searchNameBday() throws IOException {
        File file = new File("Database\\Patient.txt");
        Scanner scan = new Scanner(file);
        String firstName;
        String lastName;
        String birthday;
        String retryInput;
        String UID;

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
        String scanStr;
        String[] scanStrSplit;
        while (scan.hasNextLine()) {
            scanStr = scan.nextLine();
            scanStrSplit = scanStr.split(";", 12);
            if (scanStrSplit[1].equals(lastName) && scanStrSplit[2].equals(firstName) && scanStrSplit[4]
                    .equals(birthday)) {
                System.out.printf("%-13s %-17s %-16s %-15s %-8s %-6s %-30Ns %-12s %-15s\n",
                        scanStrSplit[0],
                        scanStrSplit[1],
                        scanStrSplit[2],
                        scanStrSplit[3],
                        scanStrSplit[4],
                        scanStrSplit[5],
                        scanStrSplit[6],
                        scanStrSplit[7],
                        scanStrSplit[8],
                        scanStrSplit[9]);
            }

        }
        do {
            System.out.println("================================");

            System.out.print("Enter the Patient's UID that you want to diplay: ");
            UID = con.sc.next();

            System.out.println("Retry Input? [Y/N]");
            retryInput = con.sc.nextLine();
        } while (retryInput.equals("Y"));
        while (scan.hasNextLine()) {
            scanStr = scan.nextLine();
            scanStrSplit = scanStr.split(";", 12);
            if (scanStrSplit[8].equals(UID)) {
                edit(scanStr);
            }

        }
        scan.close();
    }

    public void edit(String Str) throws IOException {
        // input the (modified) file content to the StringBuffer "input"
        File file = new File("Database\\Patient.txt");
        Scanner scan = new Scanner(file);
        String retryInput;
        int edit;
        String strNew;
        String[] strSplit;
        String strSplitIn;
        ArrayList<String> scanStrArray = new ArrayList<String>();
        int index = 0;
        int index2 = 1;

        do {
            System.out.println("================================");

            System.out.print("What to edit? [1-Address, 2-Phone Number]: ");
            edit = Integer.parseInt(con.sc.nextLine());

            System.out.println("Retry Input? [Y/N]");
            retryInput = con.sc.nextLine();
        } while (retryInput.equals("Y"));

        switch (edit) {
            case 1:
                while (scan.hasNextLine()) {
                    scanStrArray.add(scan.nextLine());
                    if (scanStrArray.get(index).equals(Str)) {
                        strSplit = scanStrArray.get(index).split(";", 12);
                        do {
                            System.out.println("================================");

                            System.out.print("New Address: ");
                            strSplitIn = con.sc.nextLine();

                            System.out.println("Retry Input? [Y/N]");
                            retryInput = con.sc.nextLine();
                        } while (retryInput.equals("Y"));
                        strSplit[6] = strSplitIn;
                        strNew = strSplit[0] + ";";
                        while (index2 < strSplit.length - 1) {
                            if (!(strSplit[index2].isEmpty()))
                                strNew = strNew.concat(strSplit[index2] + ";");
                            index2++;
                        }
                        scanStrArray.set(index, strNew);
                    }
                    index++;
                }
                break;
            case 2:
                while (scan.hasNextLine()) {
                    scanStrArray.add(scan.nextLine());
                    if (scanStrArray.get(index).equals(Str)) {
                        strSplit = scanStrArray.get(index).split(";", 12);
                        do {
                            System.out.println("================================");

                            System.out.print("New Phone Number: ");
                            strSplitIn = con.sc.nextLine();

                            System.out.println("Retry Input? [Y/N]");
                            retryInput = con.sc.nextLine();
                        } while (retryInput.equals("Y"));
                        strSplit[7] = strSplitIn;
                        strNew = strSplit[0] + ";";
                        while (index2 < strSplit.length) {
                            if (!(strSplit[index2].isEmpty()))
                                strNew = strNew.concat(strSplit[index2] + ";");
                            index2++;
                        }
                        scanStrArray.set(index, strNew);
                    }
                    index++;
                }
                break;
        }
        FileWriter fw = new FileWriter("Database\\Patient.txt", false);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String string : scanStrArray) {
            bw.write(string);
            bw.newLine();
        }
        strSplit = Str.split(";", 12);
        System.out.println("Data of patient " + strSplit[0] + " has been successfully updated!");
        bw.close();

        scan.close();

    }
}