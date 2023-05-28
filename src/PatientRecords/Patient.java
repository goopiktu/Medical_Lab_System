package PatientRecords;

public class Patient {
    private String patientUID;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthday;
    private String gender;
    private String address;
    private String phoneNum;
    private String nationIdNum;

    public Patient() {

        patientUID = "";
        firstName = "";
        lastName = "";
        middleName = "";
        birthday = "";
        gender = "";
        address = "";
        phoneNum = "";
        nationIdNum = "";
    }

    public Patient(String firstName,
            String lastnName,
            String middleName,
            String birthday,
            String gender,
            String address,
            String phoneNum,
            String nationalIdNum) {

        this.firstName = firstName;
        this.lastName = lastnName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phoneNum = phoneNum;
        this.nationIdNum = nationalIdNum;
    }

    public String getPatientUID() {
        return patientUID;
    }

    public void setPatientUID(String patientUID) {
        this.patientUID = patientUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNationalIdNum() {
        return nationIdNum;
    }

    public void setNationalIdNum(String nationalIdNum) {
        this.nationIdNum = nationalIdNum;
    }
}
