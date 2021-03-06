package org.he.myunsplash.app.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.he.myunsplash.app.dao.repository.PhotoRepository;
import org.he.myunsplash.app.model.Photo;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Data
@Service
public class DatabaseStorageService implements StorageService{
    private PhotoRepository photoRepository;

    public DatabaseStorageService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public List<Photo> getAllPhotos() {
        try {
            return photoRepository.findAllByOrderByIdDesc();
        } catch(Exception exception) {
            log.info("🚫 getAllPhotos() #exception: " + exception);
            return List.of();
        }
    }

    @Override
    public List<Photo> getByKeyword(String keyword) {
        log.info("🚧 getByKeyword() #keyword: " + keyword);
        try {
            return photoRepository.findByLabelContainingIgnoreCase(keyword);
        } catch(Exception exception) {
            log.info("🚫 getByKeyword() #exception: " + exception);
            return List.of();
        }
    }

    @Override
    public Photo saveNewPhoto(Photo photo) {
        try {
            return photoRepository.save(photo);
        } catch(Exception exception) {
            log.info("🚫 saveNewPhoto() #exception: " + exception);
            return new Photo();
        }
    }

    @Override
    public void deletePhoto(Long id) {
        try {
             photoRepository.deleteById(id);
        } catch(Exception exception) {
            log.info("🚫 deletePhoto() #exception: " + exception);
        }
    }
}
