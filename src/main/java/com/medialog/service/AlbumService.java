package com.medialog.service;

import com.medialog.entity.Album;
import com.medialog.repository.AlbumRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> findAllAlbums() {
        return albumRepository.findAll();
    }

    public Album findAlbumById(Long id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Album not found"));
    }
}
