package com.stackroute.music.service;

import com.stackroute.music.model.Music;
import com.stackroute.music.respository.MusicRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BasicMusicService.class})
public class BasicMusicServiceTest {
    @MockBean
    private MusicRepository musicRepository;
    private Music music;

    @Autowired
    private BasicMusicService basicMusicService;

    @Before
    public void setUp() {
        music = new Music();
        music.setTrack_comments("charlie puth");
        music.setTrack_id(1);
        music.setTrack_name("attention");
    }

    @Test
    public void testFindAllTracks() {
        when(musicRepository.findAll()).thenReturn(Collections.singletonList(music));
        assertEquals(Collections.singletonList(music), basicMusicService.getAllTracks());
    }

    @Test
    public void testAddOrUpdateTrack() {
        when(musicRepository.save(music)).thenReturn(music);
        assertEquals(music, basicMusicService.addOrUpdateTrack(music));
    }

    @Test
    public void testForDeleteTrackById() {
        basicMusicService.deleteTrackById(1);
        verify(musicRepository, times(1)).deleteById(1);
    }

    @Test
    public void testForFindById() {
        basicMusicService.findTrackById(1);
        verify(musicRepository, times(1)).findById(1);
    }
}