package nhs.uhdb.NHS_project.diary.controllers;

import nhs.uhdb.NHS_project.diary.model.Photo;
import nhs.uhdb.NHS_project.diary.services.PhotoService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class PhotoController {
    private PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping(value = "/photo/{photo_id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getPhotoById(@PathVariable Long photo_id) {
        Photo photo = photoService.getPhotoById(photo_id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] photoBytes = photo.getBytes();
        return new ByteArrayResource(photoBytes);
    }

}
