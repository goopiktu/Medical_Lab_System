package LaboratoryRequests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



import Services.Service;

/**
 * Request unique identifier
 */

public class RequestLabUID {

    Service service = new Service();

    // private String serviceCode = service.getServiceCode();
    // private char A1;
    // private char A2;
    // private String AA;
    // private int numbers;

    public String createLabUID(String serviceCode, String patUID, int entries) {
        String ID = "";

        ID = serviceCode + date() + letters(entries) + numbers(entries);

        return ID;
    }

    public String date() {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();

        return dateFormat.format(date);
    }

    public String letters(int entries) {

        ArrayList<String> uniqueLetters = new ArrayList<>();

        for (char c1 = 'A'; c1 <= 'Z'; c1++) {
            for (char c2 = 'A'; c2 <= 'Z'; c2++) {
                uniqueLetters.add("" + c1 + c2);
            }
        }
        entries /= 10;
        return uniqueLetters.get(entries);
    }

    public String numbers(int entries) {
        String temp = "";
        entries %= 100;
        temp = String.format("%02d", entries);
        return temp;
    }

}