package org.he.myunsplash.app.dao.repository;

import org.he.myunsplash.app.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

//    @Query("SELECT p FROM Photo p WHERE p.label LIKE :x")
//    List<Photo> findAllByKeyword(@Param("x") String keyword);

    List<Photo> findByLabelContainingIgnoreCase(String keyword);

    List<Photo> findAllByOrderByIdDesc();
}
