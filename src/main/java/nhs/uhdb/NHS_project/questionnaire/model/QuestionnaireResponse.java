package nhs.uhdb.NHS_project.questionnaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class QuestionnaireResponse {

    private Long id;
    private String medications;
    private String changesToHealth;
    private String swellingConcerns;
    private String hosieryConcerns;
    private Integer cellulitisEpisodes;
    private LocalDateTime createdAt;

    public QuestionnaireResponse() {
        this.id = null;
        this.medications = "";
        this.changesToHealth = "";
        this.swellingConcerns = "";
        this.hosieryConcerns = "";
        this.cellulitisEpisodes = null;
    }

}
