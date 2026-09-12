package com.medialog.config;

import com.medialog.entity.Album;
import com.medialog.entity.Artist;
import com.medialog.entity.User;
import com.medialog.repository.AlbumRepository;
import com.medialog.repository.ArtistRepository;
import com.medialog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadSampleData(ArtistRepository artistRepository,
                                     AlbumRepository albumRepository,
                                     UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("demo", "Demo Listener"));

            Artist radiohead = artistRepository.save(new Artist("Radiohead", null));
            Artist kendrickLamar = artistRepository.save(new Artist("Kendrick Lamar", null));
            Artist bjork = artistRepository.save(new Artist("Björk", null));

            albumRepository.saveAll(List.of(
                    new Album("OK Computer", 1997, null, null, radiohead),
                    new Album("Kid A", 2000, null, null, radiohead),
                    new Album("To Pimp a Butterfly", 2015, null, null, kendrickLamar),
                    new Album("Homogenic", 1997, null, null, bjork)
            ));
        };
    }
}
