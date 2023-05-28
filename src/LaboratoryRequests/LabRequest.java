package LaboratoryRequests;

public class LabRequest {
    private String reqUID;
    private String patientUID;
    private String reqDate;
    private String reqTime;
    private String result;



    public LabRequest() {
        reqUID         = "";
        patientUID     = "";
        reqDate        = "";
        reqTime        = "";
        result         = "";
    }

    public LabRequest(String reqUID,
                      String patientUID,
                      String reqDate,
                      String reqTime,
                      String result,
                      String reqDescription) {
        
        this.reqUID         = reqUID;
        this.patientUID     = patientUID;
        this.reqDate        = reqDate;
        this.reqTime        = reqTime;
        this.result         = result;
    }


    

    public String getReqUID() {
        return reqUID;
    }

    public void setReqUID(String reqUID) {
        this.reqUID = reqUID;
    }

    public String getPatientUID() {
        return patientUID;
    }

    public void setPatientUID(String patientUID) {
        this.patientUID = patientUID;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
