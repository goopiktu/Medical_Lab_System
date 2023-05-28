package LaboratoryRequests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import MainMenu.Terminal;

public class LabReqDelete extends LabRequest {
    Terminal con = new Terminal();

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
            scanStrSplit = scanStr.split(";", 6);
            if (scanStrSplit[0].equals(reqUID)) {
                delete(scanStr, reqUID);
            }
        }

        scan.close();
    }

    public void PatientUID() throws IOException {

        System.out.print("Patiens's UID: ");
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
                scanStrSplit = input.split(";", 6);
                if (scanStrSplit[1].equals(uid)) {
                    sb.append(input + "\n");
                }
            }
        }
        System.out.println(sb.toString());
        sc.close();

    }

    public static String UIDsplit(String text) {
        return text.substring(0, 3);
    }

    public void delete(String Str, String code) throws IOException {

        File file = new File("Database\\Lab\\" + UIDsplit(code) + "_Requests.txt");
        Scanner scan = new Scanner(file);

        String retryInput;
        String delete;
        String strNew;
        // String[] strSplit;
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
        FileWriter fw = new FileWriter("Database\\Lab\\" + UIDsplit(code) + "_Requests.txt", false);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String string : scanStrArray) {
            bw.write(string);
            bw.newLine();
        }
        // strSplit = Str.split(";", 6);
        System.out.printf("%s has been deleted\n", code);
        bw.close();

        scan.close();
        fw.close();
    }
}
