package PatientRecords;

import java.io.IOException;

public class ManagePatientRecords extends PatientUID {
    private PatientSearch search = new PatientSearch();
    private PatientEdit edit = new PatientEdit();
    private PatientDelete delete = new PatientDelete();
    private PatientAdd add = new PatientAdd();

    public void search() throws IOException {
        search.searchPatient();
    }

    public void edit() throws IOException {
        edit.editPatient();
    }

    public void delete() throws IOException {
        delete.deletePatient();
    }

    public void add() throws IOException {
        add.addPatient();
    }

}
