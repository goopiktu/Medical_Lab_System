package Services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import MainMenu.Terminal;

import java.io.IOException;

public class ServiceDelete extends Service {
    private Terminal con = new Terminal();

    public void deleteService() throws IOException {

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
        do {
            System.out.println("================================");

            System.out.print("Service Code: ");
            serviceCode = con.sc.nextLine();

            System.out.println("Retry Input? [Y/N] ");
            retryInput = con.sc.nextLine();
        } while (retryInput.equals("Y"));

        String scanStr;
        String[] scanStrSplit;
        while (scan.hasNextLine()) {
            scanStr = scan.nextLine();
            scanStrSplit = scanStr.split(";", 4);
            if (scanStrSplit[0].equals(serviceCode)) {
                delete(scanStr);
            }
        }

        scan.close();
    }

    public void searchDescription() throws IOException {
        File file = new File("Database\\Services.txt");
        Scanner scan = new Scanner(file);

        String serviceDescription;
        String retryInput;
        do {
            System.out.println("================================");

            System.out.print("Service Description: ");
            serviceDescription = con.sc.nextLine();

            System.out.println("Retry Input? [Y/N] ");
            retryInput = con.sc.nextLine();
        } while (retryInput.equals("Y"));

        String scanStr;
        String[] scanStrSplit;
        while (scan.hasNextLine()) {
            scanStr = scan.nextLine();
            scanStrSplit = scanStr.split(";", 4);

            if (scanStrSplit[1].contains(serviceDescription)) {
                delete(scanStr);
            }
        }

        scan.close();
    }

    public void delete(String Str) throws IOException {

        File file = new File("Database\\Services.txt");
        Scanner scan = new Scanner(file);

        String retryInput;
        String delete;
        String strNew;
        String[] strSplit;
        ArrayList<String> scanStrArray = new ArrayList<String>();
        int index = 0;

        do {
            System.out.println("================================");

            System.out.print("Reason for deletion: ");
            delete = con.sc.nextLine();

            System.out.println("Retry Input? [Y/N] ");
            retryInput = con.sc.nextLine();
        } while (retryInput.equals("Y"));

        strNew = Str + "D;" + delete + ";";

        while (scan.hasNextLine()) {
            scanStrArray.add(scan.nextLine());
            if (scanStrArray.get(index).equals(Str)) {
                scanStrArray.set(index, strNew);
            }
            index++;
        }
        FileWriter fw = new FileWriter("Database\\Services.txt", false);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String string : scanStrArray) {
            bw.write(string);
            bw.newLine();
        }
        strSplit = Str.split(";", 6);
        System.out.printf("%s %s has been deleted\n", strSplit[0], strSplit[1]);
        bw.close();

        scan.close();
        fw.close();
    }
}
