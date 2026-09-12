package com.medialog.service;

import com.medialog.entity.Album;
import com.medialog.entity.Rating;
import com.medialog.entity.User;
import com.medialog.repository.RatingRepository;
import com.medialog.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RatingService {

    private static final String DEMO_USERNAME = "demo";

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final AlbumService albumService;

    public RatingService(RatingRepository ratingRepository,
                         UserRepository userRepository,
                         AlbumService albumService) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.albumService = albumService;
    }

    public Optional<Rating> findDemoUserRating(Long albumId) {
        User demoUser = findDemoUser();
        return ratingRepository.findByUserIdAndAlbumId(demoUser.getId(), albumId);
    }

    public Double findAverageScore(Long albumId) {
        return ratingRepository.findAverageScoreByAlbumId(albumId);
    }

    @Transactional
    public Rating saveDemoUserRating(Long albumId, Integer score) {
        User demoUser = findDemoUser();
        Album album = albumService.findAlbumById(albumId);

        Rating rating = ratingRepository
                .findByUserIdAndAlbumId(demoUser.getId(), albumId)
                .orElseGet(() -> new Rating(demoUser, album, score));

        rating.setScore(score);
        return ratingRepository.save(rating);
    }

    @Transactional
    public void deleteDemoUserRating(Long albumId) {
        User demoUser = findDemoUser();
        ratingRepository.findByUserIdAndAlbumId(demoUser.getId(), albumId)
                .ifPresent(ratingRepository::delete);
    }

    private User findDemoUser() {
        return userRepository.findByUsername(DEMO_USERNAME)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "Demo user not found"));
    }
}
