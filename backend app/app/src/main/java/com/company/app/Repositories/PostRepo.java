package com.company.app.Repositories;

import java.util.List;
import java.util.Optional;

import com.company.app.entities.PostEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findByTitle(String title);

    Optional<PostEntity> findByValue(String value);

    @Query("SELECT p FROM PostEntity p WHERE p.title LIKE %:val% OR p.value LIKE %:val% ")
    Optional<List<PostEntity>> search(@Param("val") String val);

    @Query(value = "SELECT * FROM posts ORDER BY created_at LIMIT 10 ", nativeQuery = true)
    Optional<List<PostEntity>> getPostByDate();

}
