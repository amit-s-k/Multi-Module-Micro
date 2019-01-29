package com.stackroute.music.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.music.model.Music;
import com.stackroute.music.service.BasicMusicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MusicControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BasicMusicService basicMusicService;
    @InjectMocks
    private MusicController musicController;
    private Music music;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        music = new Music();
        music.setTrack_comments("charlie puth");
        music.setTrack_id(1);
        music.setTrack_name("attention");
        mockMvc = MockMvcBuilders.standaloneSetup(musicController).build();
    }

    @Test
    public void getAllTracks() throws Exception {
        List<Music> musicList = Collections.singletonList(music);
        when(basicMusicService.getAllTracks()).thenReturn(musicList);
        mockMvc.perform(MockMvcRequestBuilders.get("/music/findalltracks").contentType(MediaType.APPLICATION_JSON).content("some"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addNewTrack() throws Exception {
        when(basicMusicService.addOrUpdateTrack(music)).thenReturn(music);
        mockMvc.perform(MockMvcRequestBuilders.post("/music/addtrack").contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTrackById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/music/delete/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(basicMusicService, times(1)).deleteTrackById(1);
    }

    @Test
    public void updateTrack() throws Exception {
        Music updatedMusic = new Music();
        updatedMusic.setTrack_id(1);
        updatedMusic.setTrack_name("def");
        updatedMusic.setTrack_comments("sdf");
        when(basicMusicService.addOrUpdateTrack(updatedMusic)).thenReturn(updatedMusic);
        mockMvc.perform(MockMvcRequestBuilders.put("/music/update").contentType(MediaType.APPLICATION_JSON).content(asJsonString(updatedMusic)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        verify(basicMusicService, times(1)).addOrUpdateTrack(updatedMusic);
    }

    @Test
    public void findTrackById() throws Exception {
        when(basicMusicService.findTrackById(1)).thenReturn(Optional.of(music));
        mockMvc.perform(MockMvcRequestBuilders.get("/music/find/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}