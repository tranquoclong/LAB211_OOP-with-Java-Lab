package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Injection {
    private String injectionID;
    private String studentID;
    private String vaccineID;
    private String firstPlace;
    private String secondPlace;
    private Date firstDate;
    private Date secondDate;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public Injection() {
    }

    public Injection(String injectionID, String studentID, String vaccineID, String firstPlace, Date firstDate) {
        this.injectionID = injectionID;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
        this.firstPlace = firstPlace;
        this.firstDate = firstDate;
        this.secondPlace = null;
        this.secondDate = null;
    }

    public Injection(String injectionID, String studentID, String vaccineID, String firstPlace, Date firstDate,
                     String secondPlace, Date secondDate) {
        this.injectionID = injectionID;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    public String getInjectionID() {
        return injectionID;
    }

    public void setInjectionID(String injectionID) {
        this.injectionID = injectionID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(Date secondDate) {
        this.secondDate = secondDate;
    }

    @Override
    public String toString() {
        return injectionID  + ";" + studentID + ";" + vaccineID + ";" + firstPlace  + ";" + dateFormat.format(firstDate)  + ";" + secondPlace  + ";" + handleSecondDateFormat(secondDate) ;
    }
  private String handleSecondDateFormat(Date secondDate) {
    return secondDate == null ? "null" : dateFormat.format(secondDate);
  }
}