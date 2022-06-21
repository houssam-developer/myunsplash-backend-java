package org.he.myunsplash.app.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.he.myunsplash.app.model.Photo;
import org.he.myunsplash.app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        var photos = jsonParser.readFromJSON();
        photos.add(0, photo);
        jsonParser.writeToJSON(photos);

        return jsonParser.readFromJSON().get(0);
    }
}
