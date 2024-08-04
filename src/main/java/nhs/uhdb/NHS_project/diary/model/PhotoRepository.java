package nhs.uhdb.NHS_project.diary.model;

import java.util.List;

public interface PhotoRepository {
    Long createPhoto(Photo photo);
    Boolean deletePhotoById(Long photo_id);
    Photo getPhotoById(Long photo_id);
    List<Photo> getPhotosByDiaryEntryId(Long diary_entry_id);
    Boolean submitPhotosForDiaryEntryId(List<Photo> photos, Long diary_entry_id);

}
