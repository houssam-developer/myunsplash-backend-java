package org.he.myunsplash.app.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.he.myunsplash.app.model.Photo;
import org.he.myunsplash.app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@NoArgsConstructor
@Service
public class UnsplashService {

    @Autowired
    JsonParser jsonParser;

    public List<Photo> getAllPhotos() {
        return jsonParser.readFromJSON();
    }
}
