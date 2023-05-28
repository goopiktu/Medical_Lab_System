package LaboratoryRequests;

import java.io.IOException;

public class ManageLaboratoryRequests extends RequestLabUID {
    // private ArrayList<LabRequest> serviceRequests;
    private LabReqSearch Search = new LabReqSearch();
    private LabReqEdit Edit = new LabReqEdit();
    private LabReqDelete Delete = new LabReqDelete();
    private LabReqAdd Add = new LabReqAdd();

    int choice = 0;

    public ManageLaboratoryRequests() {
        // this.serviceRequests = serviceRequests;
    }

    public void Search() throws IOException {
        Search.searchReq();
    }

    public void Edit() throws IOException {
        Edit.editReq();
    }

    public void Delete() throws IOException {
        Delete.deleteReq();
    }

    public void Add() throws IOException {
        Add.addReq();
    }

}