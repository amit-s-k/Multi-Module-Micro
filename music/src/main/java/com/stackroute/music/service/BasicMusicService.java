package com.stackroute.music.service;

import com.stackroute.music.model.Music;
import com.stackroute.music.respository.MusicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BasicMusicService implements AbstractMusicService {
    private MusicRepository musicRepository;

    @Autowired
    public BasicMusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> getAllTracks() {
        log.info("Fetching Tracks");
        return musicRepository.findAll();
    }

    public Music addOrUpdateTrack(Music music) {
        log.info("Updating Tracks");
        return musicRepository.save(music);
    }

    public void deleteTrackById(int id) {
        log.info("Deleting track ");
        musicRepository.deleteById(id);
    }

    public Optional<Music> findTrackById(int id) {
        log.info("Searching repository for track");
        return musicRepository.findById(id);
    }

//    public Music findByTrackName(String trackName) {
//        log.info("Searching repository for trackname {}", trackName);
//        return musicRepository.findByTrackComments(trackName);
//    }
}
