package com.stackroute.music.controller;

import com.stackroute.music.model.Music;
import com.stackroute.music.service.BasicMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/music")
public class MusicController {
    private BasicMusicService basicMusicService;
    @Value("${user.name}")
    String val;

    @Autowired
    public MusicController(BasicMusicService basicMusicService) {
        this.basicMusicService = basicMusicService;
        System.out.println("ggg");

    }

    @GetMapping(value = "/config")
    public String demo() {
        System.out.println("bbb " + val);
        return val;
    }

    @GetMapping(value = "/findalltracks")
    public ResponseEntity<List<Music>> getAllTracks() {
        return new ResponseEntity<>(basicMusicService.getAllTracks(), HttpStatus.OK);

    }

    @PostMapping(value = "/addtrack")
    public ResponseEntity<String> addNewTrack(@RequestBody Music music) {
        ResponseEntity<String> responseEntity;
        try {
            basicMusicService.addOrUpdateTrack(music);
            responseEntity = new ResponseEntity<>("Track Added Sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error Occured While Adding Track", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @DeleteMapping("/delete/{track_id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int track_id) {
        ResponseEntity<?> responseEntity;
        try {
            basicMusicService.deleteTrackById(track_id);

            responseEntity = new ResponseEntity<>("Track Deleted Sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("Error Occured While Deleting Track", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updateTrack(@RequestBody Music music) {
        ResponseEntity<String> responseEntity;
        try {
            basicMusicService.addOrUpdateTrack(music);
            responseEntity = new ResponseEntity<>("Track Updated Sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error Occured While Updating Track", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Music> findTrackById(@PathVariable int id) {
        ResponseEntity<Music> responseEntity;
        Music music = null;
        try {
            Optional<Music> trackById = basicMusicService.findTrackById(id);
            if (trackById.isPresent()) {
                music = trackById.get();
            }
            responseEntity = new ResponseEntity<>(music, HttpStatus.OK);

        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(music, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

//    @GetMapping(value = "/findbyname/{trackname}")
//    public ResponseEntity<Music> findByTrackName(@PathVariable String trackname) {
//        ResponseEntity<Music> responseEntity;
//        try {
//            responseEntity = new ResponseEntity<>(basicMusicService.findByTrackName(trackname), HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//        return responseEntity;
//    }
}
