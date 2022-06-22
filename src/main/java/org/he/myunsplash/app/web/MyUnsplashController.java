package org.he.myunsplash.app.web;


import org.he.myunsplash.app.domain.UnsplashService;
import org.he.myunsplash.app.model.Photo;
import org.he.myunsplash.app.utils.CommonPredicate;
import org.he.myunsplash.app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.he.myunsplash.app.utils.CommonAssertions.assertIsValidString;
import static org.he.myunsplash.app.utils.CommonPredicate.isString;
import static org.he.myunsplash.app.utils.CommonPredicate.isValidString;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class MyUnsplashController {

    @Autowired
    UnsplashService unsplashService;

    @GetMapping("/photos")
    ResponseEntity getPhotosByKeyword(@RequestParam(value = "keyword", defaultValue = "")String keyword) {
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
        System.out.println("POST ====");
        if (photo == null) {
            System.out.println("savePhoto() #photo is null");
            return new ResponseEntity<Object>(HttpEntity.EMPTY, HttpStatus.NO_CONTENT);
        }

        if (!assertIsValidString(photo.getLabel())) {
            System.out.println("savePhoto() #label is not valid");
            return new ResponseEntity<Object>(HttpEntity.EMPTY, HttpStatus.NO_CONTENT);
        }

        if (!assertIsValidString(photo.getUrl())) {
            System.out.println("savePhoto() #url is not valid");
            return new ResponseEntity<Object>(HttpEntity.EMPTY, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Object>(HttpEntity.EMPTY, HttpStatus.NO_CONTENT);
        //return unsplashService.saveNewPhoto(photo);
    }
}
