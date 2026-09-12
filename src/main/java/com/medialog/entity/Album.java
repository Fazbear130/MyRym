package com.medialog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer releaseYear;

    private String coverImageUrl;

    private String musicBrainzId;

    @ManyToOne(optional = false)
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<Rating> ratings = new ArrayList<>();

    protected Album() {
    }

    public Album(String title, Integer releaseYear, String coverImageUrl,
                 String musicBrainzId, Artist artist) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.coverImageUrl = coverImageUrl;
        this.musicBrainzId = musicBrainzId;
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public String getMusicBrainzId() {
        return musicBrainzId;
    }

    public Artist getArtist() {
        return artist;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}
