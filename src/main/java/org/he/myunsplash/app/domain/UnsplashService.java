package org.he.myunsplash.app.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.he.myunsplash.app.model.Photo;
import org.he.myunsplash.app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
@Service
public class UnsplashService {

    @Autowired
    JsonParser jsonParser;

    public List<Photo> getAllPhotos() {
        return jsonParser.readFromJSON();
    }

    public List<Photo> getByKeyword(String keyword) {
        System.out.println("getByKeyword() #keyword: " + keyword);
        String keywordVal = keyword.toLowerCase();
        return jsonParser.readFromJSON()
                .stream()
                .filter(it -> it.getLabel().contains(keywordVal))
                .collect(Collectors.toList());
    }

    public Photo saveNewPhoto(Photo photo) {
        log.info("🚧 saveNewPhoto() #photo: " + photo);
        var photos = jsonParser.readFromJSON();

        try {
            photos.add(0, photo);
            jsonParser.writeToJSON(photos);
        } catch (Exception exception) {
            log.info("🚫 saveNewPhoto() #ex: ", exception);
        }


        return jsonParser.readFromJSON().get(0);
    }

    public Boolean deletePhoto(String id) {
        log.info("🚧 deletePhoto() #id: " + id);

        try {

            var photosUpdated = jsonParser
                    .readFromJSON()
                    .stream()
                    .filter(it -> it.getId() != id)
                    .collect(Collectors.toList());

            jsonParser.writeToJSON(photosUpdated);

        } catch(Exception exception) {
            log.info("🚫 deletePhoto() #exception: " + exception);
        }

        return false;
    }
}
