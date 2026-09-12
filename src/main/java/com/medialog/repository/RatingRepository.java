package com.medialog.repository;

import com.medialog.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByUserIdAndAlbumId(Long userId, Long albumId);

    @Query("select avg(r.score) from Rating r where r.album.id = :albumId")
    Double findAverageScoreByAlbumId(@Param("albumId") Long albumId);
}
