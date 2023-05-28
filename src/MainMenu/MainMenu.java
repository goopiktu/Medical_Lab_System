package MainMenu;

import java.io.IOException;

import LaboratoryRequests.*;
import PatientRecords.*;
import Services.*;

public class MainMenu {

    ManageServices manageServices = new ManageServices();
    ManageLaboratoryRequests laboratoryResults = new ManageLaboratoryRequests();
    ManagePatientRecords patientRecords = new ManagePatientRecords();
    private String choice;
    Terminal con = new Terminal();

    public void initializeData() {
    }

    public void menuConsole(/* resume */) {

    }

    // i think this is supposed to be a menu console?
    public void medicalLaboratoryInformationSystem() throws IOException {
        int check = 0;
        do {
            System.out.println("Medical Labroratory Information System");
            System.out.println("[1] Manage Patient Records");
            System.out.println("[2] Manage Services");
            System.out.println("[3] Manage Laboratory Results");
            System.out.println("[x] Exit Program\n");
            System.out.println("Select a transaction:");

            choice = con.sc.nextLine();

            switch (choice) {
                case "1":
                    patientConsole();
                    check = 1;
                    break;

                case "2":
                    ServicesConsole();
                    check = 1;
                    break;

                case "3":
                    labReqConsole();
                    check = 1;
                    break;
                case "x":
                    check = 0;
                    break;
                default:
                    System.out.println("Incorrect input");
                    check = 1;
                    break;
            }
        } while (check == 1);
        con.scannerClose();
    }

    public void patientConsole() throws IOException {
        int check = 0;
        do {
            System.out.println("Manage Patient Records");
            System.out.println("[1] Add New Patient");
            System.out.println("[2] Edit Patient Record");
            System.out.println("[3] Delete Patient Record");
            System.out.println("[4] Search Patient Record");
            System.out.println("[x] Return to Main Menu\n");
            System.out.println("Select a transaction:");
            choice = con.sc.nextLine();

            switch (choice) {
                case "1":
                    patientRecords.add();
                    check = 1;
                    break;

                case "2":
                    patientRecords.edit();
                    check = 1;
                    break;

                case "3":
                    patientRecords.delete();
                    check = 1;
                    break;

                case "4":
                    patientRecords.search();
                    check = 1;
                    break;

                case "x":
                    check = 0;
                    break;

                default:
                    System.out.println("Incorrect input");
                    check = 1;
                    break;
            }

        } while (check == 1);
    }

    public void ServicesConsole() throws IOException {
        int check = 0;
        do {
            System.out.println("Manage Services");
            System.out.println("[1] Add New Service");
            System.out.println("[2] Edit Service");
            System.out.println("[3] Delete Service");
            System.out.println("[4] Search Service");
            System.out.println("[x] Return to Main Menu\n");
            System.out.println("Select a transaction:");
            choice = con.sc.nextLine();

            switch (choice) {
                case "1":
                    manageServices.add();
                    check = 1;
                    break;

                case "2":
                    manageServices.edit();
                    check = 1;
                    break;

                case "3":
                    manageServices.delete();
                    check = 1;
                    break;

                case "4":
                    manageServices.search();
                    check = 1;
                    break;

                case "x":
                    check = 0;
                    break;

                default:
                    System.out.println("Incorrect input");
                    check = 1;
                    break;
            }
        } while (check == 1);
    }

    public void labReqConsole() throws IOException {
        int check = 0;
        do {
            System.out.println("Manage Laboratory Requests");
            System.out.println("[1] Add New Laboratory Request");
            System.out.println("[2] Edit Laboratory Request");
            System.out.println("[3] Delete Laboratory Request");
            System.out.println("[4] Search Laboratory Request");
            System.out.println("[x] Return to Main Menu\n");
            System.out.println("Select a transaction: ");
            choice = con.sc.nextLine();

            switch (choice) {
                case "1":
                    laboratoryResults.Add();
                    check = 1;
                    break;

                case "2":
                    laboratoryResults.Edit();
                    check = 1;
                    break;

                case "3":
                    laboratoryResults.Delete();
                    check = 1;
                    break;

                case "4":
                    laboratoryResults.Search();
                    check = 1;
                    break;

                case "x":
                    check = 0;
                    break;

                default:
                    System.out.println("Incorrect input");
                    check = 1;
                    break;

            }
        } while (check == 1);
    }

}