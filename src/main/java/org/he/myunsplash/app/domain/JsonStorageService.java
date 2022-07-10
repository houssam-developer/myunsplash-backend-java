package org.he.myunsplash.app.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.he.myunsplash.app.model.Photo;
import org.he.myunsplash.app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
public class JsonStorageService implements StorageService {

    @Autowired
    JsonParser jsonParser;

    @Override
    public List<Photo> getAllPhotos() {
        return jsonParser.readFromJSON();
    }

    @Override
    public List<Photo> getByKeyword(String keyword) {
        System.out.println("getByKeyword() #keyword: " + keyword);
        String keywordVal = keyword.toLowerCase();
        return getPhotos(keywordVal);
    }

    private List<Photo> getPhotos(String keywordVal) {
        return jsonParser.readFromJSON()
                .stream()
                .filter(it -> it.getLabel().contains(keywordVal))
                .collect(Collectors.toList());
    }

    @Override
    public Photo saveNewPhoto(Photo photo) {
        log.info("🚧 saveNewPhoto() #photo: " + photo);
        var photos = jsonParser.readFromJSON();
        photo.setId(Math.round(Math.random() * 100));

        try {
            photos.add(0, photo);
            jsonParser.writeToJSON(photos);
        } catch (Exception exception) {
            log.info("🚫 saveNewPhoto() #ex: ", exception);
        }


        return jsonParser.readFromJSON().get(0);
    }

    @Override
    public void deletePhoto(Long id) {
        log.info("🚧 deletePhoto() #id: " + id);

        try {

            var photosUpdated = jsonParser
                    .readFromJSON()
                    .stream()
                    .filter(it -> !it.getId().equals(id) )
                    .collect(Collectors.toList());

            jsonParser.writeToJSON(photosUpdated);

        } catch(Exception exception) {
            log.info("🚫 deletePhoto() #exception: " + exception);
        }
    }
}
