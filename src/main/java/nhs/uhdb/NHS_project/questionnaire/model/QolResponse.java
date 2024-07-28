package nhs.uhdb.NHS_project.questionnaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class QolResponse {

    private Long id;
    private Long user_id;
    private LocalDate createdAt;
    private Integer walking;
    private Integer bending;
    private Integer standing;
    private Integer gettingUp;
    private Integer occupation;
    private Integer housework;
    private Integer leisureActivities;
    private String leisureExamples;
    private Integer dependency;
    private Integer appearance;
    private Integer clothesFitDifficulty;
    private Integer clothesPreferenceDifficulty;
    private Integer shoesFitDifficulty;
    private Integer socksFitDifficulty;
    private Integer selfPerception;
    private Integer relationshipImpact;
    private Integer pain;
    private Integer numbness;
    private Integer pinsNeedles;
    private Integer legWeakness;
    private Integer legHeaviness;
    private Integer sleepTrouble;
    private Integer difficultyConcentrating;
    private Integer feelingTense;
    private Integer feelingWorried;
    private Integer feelingIrritable;
    private Integer feelingDepressed;
    private Integer qualityOfLife;

    public QolResponse() {
        this.id = null;
        this.walking = 0;
        this.bending = 0;
        this.standing = 0;
        this.gettingUp = 0;
        this.occupation = 0;
        this.housework = 0;
        this.leisureActivities = 0;
        this.leisureExamples = " ";
        this.dependency = 0;
        this.appearance = 0;
        this.clothesFitDifficulty = 0;
        this.clothesPreferenceDifficulty = 0;
        this.shoesFitDifficulty = 0;
        this.socksFitDifficulty = 0;
        this.selfPerception = 0;
        this.relationshipImpact = 0;
        this.pain = 0;
        this.numbness = 0;
        this.pinsNeedles = 0;
        this.legWeakness = 0;
        this.legHeaviness = 0;
        this.sleepTrouble = 0;
        this.difficultyConcentrating = 0;
        this.feelingTense = 0;
        this.feelingWorried = 0;
        this.feelingIrritable = 0;
        this.feelingDepressed = 0;
        this.qualityOfLife = 0;
    }


}
