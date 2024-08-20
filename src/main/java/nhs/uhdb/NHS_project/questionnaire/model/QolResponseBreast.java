package nhs.uhdb.NHS_project.questionnaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class QolResponseBreast {
    private Long id;
    private Long user_id;
    private LocalDate createdAt;
    private Integer occupation;
    private Integer housework;
    private Integer dressing;
    private Integer washing;
    private Integer leisure;
    private String leisure_examples;
    private Integer depend_on_people;
    private Integer appearance;
    private Integer finding_clothes_to_fit;
    private Integer finding_clothes_to_wear;
    private Integer feelings;
    private Integer relationships;
    private Integer pain;
    private Integer numbness;
    private Integer pins_and_needles;
    private Integer tightness;
    private Integer heavy;
    private Integer sleeping;
    private Integer concentrating;
    private Integer worried;
    private Integer irritable;
    private Integer depressed;
    private Integer overall;

    public QolResponseBreast() {
        this.id = null;
        this.user_id = null;
        this.createdAt = null;
        this.occupation = 0;
        this.housework = 0;
        this.dressing = 0;
        this.washing = 0;
        this.leisure = 0;
        this.leisure_examples = "";
        this.depend_on_people = 0;
        this.appearance = 0;
        this.finding_clothes_to_fit = 0;
        this.finding_clothes_to_wear = 0;
        this.feelings = 0;
        this.relationships = 0;
        this.pain = 0;
        this.numbness = 0;
        this.pins_and_needles = 0;
        this.tightness = 0;
        this.heavy = 0;
        this.sleeping = 0;
        this.concentrating = 0;
        this.worried = 0;
        this.irritable = 0;
        this.depressed = 0;
        this.overall = 0;
    }
}
