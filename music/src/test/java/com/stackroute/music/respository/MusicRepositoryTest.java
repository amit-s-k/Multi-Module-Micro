package com.stackroute.music.respository;

import com.stackroute.music.model.Music;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
public class MusicRepositoryTest {

    @Autowired
    private MusicRepository musicRepository;
    private Music music;



    @Before
    public void setUp() {
        musicRepository.deleteAll();
        music = new Music();
        music.setTrack_comments("taylor swift");
        music.setTrack_id(1);
        music.setTrack_name("blank space");
        musicRepository.save(music);
    }


    @Test
    public void testForDeleteMusic() {
        musicRepository.deleteAll();
        musicRepository.save(music);
        long countBeforeDeletion = musicRepository.count();
        musicRepository.findAll().forEach(music1 -> System.out.println(music1.getTrack_name()));
        musicRepository.deleteById(1);
        musicRepository.findAll().forEach(music1 -> System.out.println("fff"+music1));
        assertEquals(countBeforeDeletion , musicRepository.count());
    }

    @Test
    public void testForUpdateMusic() {
        Music updatedMusic = new Music();
        updatedMusic.setTrack_id(1);
        updatedMusic.setTrack_name("hymm for the weekend");
        updatedMusic.setTrack_comments("coldplay");
        assertEquals(updatedMusic, musicRepository.save(updatedMusic));

    }
}