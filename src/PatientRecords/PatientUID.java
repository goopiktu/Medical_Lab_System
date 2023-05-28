package PatientRecords;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * PatientUID 
 */
public class PatientUID {
    
    private char constant; 
    private String letters;  
    private String numbers;
    

    public char getConstant() {
        return constant;
    }

    public void setConstant(char constant) {
        this.constant = constant;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }


    public String createUID(int patient) {
        String UID;
        constant = 'P';
        UID = constant + date() + letters(patient) + numbers(patient); 
        return UID;
    }

    public String date() {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        
        return dateFormat.format(date);
    }


    public String letters(int patient) {
        ArrayList<String> uniqueLetters = new ArrayList<>();
        
        for(char c1 = 'A'; c1 <= 'Z'; c1++){
            for(char c2 = 'A'; c2 <= 'Z'; c2++){
                for(char c3 = 'A'; c3 <= 'Z'; c3++){
                    uniqueLetters.add("" + c1 + c2 + c3);
                }
            } 
        }
        patient /= 100;
        return uniqueLetters.get(patient);
    }

    public String numbers(int patient) {
        String temp = "";
        patient %= 100;
        temp = String.format("%02d", patient);
        return temp;
    }
}