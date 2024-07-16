package nhs.uhdb.NHS_project.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exercise {
    private Long id;
    private String name;
    private String description;
    private String videoLink;

    public Exercise() {
        this.id = null;
        this.name = "";
        this.description = "";
        this.videoLink = "";
    }
}
