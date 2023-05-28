package PatientRecords;

import MainMenu.Terminal;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class PatientAdd extends Patient {
    Terminal con = new Terminal();

    public void addPatient() throws IOException {
        PatientUID uid = new PatientUID();
        String retryInput;
        Patient patient = new Patient();

        do {
            System.out.println("================================");

            System.out.print("First Name: ");
            patient.setFirstName(con.sc.nextLine());

            System.out.print("Last Name: ");
            patient.setLastName(con.sc.nextLine());

            System.out.print("Middle Name: ");
            patient.setMiddleName(con.sc.nextLine());

            System.out.print("Birthday(YYYYMMDD): ");
            patient.setBirthday(con.sc.nextLine());

            System.out.print("Gender(M or F): ");
            patient.setGender(con.sc.nextLine());

            System.out.print("Address: ");
            patient.setAddress(con.sc.nextLine());

            System.out.print("Phone Number: ");
            patient.setPhoneNum(con.sc.nextLine());

            System.out.print("National ID Number: ");
            patient.setNationalIdNum(con.sc.nextLine());

            System.out.printf(
                    "\n\n===============\nFull Name: %s %s %s\nBirthday: %s\nGender: %s\nAddress: %s\nPhone Number: %s\nNational ID Number: %s\n",
                    patient.getFirstName(),
                    patient.getMiddleName(),
                    patient.getLastName(),
                    patient.getBirthday(),
                    patient.getGender(),
                    patient.getAddress(),
                    patient.getPhoneNum(),
                    patient.getNationalIdNum());

            System.out.println("Retry Input? [Y/N]");
            retryInput = con.sc.nextLine();
        } while (retryInput.equals("Y"));

        int checkSave = 1;
        do {
            System.out.println("Save Patient Record? [Y/N]");
            String savePatient = con.sc.nextLine();
            if (savePatient.equals("N")) {
                System.out.print("Patient Record not saved!");
                checkSave = 0;

            } else if (savePatient.equals("Y")) {
                FileWriter fw = new FileWriter("Database\\Patient.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(uid.createUID(countLineBufferedReader("Database\\Patient.txt")));
                bw.write(";");
                bw.write(patient.getLastName());
                bw.write(";");
                bw.write(patient.getFirstName());
                bw.write(";");
                bw.write(patient.getMiddleName());
                bw.write(";");
                bw.write(patient.getBirthday());
                bw.write(";");
                bw.write(patient.getGender());
                bw.write(";");
                bw.write(patient.getAddress());
                bw.write(";");
                bw.write(patient.getPhoneNum());
                bw.write(";");
                bw.write(patient.getNationalIdNum());
                bw.write(";");
                bw.newLine();
                System.out.println("Patient Record saved!");
                bw.close();
                checkSave = 0;

            } else {
                System.out.println("Incorrect input, please try again");
                checkSave = 1;
            }
        } while (checkSave == 1);

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

}
