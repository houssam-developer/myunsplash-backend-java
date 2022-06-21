package org.he.myunsplash.app.web;

import org.he.myunsplash.app.domain.UnsplashService;
import org.he.myunsplash.app.model.Photo;
import org.he.myunsplash.app.utils.CommonPredicate;
import org.he.myunsplash.app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class MyUnsplashController {

    @Autowired
    UnsplashService unsplashService;

    @GetMapping("/photos")
    List<Photo> getPhotosByKeyword(@RequestParam(value = "keyword", defaultValue = "")String keyword) {
        if (keyword.equals("")) { return unsplashService.getAllPhotos(); }

        return unsplashService.getByKeyword(keyword);
    }



    @PostMapping("/photos")
    public Photo savePhoto(@RequestBody Photo photo) {
        return new Photo();
    }
}
