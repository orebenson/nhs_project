package nhs.uhdb.NHS_project.questionnaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PreappointmentResponse {

    private Long id;
    private Long user_id;
    private LocalDate createdAt;
    private String medications;
    private String changesToHealth;
    private String swellingConcerns;
    private String hosieryConcerns;
    private Integer cellulitisEpisodes;


    public PreappointmentResponse() {
        this.id = null;
        this.medications = "";
        this.changesToHealth = "";
        this.swellingConcerns = "";
        this.hosieryConcerns = "";
        this.cellulitisEpisodes = null;
    }

}
