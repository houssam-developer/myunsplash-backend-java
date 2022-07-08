package org.he.myunsplash.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import org.he.myunsplash.app.model.Photo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JsonParser {
    private ObjectMapper mapper = new ObjectMapper();
    private Path dbJsonPath = Paths.get("src/main/java/org/he/myunsplash/app/dao/data/db.json");


    public void writeToJSON(List<Photo> photos) {
        System.out.println("writeJSON()");
        try {
            mapper.writeValue(dbJsonPath.toFile(), photos.toArray());
        } catch (Exception ex) {
            System.out.println("writeJSON() #ex: " + ex);
        }
    }

    public List<Photo> readFromJSON() {
        System.out.println("readFromJSON()");
        try {
            var photosArray = mapper.readValue(dbJsonPath.toFile(), Photo[].class);
            var photos = new ArrayList<Photo>();
            Arrays.stream(photosArray).forEach(photos::add);

            return photos;
        } catch (Exception ex) {
            System.out.println("readFromJSON() EXCEPTION #ex: " + ex);
            return List.of();
        }
    }
}
