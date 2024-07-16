package nhs.uhdb.NHS_project.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Video {
    private Long id;
    private String videoLink;

    public Video() {
        this.id = null;
        this.videoLink = "";
    }
}