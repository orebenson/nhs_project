package nhs.uhdb.NHS_project.diary.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Photo {
    private Long id;
    private byte[] bytes;

    public Photo() {
        this.id = null;
        this.bytes = new byte[0];
    }
}
