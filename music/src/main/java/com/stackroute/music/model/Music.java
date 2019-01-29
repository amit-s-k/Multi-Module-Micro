package com.stackroute.music.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "playlist")
@Data
public class Music {
    @Id
    private int track_id;
    private String track_name;
    private String track_comments;
}
