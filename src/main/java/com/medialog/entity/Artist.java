package com.medialog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String musicBrainzId;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums = new ArrayList<>();

    protected Artist() {
    }

    public Artist(String name, String musicBrainzId) {
        this.name = name;
        this.musicBrainzId = musicBrainzId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMusicBrainzId() {
        return musicBrainzId;
    }

    public List<Album> getAlbums() {
        return albums;
    }
}
