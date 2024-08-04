package nhs.uhdb.NHS_project.diary.services;

import nhs.uhdb.NHS_project.diary.model.Photo;

import java.util.List;

public interface PhotoService {
    Long createPhoto(Photo photo);
    Boolean deletePhotoById(Long photo_id);
    Photo getPhotoById(Long photo_id);
    List<Photo> getPhotosByDiaryEntryId(Long diary_entry_id);
}
