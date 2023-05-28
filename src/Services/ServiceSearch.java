package Services;

import MainMenu.Terminal;

import java.io.File;

import java.util.Scanner;
import java.io.IOException;

public class ServiceSearch extends Service {
    private Terminal con = new Terminal();

    public void searchService() throws IOException {
        int checkSave = 1;

        do {
            System.out.println("Do you know Service Code? [Y/N]");
            String codeCheck = con.sc.nextLine();
            if (codeCheck.equals("N")) {
                searchDescription();
                checkSave = 0;
            } else if (codeCheck.equals("Y")) {
                searchCode();
                checkSave = 0;
            } else {
                System.out.println("Incorrect input, please try again");
                checkSave = 1;
            }
        } while (checkSave == 1);
    }

    public void searchCode() throws IOException {
        File file = new File("Database\\Services.txt");
        Scanner scan = new Scanner(file);
        String serviceCode;
        String retryInput;

        String scanStr;
        String[] scanStrSplit;
        do {
            do {
                System.out.println("================================");

                System.out.print("Service Code: ");
                serviceCode = con.sc.nextLine();

                System.out.println("Retry Input? [Y/N] ");
                retryInput = con.sc.nextLine();
            } while (retryInput.equals("Y"));

            while (scan.hasNextLine()) {
                scanStr = scan.nextLine();
                scanStrSplit = scanStr.split(";", 4);
                if (scanStrSplit[0].equals(serviceCode) && !(scanStrSplit[0].isEmpty())
                        && !(scanStrSplit[3].equals("D"))) {
                    System.out.printf("%s\t%s\t%s\n\n",
                            scanStrSplit[0],
                            scanStrSplit[1],
                            scanStrSplit[2]);
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

    public void searchDescription() throws IOException {
        File file = new File("Database\\Services.txt");
        Scanner scan = new Scanner(file);
        String serviceDescription;
        String retryInput;
        String scanStr;
        String[] scanStrSplit;
        do {
            do {
                System.out.println("================================");

                System.out.print("Service Description: ");
                serviceDescription = con.sc.nextLine();

                System.out.println("Retry Input? [Y/N] ");
                retryInput = con.sc.nextLine();
            } while (retryInput.equals("Y"));

            while (scan.hasNextLine()) {
                scanStr = scan.nextLine();
                scanStrSplit = scanStr.split(";", 4);

                if (scanStrSplit[1].contains(serviceDescription) && !(scanStrSplit[0].isEmpty())
                        && !(scanStrSplit[3].equals("D"))) {
                    System.out.printf("%s\t%s\t%s\n\n",
                            scanStrSplit[0],
                            scanStrSplit[1],
                            scanStrSplit[2]);
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

}
