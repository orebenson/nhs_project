package nhs.uhdb.NHS_project.questionnaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class QolResponseArm {
    private Long id;
    private Long user_id;
    private LocalDate createdAt;
    private Integer occupation;
    private Integer housework;
    private Integer combing_hair;
    private Integer dressing;
    private Integer writing;
    private Integer eating;
    private Integer washing;
    private Integer cleaning_teeth;
    private Integer leisure;
    private String leisure_examples;
    private Integer depend_on_people;
    private Integer appearance;
    private Integer difficulty_finding_fitting_clothes;
    private Integer difficulty_finding_liked_clothes;
    private Integer feelings;
    private Integer relationships;
    private Integer pain;
    private Integer numbness;
    private Integer pins_and_needles;
    private Integer weak;
    private Integer heavy;
    private Integer tired;
    private Integer sleeping;
    private Integer concentrating;
    private Integer tense;
    private Integer worried;
    private Integer irritable;
    private Integer depressed;
    private Integer overall;

    public QolResponseArm() {
        this.id = null;
        this.user_id = null;
        this.createdAt = null;
        this.occupation = 0;
        this.housework = 0;
        this.combing_hair = 0;
        this.dressing = 0;
        this.writing = 0;
        this.eating = 0;
        this.washing = 0;
        this.cleaning_teeth = 0;
        this.leisure = 0;
        this.leisure_examples = "";
        this.depend_on_people = 0;
        this.appearance = 0;
        this.difficulty_finding_fitting_clothes = 0;
        this.difficulty_finding_liked_clothes = 0;
        this.feelings = 0;
        this.relationships = 0;
        this.pain = 0;
        this.numbness = 0;
        this.pins_and_needles = 0;
        this.weak = 0;
        this.heavy = 0;
        this.tired = 0;
        this.sleeping = 0;
        this.concentrating = 0;
        this.tense = 0;
        this.worried = 0;
        this.irritable = 0;
        this.depressed = 0;
        this.overall = 0;
    }

}
