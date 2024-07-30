package nhs.uhdb.NHS_project.questionnaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<CellulitisIncident> episodes;


    public PreappointmentResponse() {
        this.id = null;
        this.medications = "";
        this.changesToHealth = "";
        this.swellingConcerns = "";
        this.hosieryConcerns = "";
        this.cellulitisEpisodes = 0;
        this.episodes = new ArrayList<>();
    }

}
