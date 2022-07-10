package org.he.myunsplash.app.dao.repository;

import org.he.myunsplash.app.model.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("SELECT p FROM Photo p WHERE p.label LIKE :keyword")
    Page<Photo> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
