package com.medialog.service;

import com.medialog.entity.Album;
import com.medialog.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RatingServiceTests {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private AlbumRepository albumRepository;

    @Test
    void createsUpdatesAndDeletesDemoUserRating() {
        Album album = albumRepository.findAll().getFirst();

        ratingService.saveDemoUserRating(album.getId(), 8);
        assertThat(ratingService.findDemoUserRating(album.getId()))
                .hasValueSatisfying(rating -> assertThat(rating.getScore()).isEqualTo(8));

        ratingService.saveDemoUserRating(album.getId(), 10);
        assertThat(ratingService.findDemoUserRating(album.getId()))
                .hasValueSatisfying(rating -> assertThat(rating.getScore()).isEqualTo(10));

        ratingService.deleteDemoUserRating(album.getId());
        assertThat(ratingService.findDemoUserRating(album.getId())).isEmpty();
    }
}
