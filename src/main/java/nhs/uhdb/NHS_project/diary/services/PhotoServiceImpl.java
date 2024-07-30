package nhs.uhdb.NHS_project.diary.services;

import nhs.uhdb.NHS_project.diary.model.Photo;
import nhs.uhdb.NHS_project.diary.model.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    PhotoRepository photoRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Long createPhoto(Photo photo) {
        return photoRepository.createPhoto(photo);
    }

    @Override
    public Boolean deletePhotoById(Long photo_id) {
        return photoRepository.deletePhotoById(photo_id);
    }

    @Override
    public Photo getPhotoById(Long photo_id) {
        return photoRepository.getPhotoById(photo_id);
    }

    @Override
    public List<Photo> getPhotosByDiaryEntryId(Long diary_entry_id) {
        return photoRepository.getPhotosByDiaryEntryId(diary_entry_id);
    }
}
