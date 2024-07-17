package nhs.uhdb.NHS_project.questionnaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QolQuestionnaire {

    private Long id;
    private Integer walking;
    private Integer bending;
    private Integer standing;
    private Integer getting_up;
    private Integer occupation;
    private Integer housework;
    private String leisure_activities;
    private Integer dependency;
    private Integer appearance;
    private Integer clothes_fit_difficulty;
    private Integer clothes_preference_difficulty;
    private Integer shoes_fit_difficulty;
    private Integer socks_fit_difficulty;
    private Integer self_perception;
    private Integer relationship_impact;
    private Integer pain;
    private Integer numbness;
    private Integer pins_needles;
    private Integer leg_weakness;
    private Integer leg_heaviness;
    private Integer sleep_trouble;
    private Integer difficulty_concentrating;
    private Integer feeling_tense;
    private Integer feeling_worried;
    private Integer feeling_irritable;
    private Integer feeling_depressed;
    private Integer quality_of_life;

}
