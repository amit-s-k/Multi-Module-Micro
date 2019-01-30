package com.stackroute.music.respository;

import com.stackroute.music.model.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface MusicRepository extends MongoRepository<Music, Integer> {
}
