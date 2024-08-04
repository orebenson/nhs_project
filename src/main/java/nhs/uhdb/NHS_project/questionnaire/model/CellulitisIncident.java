package nhs.uhdb.NHS_project.questionnaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;


//Using Lombok annotation to generate getters and setters, including constructors
@Data
@AllArgsConstructor
public class CellulitisIncident {
    private String dateOfCellulitis;
    private String areaAffected;
    private String redness;
    private String painDiscomfort;
    private String warmTouch;
    private String swellingWorsen;
    private String blisters;
    private String raisedTemperature;
    private String fluSymptoms;
    private String adviceVisit;
    private String oralAntibiotics;
    private String courseDuration;
    private String ivAntibiotics;
    private String hospitalAdmission;
    private String lymphoedemaClinicContact;
    private String comments;

    public CellulitisIncident() {
        this.dateOfCellulitis = "";
        this.areaAffected = "";
        this.redness = "";
        this.painDiscomfort = "";
        this.warmTouch = "";
        this.swellingWorsen = "";
        this.blisters = "";
        this.raisedTemperature = "";
        this.fluSymptoms = "";
        this.adviceVisit = "";
        this.oralAntibiotics = "";
        this.courseDuration = "";
        this.ivAntibiotics = "";
        this.hospitalAdmission = "";
        this.lymphoedemaClinicContact = "";
        this.comments = "";
    }

}
