package org.he.myunsplash.app.web;


import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.he.myunsplash.app.domain.UnsplashService;
import org.he.myunsplash.app.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.he.myunsplash.app.utils.CommonAssertions.assertIsValidString;

@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class MyUnsplashRestController {

    @Autowired
    UnsplashService unsplashService;

    @GetMapping("/photos")
    ResponseEntity getPhotosByKeyword(@RequestParam(value = "keyword", defaultValue = "")String keyword) {
        log.info("📡 [GET] getPhotosByKeyword() #");
        var photos = new HashMap<String, List<Photo>>();
        if (keyword.equals("")) {
            photos.put("photos", unsplashService.getAllPhotos());
            return new ResponseEntity(photos, HttpStatus.OK);
        }

        photos.put("photos", unsplashService.getByKeyword(keyword));
        return new ResponseEntity(photos, HttpStatus.OK);
    }

    @PostMapping("/photos")
    public ResponseEntity<Object> savePhoto(@RequestBody Photo photo) {
        log.info("📡 [POST] savePhoto()");
        if (photo == null) {
            System.out.println("savePhoto() #photo is null");
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.NO_CONTENT);
        }

        if (!assertIsValidString(photo.getLabel())) {
            System.out.println("savePhoto() #label is not valid");
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
        }

        if (!assertIsValidString(photo.getUrl())) {
            System.out.println("savePhoto() #url is not valid");
            return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
        }

        unsplashService.saveNewPhoto(photo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/photos/{id}")
    public ResponseEntity deletePhoto(@PathVariable String id) {
        log.info("📡 [DELETE] deletePhoto() #id: " + id);

        val photos = unsplashService.deletePhoto(id);
        if (photos.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(photos, HttpStatus.ACCEPTED);

    }
}
