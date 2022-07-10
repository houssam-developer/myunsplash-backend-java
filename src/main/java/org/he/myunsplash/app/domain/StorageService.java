package org.he.myunsplash.app.domain;

import org.he.myunsplash.app.model.Photo;

import java.util.List;

public interface StorageService {

    List<Photo> getAllPhotos();

    List<Photo> getByKeyword(String keyword);

    Photo saveNewPhoto(Photo photo);

    void deletePhoto(Long id);
}
