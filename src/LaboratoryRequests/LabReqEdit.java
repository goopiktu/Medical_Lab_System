package LaboratoryRequests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import MainMenu.Terminal;

public class LabReqEdit extends LabRequest {
    Terminal con = new Terminal();

    public void editReq() throws IOException {
        deleteReq();
    }

    public void deleteReq() throws IOException {
        int checkSave = 1;

        do {
            System.out.println("Do you know Request's UID [Y/N]");
            String reqUIDCheck = con.sc.nextLine();

            if (reqUIDCheck.equals("N")) {
                PatientUID();
                RequestsLabUID();
                checkSave = 0;
            } else if (reqUIDCheck.equals("Y")) {
                RequestsLabUID();
                checkSave = 0;
            } else {
                System.out.println("Incorrect input, please try again");
                checkSave = 1;
            }
        } while (checkSave == 1);

    }

    public void RequestsLabUID() throws IOException {

        System.out.print("Request's UID: ");
        String reqUID = con.sc.nextLine();

        File file = new File("Database\\Lab\\" + UIDsplit(reqUID) + "_Requests.txt");
        Scanner scan = new Scanner(file);
        String scanStr;
        String[] scanStrSplit;
        while (scan.hasNextLine()) {
            scanStr = scan.nextLine();
            scanStrSplit = scanStr.split(";", 4);
            if (scanStrSplit[0].equals(reqUID)) {
                edit(scanStr, UIDsplit(reqUID));
            }
        }

        scan.close();
    }

    public void PatientUID() throws IOException {

        System.out.print("Patients's UID: ");
        String uid = con.sc.nextLine();

        File folder = new File("Database\\Lab\\");
        File filesList[] = folder.listFiles();
        StringBuffer sb = new StringBuffer();
        Scanner sc = null;

        String[] scanStrSplit;

        for (File file : filesList) {
            sc = new Scanner(file);
            String input;
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                scanStrSplit = input.split(";", 8);
                if (scanStrSplit[1].equals(uid)) {
                    sb.append(input + "\n");
                }
            }
        }
        System.out.println(sb.toString());
        sc.close();

    }

    public String UIDsplit(String text) {
        return text.substring(0, 3);
    }

    public void edit(String Str, String code) throws IOException {

        File file = new File("Database\\Lab\\" + code + "_Requests.txt");
        Scanner scan = new Scanner(file);
        String retryInput;
        String strNew;
        String[] strSplit;
        String strSplitIn;
        ArrayList<String> scanStrArray = new ArrayList<String>();
        int index = 0;
        int index2 = 1;

        while (scan.hasNextLine()) {
            scanStrArray.add(scan.nextLine());
            if (scanStrArray.get(index).equals(Str)) {
                strSplit = scanStrArray.get(index).split(";", 8);

                do {
                    System.out.println("================================");

                    System.out.print("New Result: ");
                    strSplitIn = con.sc.nextLine();

                    System.out.println("Retry Input? [Y/N] ");
                    retryInput = con.sc.nextLine();
                } while (retryInput.equals("Y"));
                System.out.print(strSplit[4]);
                strSplit[4] = strSplitIn;
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
        FileWriter fw = new FileWriter("Database\\Lab\\" + code + "_Requests.txt", false);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String string : scanStrArray) {
            bw.write(string);
            bw.newLine();
        }
        strSplit = Str.split(";", 4);
        System.out.println("Data of patient " + strSplit[0] + " has been updated");
        bw.close();

        scan.close();

    }

}
